/*
 * BanDialog.java
 *
 * Created on May 23, 2010, 11:11:47 AM
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

/**
 * Simple popup to provide a GUI for ban length
 * @author ben
 */
public class BanDialog extends javax.swing.JDialog {
    private boolean m_cancel = false;
    /** Creates new form BanDialog */
    public BanDialog(String name) {
        initComponents();
        this.setTitle("Banning " + name + "...");
        this.setModal(true);
    }

    public long getBanLength() {
        if (m_cancel) return 0;
        
        long time = 0;
        try {
            time += Integer.parseInt(spinYears.getValue().toString()) * 31536000;
        } catch (Exception e) { }
        try {
            time += Integer.parseInt(spinDays.getValue().toString()) * 86400;
        } catch (Exception e) { }
        try {
            time += Integer.parseInt(spinHours.getValue().toString()) * 3600;
        } catch (Exception e) { }
        try {
            time += Integer.parseInt(spinMinutes.getValue().toString()) * 60;
        } catch (Exception e) { }

        return time;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        spinYears = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        spinDays = new javax.swing.JSpinner();
        jLabel2 = new javax.swing.JLabel();
        spinHours = new javax.swing.JSpinner();
        jLabel3 = new javax.swing.JLabel();
        spinMinutes = new javax.swing.JSpinner();
        jLabel4 = new javax.swing.JLabel();
        btnBan = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setLocationByPlatform(true);
        setResizable(false);

        spinYears.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        spinYears.setPreferredSize(new java.awt.Dimension(50, 28));
        jPanel1.add(spinYears);

        jLabel1.setText("Years");
        jPanel1.add(jLabel1);

        spinDays.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        spinDays.setPreferredSize(new java.awt.Dimension(50, 28));
        jPanel1.add(spinDays);

        jLabel2.setText("Days");
        jPanel1.add(jLabel2);

        spinHours.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        spinHours.setPreferredSize(new java.awt.Dimension(50, 28));
        jPanel1.add(spinHours);

        jLabel3.setText("Hours");
        jPanel1.add(jLabel3);

        spinMinutes.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(10)));
        spinMinutes.setPreferredSize(new java.awt.Dimension(50, 28));
        jPanel1.add(spinMinutes);

        jLabel4.setText("Minutes");
        jPanel1.add(jLabel4);

        btnBan.setText("Ban");
        btnBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBanActionPerformed(evt);
            }
        });

        btnCancel.setText("Cancel");
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(261, Short.MAX_VALUE)
                .add(btnCancel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(btnBan)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(btnBan)
                    .add(btnCancel))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBanActionPerformed
        dispose();
    }//GEN-LAST:event_btnBanActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        m_cancel = true;
        dispose();
    }//GEN-LAST:event_btnCancelActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                BanDialog bd = new BanDialog("bearzly");
                bd.setVisible(true);
                System.out.println(bd.getBanLength());
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBan;
    private javax.swing.JButton btnCancel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner spinDays;
    private javax.swing.JSpinner spinHours;
    private javax.swing.JSpinner spinMinutes;
    private javax.swing.JSpinner spinYears;
    // End of variables declaration//GEN-END:variables

}
