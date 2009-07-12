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
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ben
 */
public class VisualToolTip extends javax.swing.JPanel {

    /** Creates new form VisualToolTip */
    public VisualToolTip(String name, String stats, String effects,
            int num, int denom, boolean exact) {
        initComponents();
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.weightx = 0.5;
        c.anchor = GridBagConstraints.PAGE_START;
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0; c.gridy = 0;
        c.ipady = 0;
        c.gridwidth = 2;
        c.weighty = 0;
        JLabel lblName = new JLabel("<html><b>" + name + "</b></html>");
        add(lblName, c);

        if ((num >= 0) && (denom > 0)) {
            HealthBar bar = new HealthBar(exact);
            bar.setRatio(num, denom, false);
            c.gridx = 0;
            c.gridy = 1;
            c.ipady = 20;
            c.gridwidth = 2;
            c.weightx = 0;
            add(bar, c);
        }
        c.gridwidth = 1;
        c.ipady = 0;
        c.weighty = 1.0;
        JPanel panelStats = new JPanel();
        JLabel lblStats = new JLabel(stats);
        panelStats.add(lblStats);
        panelStats.setBorder(BorderFactory.createTitledBorder("Stats"));
        JPanel panelEffects = new JPanel();
        JLabel lblEffects = new JLabel(effects);
        panelEffects.add(lblEffects);
        panelEffects.setBorder(BorderFactory.createTitledBorder("Effects"));
        c.weightx = 0.5;
        c.gridx = 0; c.gridy = 2;
        add(panelStats, c);
        c.gridx = 1; c.gridy = 2;
        add(panelEffects, c);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new java.awt.GridBagLayout());
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        javax.swing.JFrame frame = new javax.swing.JFrame("test");
        frame.add(new VisualToolTip("Bulba", "test1", "test2", 4, 6, true));
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
