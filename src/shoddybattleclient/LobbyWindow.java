/*
 * LobbyWindow.java
 *
 * Created on Apr 5, 2009, 12:47:25 PM
 *
 * This file is a part of Shoddy Battle.
 * Copyright (C) 2009  Catherine Fitzpatrick and Benjamin Gwin
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, visit the Free Software Foundation, Inc.
 * online at http://gnu.org.
 */

package shoddybattleclient;

import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import javax.swing.*;
import java.util.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import shoddybattleclient.ChallengeNotifier.Challenge;
import shoddybattleclient.network.ServerLink;
import shoddybattleclient.network.ServerLink.ChallengeMediator;
import shoddybattleclient.utils.*;

/**
 *
 * @author ben
 */
public class LobbyWindow extends javax.swing.JFrame {

    public static class Channel {
        public static final int PROTECTED = 1; // +a
        public static final int OP = 2;        // +o
        public static final int VOICE = 4;     // +v
        public static final int MUTE = 8;      // +b
        public static final int IDLE = 16;     // inactive
        public static final int BUSY = 32;     // ("ignoring challenges")

        public static final String[] MODES =
                { "a", "o", "v", "b", "", "" }; // TODO: last two

        public static final int TYPE_ORDINARY = 0;
        public static final int TYPE_BATTLE = 1;

        private static final ImageIcon[] m_icons = new ImageIcon[3];

        private int m_id;
        private int m_type;
        private String m_name;
        private String m_topic;
        private int m_flags;
        private ChatPane m_chat;
        // TOOD: Do not assume the background is white.
        private static ColourMap m_colours = new ColourMap(Color.WHITE);
        private UserListModel m_users =
                new UserListModel(new ArrayList<User>());

        public static ImageIcon getModeIcon(String file) {
            return new ImageIcon(Toolkit.getDefaultToolkit()
                    .createImage(GameVisualisation.class.getResource(
                    "resources/modes/" + file)));
        }

        static {
            m_icons[0] = getModeIcon("voice.png");
            m_icons[1] = getModeIcon("operator.png");
            m_icons[2] = getModeIcon("protected.png");
        }

        public void setChatPane(ChatPane c) {
            m_chat = c;
        }
        public ChatPane getChatPane() {
            return m_chat;
        }
        public UserListModel getModel() {
            return m_users;
        }
        public static int getLevel(int flags) {
            if ((flags & PROTECTED) != 0)
                return 3;
            if ((flags & OP) != 0)
                return 2;
            if ((flags & VOICE) != 0)
                return 1;
            if ((flags & MUTE) != 0)
                return -1;
            return 0;
        }
        public Channel(int id, int type, String name, String topic, int flags) {
            m_id = id;
            m_type = type;
            m_name = name;
            m_topic = topic;
            m_flags = flags;
        }
        public void addUser(String name, int flags) {
            m_users.add(new User(name, flags));
        }
        public void removeUser(String name) {
            m_users.remove(name);
        }
        public void updateUser(String setter, String name, int flags) {
            User user = getUser(name);
            int old = user.getFlags();
            int diff = old ^ flags;
            if (diff == 0)
                return;
            StringBuilder modes = new StringBuilder();
            String last = null;
            for (int i = 0; i < 4; ++i) {
                int bit = 1 << i;
                if ((diff & bit) != 0) {
                    String prefix = ((old & bit) != 0) ? "-" : "+";
                    if (!prefix.equals(last)) {
                        modes.append(prefix);
                    }
                    last = prefix;
                    modes.append(MODES[i]);
                }
            }
            String msg;
            if (setter.length() > 0) {
                msg = Text.getText(26, 0, new String[] {setter, modes.toString(), name});
            } else {
                msg = Text.getText(26, 1, new String[] {modes.toString(), name});
            }
            msg = "<font class='mode'>" + msg + "</font>";
            m_chat.getLobby().showChannelMessage(this, null,
                    msg, false);
            user.setLevel(flags);
        }
        public String getUserHtml(User user) {
            return user.getHtml(m_colours.getColour(user.getName()));
        }
        
        class UserCellRenderer extends JLabel implements ListCellRenderer {
            public Component getListCellRendererComponent(JList list,
                    Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                User user = (User)value;
                setText("<html>" + getUserHtml(user) + "</html>");
                int level = user.getLevel();
                if (level > 0) {
                    setIcon(m_icons[level - 1]);
                } else {
                    setIcon(null);
                }
                if (isSelected) {
                    setBackground(list.getSelectionBackground());
                    setForeground(list.getSelectionForeground());
                } else {
                    setBackground(list.getBackground());
                    setForeground(list.getForeground());
                }
                setEnabled(list.isEnabled());
                setFont(list.getFont());
                setOpaque(true);
                return this;
            }

        }

        public ListCellRenderer getRenderer() {
            return new UserCellRenderer();
        }
        public void sort() {
            m_users.sort();
        }
        public String getTopic() {
            return m_topic;
        }
        public void setTopic(String topic) {
            m_topic = topic;
        }
        public String getName() {
            return m_name;
        }
        int getId() {
            return m_id;
        }
        public User getUser(String name) {
            return m_users.getUser(name);
        }
        public int getType() {
            return m_type;
        }
    }

    /**
     * Algorithm for assigning to each string a colour that contrasts well with
     * the background
     */
    public static class ColourMap {
        private static final int BRIGHTNESS_DELTA = 40000;//100000;
        private static final int COLOUR_DELTA = 500;

        private static final Random m_random = new Random(2718281);
        private static final Color[] m_colours = new Color[255];
        private static int getBrightness(Color c) {
            return c.getRed() * 299 + c.getGreen() * 587 + c.getBlue() * 114;
        }
        private static int getDifference(Color c1, Color c2) {
            int r1 = c1.getRed();
            int g1 = c1.getGreen();
            int b1 = c1.getBlue();
            int r2 = c2.getRed();
            int g2 = c2.getGreen();
            int b2 = c2.getBlue();
            return Math.max(r1, r2) - Math.min(r1, r2)
                    + Math.max(g1, g2) - Math.min(g1, g2)
                    + Math.max(b1, b2) - Math.min(b1, b2);
        }
        private static Color getColour() {
            int r = m_random.nextInt(256);
            int g = m_random.nextInt(256);
            int b = m_random.nextInt(256);
            return new Color(r, g, b);
        }
        private static Color getVisibleColour(Color background) {
            while (true) {
                Color c = getColour();
                int b = getBrightness(c);
                int delta = Math.abs(b - getBrightness(background));
                if ((delta > BRIGHTNESS_DELTA)
                        && (getDifference(c, background) > COLOUR_DELTA))
                    return c;
            }
        }
        public ColourMap(Color background) {
            Set set = new HashSet();
            for (int i = 0; i < m_colours.length; ++i) {
                Color c = getVisibleColour(background);
                while (set.contains(c)) {
                    c = getVisibleColour(background);
                }
                set.add(c);
            }
            set.toArray(m_colours);
        }
        public Color getColour(Object key) {
            return m_colours[Math.abs(key.hashCode() % m_colours.length)];
        }
    }

    public static class User implements Comparable {
        public final static int STATUS_PRESENT = 0;
        public final static int STATUS_AWAY = 1;
        private String m_name;
        private int m_status = 0;
        private int m_flags, m_level;
        private List<Integer> m_battles = new ArrayList<Integer>();

        public User(String name, int flags) {
            m_name = name;
            m_flags = flags;
            m_level = Channel.getLevel(flags);
        }
        public int getFlags() {
            return m_flags;
        }
        public void setLevel(int flags) {
            m_flags = flags;
            m_level = Channel.getLevel(flags);
        }
        public int getLevel() {
            return m_level;
        }
        public void setStatus(int status) {
            m_status = status;
        }
        public String getName() {
            return m_name;
        }
        public int compareTo(Object o2) {
            User u2 = ((User)o2);
            if (m_level > u2.m_level)
                return -1;
            if (m_level < u2.m_level)
                return 1;
            if (m_status < u2.m_status)
                return -1;
            if (m_status > u2.m_status)
                return 1;
            String s2 = u2.m_name;
            return m_name.compareToIgnoreCase(s2);
        }
        public String getPrefix() {
            String[] prefixes = { "", "+", "@", "&" };
            return prefixes[m_level];
        }
        public void addBattle(int id) {
            m_battles.add(id);
        }
        public void removeBattle(int id) {
            m_battles.remove(id);
        }
        @Override
        public boolean equals(Object o2) {
            return ((User)o2).m_name.equals(m_name);
        }
        @Override
        public String toString() {
            return m_name;
        }
        public String getHtml(Color c) {
            if (m_level != -1) {
                int r = c.getRed();
                int g = c.getGreen();
                int b = c.getBlue();
                String colour = "rgb(" + r + "," + g + "," + b + ")";
                String style = (m_battles.size() > 0) ? "font-style: italic;" : "";
                return "<font class='name' style='color: "
                    + colour + style + "'>" + m_name + "</font>";
            }
            return "<font class='mute'>"
                    + m_name + "</font>";
        }
    }

    //private ChatPane m_chat;
    private ChallengeNotifier m_notifier;
    private String m_name;
    private ServerLink m_link;
    private Map<Integer, Channel> m_channels = new HashMap<Integer, Channel>();

    public Channel getChannel(String name) {
        for (Channel i : m_channels.values()) {
            if (name.equals(i.getName())) {
                return i;
            }
        }
        return null;
    }

    public void addChannel(Channel channel) {
        m_channels.put(channel.m_id, channel);
        
        ChatPane c = new ChatPane(channel, this, m_name);
        c.getChat().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                m_notifier.processClick(e);
            }
        });
        channel.setChatPane(c);

        if (channel.getType() == Channel.TYPE_BATTLE)
            return;
        
        String name = channel.getName();
        c.addMessage(null, "<b>The topic for #"
                + name + " is: "
                + channel.getTopic()
                + "</b>", false);
        tabChats.add("#" + name, c);
    }
    
    public Channel getChannel(int id) {
        return m_channels.get(id);
    }

    public void handleJoinPart(int id, String user, boolean join) {
        Channel channel = m_channels.get(id);
        if (channel != null) {
            if (join) {
                channel.addUser(user, 0);
            } else {
                channel.removeUser(user);
            }
        }
        if (channel.getChatPane() == tabChats.getSelectedComponent()) {
            listUsers.setModel(new UserListModel(channel.getModel().getList()));
        }
    }

    /** Creates new form LobbyWindow */
    public LobbyWindow(ServerLink link, String userName) {
        initComponents();
        m_link = link;
        m_link.setLobbyWindow(this);
        m_name = userName;
        m_notifier = new ChallengeNotifier(this);
        setGlassPane(m_notifier);

        tabChats.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                Component comp = tabChats.getSelectedComponent();
                boolean isChat = comp instanceof ChatPane;
                m_notifier.setVisible(isChat);
                if (!isChat) return;
                ChatPane c = (ChatPane)comp;
                Channel channel = c.getChannel();
                listUsers.setModel(channel.getModel());
                listUsers.setCellRenderer(channel.getRenderer());
            }
        });

        setTitle("Shoddy Battle - " + userName);
    }

    public ServerLink getLink() {
        return m_link;
    }

    /**
     * Returns the bounds of the main chat pane
     */
    public Rectangle getChatBounds() {
        Component c = tabChats.getComponentAt(0);
        if (!(c instanceof ChatPane)) return null;
        ChatPane chat = (ChatPane)c;
        Point p = chat.getPane().getLocation();
        Point p2 = chat.getLocation();
        Point p3 = tabChats.getLocation();
        JScrollPane pane = chat.getPane();
        int w = pane.getWidth() - pane.getVerticalScrollBar().getWidth();
        int h = pane.getHeight();
        int x = p.x + p2.x + p3.x;
        int y = p.y + p2.y + p3.y;
        return new Rectangle(x, y, w, h);
    }

    public static void viewWebPage(URL page) {
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println(page);
        }
    }

    /**
     * Takes some letters and returns a list of matching usernames
     */
    public List<String> autocompleteUser(String str) {
        str = str.toLowerCase();
        UserListModel model = (UserListModel)listUsers.getModel();
        List<User> list = model.getList();
        List<String> ret = new ArrayList<String>();
        for (User u : list) {
            String name = u.m_name;
            if (name.toLowerCase().startsWith(str)) {
                ret.add(name);
            }
        }
        return ret;
    }

    public void addChallenge(String name, boolean incoming, int gen, int n) {
        m_notifier.addChallenge(name, incoming, gen, n);
    }

    public ChallengeMediator getChallengeMediator(String name) {
        return m_notifier.getMediator(name);
    }

    public void cancelChallenge(String name) {
        m_notifier.removeChallenge(name);
    }

    public void cancelChallenge(int id) {
        m_notifier.removeChallenge(id);
    }

    public ChatPane getChat() {
        return (ChatPane)tabChats.getSelectedComponent();
    }

    public String getUserName() {
        return m_name;
    }

    public void handleUpdateStatus(int id, String setter, String user, int flags) {
        Channel channel = m_channels.get(id);
        if (channel != null) {
            channel.updateUser(setter, user, flags);
            if (channel.getChatPane() == tabChats.getSelectedComponent()) {
                updateUsers(channel);
            }
        }
    }

    private void updateUsers(Channel channel) {
        UserListModel model = channel.getModel();
        model.sort();
        listUsers.setModel(new UserListModel(model.getList()));
    }

    public void showChannelMessage(Channel channel,
            String user, String message, boolean encode) {
        if (channel.getType() == Channel.TYPE_ORDINARY) {
            channel.getChatPane().addMessage(user, message, encode);
        } else {
            // battle chat message
            BattleWindow wnd = m_link.getBattle(channel.getId());
            if (wnd != null) {
                wnd.addMessage(user, message, encode);
            }
        }
    }

    public void handleChannelMessage(int id, String user, String message) {
        Channel channel = m_channels.get(id);
        if (channel != null) {
            User u = channel.getUser(user);
            String prefix = u.getPrefix();
            showChannelMessage(channel,
                    prefix + channel.getUserHtml(u), message, true);
        }
    }

    public void closeTab(int index) {
        tabChats.removeTabAt(index);
    }

    public void openUserPanel(String user, boolean incoming, Challenge c) {
        int index = tabChats.getTabCount();
        UserPanel panel = new UserPanel(user, m_link, index);
        tabChats.add(user, panel);
        tabChats.setSelectedComponent(panel);
        if (incoming) {
            panel.setIncoming();
            panel.setOptions(c);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnChallenge = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listUsers = new javax.swing.JList();
        tabChats = new CloseableTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setLocationByPlatform(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        btnChallenge.setText("Challenge");
        btnChallenge.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChallengeActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(listUsers);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(tabChats, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 108, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(btnChallenge, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 110, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createSequentialGroup()
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                        .add(9, 9, 9)
                        .add(btnChallenge))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, tabChats, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you " +
                "want to leave this server?", "Disconnecting...", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            this.dispose();
            m_link.close();
            new WelcomeWindow().setVisible(true);
        }
    }//GEN-LAST:event_formWindowClosing

    private void btnChallengeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChallengeActionPerformed
        User user = (User)listUsers.getSelectedValue();
        if (user == null) return;
        String opponent = user.getName();
        if (opponent.equals(m_name) && false) {
            // todo: internationalisation
            JOptionPane.showMessageDialog(this, "You cannot challenge yourself.");
        } else {
            openUserPanel(opponent, false, null);
        }
}//GEN-LAST:event_btnChallengeActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LobbyWindow lw = new LobbyWindow(null, "Ben");
                lw.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChallenge;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listUsers;
    private javax.swing.JTabbedPane tabChats;
    // End of variables declaration//GEN-END:variables

}
