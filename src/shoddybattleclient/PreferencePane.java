/*
 * PreferencePane.java
 *
 * Created on Jun 29, 2009, 5:22:54 PM

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

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import shoddybattleclient.Preference.HealthDisplay;
import shoddybattleclient.utils.Text;

/**
 *
 * @author ben
 */
public class PreferencePane extends javax.swing.JFrame {

    /** Creates new form PreferencePane */
    public PreferencePane() {
        initComponents();

        chkTimestamps.setSelected(Preference.timeStampsEnabled());
        txtTimestampFormat.setText(Preference.getTimeStampFormat());
        txtTimestampFormat.setEnabled(chkTimestamps.isSelected());
        chkAnimateHealth.setSelected(Preference.animateHealthBars());

        txtIgnored.setText(Preference.getIgnoredUsersStr());

        cmbUserHealth.setModel(new DefaultComboBoxModel(Preference.HealthDisplay.values()));
        cmbUserHealth.setSelectedItem(Preference.getHealthDisplay(true));
        cmbOppHealth.setModel(new DefaultComboBoxModel(Preference.HealthDisplay.values()));
        cmbOppHealth.setSelectedItem(Preference.getHealthDisplay(false));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        chkTimestamps = new javax.swing.JCheckBox();
        jLabel1 = new javax.swing.JLabel();
        txtTimestampFormat = new javax.swing.JTextField();
        lblTimestampInfo = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIgnored = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cmbUserHealth = new javax.swing.JComboBox();
        cmbOppHealth = new javax.swing.JComboBox();
        lblUserHealth = new javax.swing.JLabel();
        lblEnemyHealth = new javax.swing.JLabel();
        chkAnimateHealth = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setOpaque(false);

        chkTimestamps.setText("Enable Timestamps");
        chkTimestamps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkTimestampsActionPerformed(evt);
            }
        });

        jLabel1.setText("Timestamp Format:");

        txtTimestampFormat.setText("[h:m:s]");
        txtTimestampFormat.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtTimestampFormatCaretUpdate(evt);
            }
        });

        lblTimestampInfo.setFont(new java.awt.Font("Lucida Grande", 1, 10));
        lblTimestampInfo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        jLabel2.setText("Ignored Users:");

        txtIgnored.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                txtIgnoredCaretUpdate(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 1, 10));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Type user names separated by commas");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(lblTimestampInfo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(chkTimestamps)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(txtTimestampFormat, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel2)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(txtIgnored, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(chkTimestamps)
                    .add(jLabel1)
                    .add(txtTimestampFormat, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(lblTimestampInfo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 20, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(txtIgnored, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel3)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Chat", jPanel1);

        jPanel2.setOpaque(false);

        jLabel4.setText("Show my health changes as:");

        jLabel5.setText("Show enemy health changes as:");

        cmbUserHealth.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbUserHealthItemStateChanged(evt);
            }
        });

        cmbOppHealth.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cmbOppHealthItemStateChanged(evt);
            }
        });

        chkAnimateHealth.setText("Animate health bars?");
        chkAnimateHealth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkAnimateHealthActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(chkAnimateHealth))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(cmbOppHealth, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(cmbUserHealth, 0, 93, Short.MAX_VALUE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(lblEnemyHealth, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE)
                    .add(lblUserHealth, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 90, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(cmbUserHealth, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(lblUserHealth, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2Layout.createSequentialGroup()
                        .add(jLabel5)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(chkAnimateHealth))
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(cmbOppHealth, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(lblEnemyHealth, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(167, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Battle", jPanel2);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void chkTimestampsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkTimestampsActionPerformed
        boolean selected = chkTimestamps.isSelected();
        Preference.setTimeStampsEnabled(selected);
        txtTimestampFormat.setEnabled(selected);
    }//GEN-LAST:event_chkTimestampsActionPerformed

    private void txtTimestampFormatCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtTimestampFormatCaretUpdate
        String format = txtTimestampFormat.getText();
        Date d = new Date();
        String date = "Invalid";
        try {
            SimpleDateFormat f = new SimpleDateFormat(format);
            date = f.format(d);
        } catch (Exception e) {

        }
        lblTimestampInfo.setText("H: 0-24 h: 0-12 m: 0-60 s: 0-60 ' ': escape    Current: " + date);
        Preference.setTimeStampFormat(txtTimestampFormat.getText());
    }//GEN-LAST:event_txtTimestampFormatCaretUpdate

    private void cmbUserHealthItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbUserHealthItemStateChanged
        HealthDisplay disp = (HealthDisplay)cmbUserHealth.getSelectedItem();
        lblUserHealth.setText("<html>" + Text.formatHealthChange(40, 368, disp) + "</html>");
        Preference.setUserHealthDisplay(disp);
    }//GEN-LAST:event_cmbUserHealthItemStateChanged

    private void cmbOppHealthItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cmbOppHealthItemStateChanged
        HealthDisplay disp = (HealthDisplay)cmbOppHealth.getSelectedItem();
        lblEnemyHealth.setText("<html>" + Text.formatHealthChange(4, 48, disp) + "</html>");
        Preference.setOpponentHealthDisplay(disp);
    }//GEN-LAST:event_cmbOppHealthItemStateChanged

    private void txtIgnoredCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_txtIgnoredCaretUpdate
        Preference.setIgnoredUsers(txtIgnored.getText());
    }//GEN-LAST:event_txtIgnoredCaretUpdate

    private void chkAnimateHealthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkAnimateHealthActionPerformed
        Preference.setAnimateHealthBars(chkAnimateHealth.isSelected());
    }//GEN-LAST:event_chkAnimateHealthActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PreferencePane().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chkAnimateHealth;
    private javax.swing.JCheckBox chkTimestamps;
    private javax.swing.JComboBox cmbOppHealth;
    private javax.swing.JComboBox cmbUserHealth;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblEnemyHealth;
    private javax.swing.JLabel lblTimestampInfo;
    private javax.swing.JLabel lblUserHealth;
    private javax.swing.JTextField txtIgnored;
    private javax.swing.JTextField txtTimestampFormat;
    // End of variables declaration//GEN-END:variables

}
