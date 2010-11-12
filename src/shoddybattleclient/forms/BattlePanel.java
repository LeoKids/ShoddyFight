/*
 * BattlePanel.java
 *
 * Created on Jun 20, 2009, 2:48:59 PM
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

package shoddybattleclient.forms;

import java.util.ArrayList;
import shoddybattleclient.utils.*;
import javax.swing.*;
import shoddybattleclient.network.ServerLink;

/**
 *
 * @author ben
 */
public class BattlePanel extends javax.swing.JPanel {

    public static class Battle {
        public int id;
        public String[] players;
        public int generation;
        public int n;
        public boolean rated;
        public int ladder;
        public int population;
    }

    private BattleTableModel m_model;
    private ServerLink m_link;
    private Battle[] m_battles;

    /** Creates new form BattlePanel */
    public BattlePanel(ServerLink link) {
        m_model = new BattleTableModel(link);
        initComponents();
        m_link = link;

        tblBattles.setModel(m_model);
    }

    public void setBattles(Battle[] battles) {
        m_battles = battles;
        updateBattleTable();
    }
    
    private void updateBattleTable() {
        m_model = new BattleTableModel(m_link);

        String filter = txtPlayerFilter.getText().trim().toUpperCase();
        Battle[] battles = m_battles;
        if (!filter.equals("")) {
            ArrayList<Battle> filtered = new ArrayList<Battle>();
            for (Battle battle : m_battles) {
                String[] players = battle.players;
                for (String p : players) {
                    if (p.toUpperCase().startsWith(filter)) {
                        filtered.add(battle);
                        break;
                    }
                }
            }
            battles = (Battle[])filtered.toArray(new Battle[filtered.size()]);
        }
        
        for (Battle battle : battles) {
            m_model.addBattle(battle.id, battle.generation, battle.ladder,
                    battle.rated, battle.players[0], battle.players[1],
                    battle.n, battle.population);
        }
        tblBattles.setModel(m_model);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblBattles = new SortableJTable();
        btnRefresh = new javax.swing.JButton();
        btnJoin = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtPlayerFilter = new javax.swing.JTextField();

        setOpaque(false);

        tblBattles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblBattles.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tblBattles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblBattlesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblBattles);

        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRefreshActionPerformed(evt);
            }
        });

        btnJoin.setText("Join");
        btnJoin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJoinActionPerformed(evt);
            }
        });

        jLabel1.setText("Player:");

        txtPlayerFilter.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPlayerFilterKeyReleased(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 494, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(txtPlayerFilter, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
                        .add(18, 18, 18)
                        .add(btnJoin, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 89, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(btnRefresh)))
                .addContainerGap())
        );

        layout.linkSize(new java.awt.Component[] {btnJoin, btnRefresh}, org.jdesktop.layout.GroupLayout.HORIZONTAL);

        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnRefresh)
                    .add(btnJoin)
                    .add(jLabel1)
                    .add(txtPlayerFilter, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnJoinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJoinActionPerformed
        int idx = tblBattles.getSelectedRow();
        int fid = m_model.getId(idx);
        if (fid != -1) {
            m_link.joinChannel(String.valueOf(fid));
        }
    }//GEN-LAST:event_btnJoinActionPerformed

    private void btnRefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRefreshActionPerformed
        m_link.requestChannelList();
    }//GEN-LAST:event_btnRefreshActionPerformed

    private void txtPlayerFilterKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPlayerFilterKeyReleased
        updateBattleTable();
    }//GEN-LAST:event_txtPlayerFilterKeyReleased

    private void tblBattlesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblBattlesMouseClicked
        if (evt.getClickCount() == 2) {
            btnJoinActionPerformed(null);
        }
    }//GEN-LAST:event_tblBattlesMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnJoin;
    private javax.swing.JButton btnRefresh;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblBattles;
    private javax.swing.JTextField txtPlayerFilter;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
        JFrame frame = new JFrame("Battle Panel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        BattlePanel panel = new BattlePanel(null);
        frame.add(panel);
        Battle b = new Battle();
        b.id = 10;
        b.ladder = 2;
        b.n = 2;
        b.population = 3;
        b.players = new String[] {"bearzly", "Catherine"};
        panel.setBattles(new Battle[] {b});
        frame.setVisible(true);
    }
}
