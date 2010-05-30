/*
 * VisualToolTip.java
 *
 * Created on Apr 30, 2009, 10:58:16 PM
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
import java.awt.Font;
import java.util.List;
import javax.swing.JLabel;
import shoddybattleclient.GameVisualisation.VisualPokemon;
import shoddybattleclient.shoddybattle.Pokemon;
import shoddybattleclient.shoddybattle.Pokemon.Gender;
import shoddybattleclient.utils.Text;

/**
 *
 * @author ben
 */
public class VisualToolTip extends javax.swing.JPanel {
    private HealthBar m_healthBar;
    private JLabel[] m_statLabels = new JLabel[7];
    private static String[] statPrefixes = new String[7];

    static {
        int longest = 0;
        for (int i = 0; i < statPrefixes.length; i++) {
            statPrefixes[i] = Pokemon.getStatName(i + 1) + ": ";
            if (statPrefixes[i].length() > longest) longest = statPrefixes[i].length();
        }
        for (int i = 0; i < 7; i++) {
            while (statPrefixes[i].length() < longest) {
                statPrefixes[i] += " ";
            }
        }
    }

    /** Creates new form VisualToolTip */
    public VisualToolTip(VisualPokemon p, boolean fraction) {
        initComponents();
        m_healthBar = new HealthBar(fraction);
        m_healthBar.setRatio(p.getNumerator(), p.getDenominator(), false);
        panelHealth.add(m_healthBar);
        for (int i = 0; i < m_statLabels.length; i++) {
            JLabel label = new JLabel(statPrefixes[i]);
            label.setForeground(Color.WHITE);
            label.setFont(Font.decode(Font.MONOSPACED));
            label.setFont(label.getFont().deriveFont(12.0f));
            m_statLabels[i] = label;
            panelStats.add(label);
        }
        if (p.getName() == null) {
            lblName.setText("???");
            lblLevel.setText("");
            return;
        }
        lblName.setText(p.getName());
        if (p.isFainted()) lblName.setForeground(Color.GRAY);
        int level = p.getLevel();
        Gender g = Gender.getGender(p.getGender());
        String gender = "";
        if (Gender.GENDER_MALE.equals(g)) {
            gender = String.valueOf('\u2642');
        } else if (Gender.GENDER_FEMALE.equals(g)) {
            gender = String.valueOf('\u2640');
        }
        lblLevel.setText("Level " + String.valueOf(level) + " " + gender);
        
        List<String> statuses = p.getStatuses();
        if (statuses.size() == 0) {
            lblEffects.setText("No Effects");
        } else {
            StringBuilder sb = new StringBuilder("<html>");
            for (String s : statuses) {
                sb.append("-");
                sb.append(s);
                sb.append("<br>");
            }
            sb.append("</html>");
            lblEffects.setText(sb.toString());
        }
        for (int i = 0; i < m_statLabels.length; i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(statPrefixes[i]);
            //todo calc stat
            sb.append(p.getStatLevel(i + 1));
            m_statLabels[i].setText(sb.toString());
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
        java.awt.GridBagConstraints gridBagConstraints;

        lblName = new javax.swing.JLabel();
        panelHealth = new javax.swing.JPanel();
        lblLevel = new javax.swing.JLabel();
        panelStats = new javax.swing.JPanel();
        panelEffects = new javax.swing.JPanel();
        lblEffects = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 51));
        setLayout(new java.awt.GridBagLayout());

        lblName.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        lblName.setForeground(new java.awt.Color(255, 255, 255));
        lblName.setText("Pokemon");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(2, 0, 0, 0);
        add(lblName, gridBagConstraints);

        panelHealth.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 102, 102)));
        panelHealth.setPreferredSize(new java.awt.Dimension(150, 25));
        panelHealth.setLayout(new java.awt.GridLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 2);
        add(panelHealth, gridBagConstraints);

        lblLevel.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        lblLevel.setForeground(new java.awt.Color(255, 255, 255));
        lblLevel.setText("Level");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        add(lblLevel, gridBagConstraints);

        panelStats.setBackground(new java.awt.Color(79, 79, 79));
        panelStats.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelStats.setPreferredSize(new java.awt.Dimension(100, 115));
        panelStats.setLayout(new java.awt.GridLayout(7, 1, 2, 0));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridheight = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.6;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 5, 2);
        add(panelStats, gridBagConstraints);

        panelEffects.setBackground(new java.awt.Color(79, 79, 79));
        panelEffects.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelEffects.setPreferredSize(new java.awt.Dimension(100, 50));
        panelEffects.setLayout(new javax.swing.BoxLayout(panelEffects, javax.swing.BoxLayout.PAGE_AXIS));

        lblEffects.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        lblEffects.setForeground(new java.awt.Color(255, 255, 255));
        lblEffects.setText("No Effects");
        panelEffects.add(lblEffects);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridheight = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTH;
        gridBagConstraints.weightx = 0.4;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(4, 5, 5, 2);
        add(panelEffects, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        javax.swing.JFrame frame = new javax.swing.JFrame("test");
        VisualToolTip t = new VisualToolTip(new VisualPokemon(), true);
        frame.add(t);
        frame.setSize(265, 175);
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblEffects;
    private javax.swing.JLabel lblLevel;
    private javax.swing.JLabel lblName;
    private javax.swing.JPanel panelEffects;
    private javax.swing.JPanel panelHealth;
    private javax.swing.JPanel panelStats;
    // End of variables declaration//GEN-END:variables

}
