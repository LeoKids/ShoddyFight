/*
 * FindPanel.java
 *
 * Created on May 29, 2009, 3:12:27 PM
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

import java.awt.FileDialog;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.JOptionPane;
import shoddybattleclient.forms.UserPanel.TeamBox;
import shoddybattleclient.network.ServerLink;
import shoddybattleclient.shoddybattle.Generation;
import shoddybattleclient.shoddybattle.Generation.Metagame;
import shoddybattleclient.shoddybattle.Pokemon;

/**
 *
 * @author ben
 */
public class FindPanel extends javax.swing.JPanel {

    private ServerLink m_link;
    private Pokemon[] m_team;

    /** Creates new form FindPanel */
    public FindPanel(ServerLink link) {
        initComponents();
        m_link = link;
    }

    public void updateMetagames() {
        cmbGeneration.removeAllItems();
        for (Generation gen : m_link.getGenerations()) {
            cmbGeneration.addItem(gen);
        }
    }

    public void unsetTeam() {
        ((TeamBox)panelSprites).reset();
        btnLoad.setEnabled(true);
        btnCancel.setEnabled(false);
    }

    private String getFriendlyPartySize(int partySize) {
        String[] partySizes = new String[] {"Singles", "Doubles"};
        if (partySize <= partySizes.length) {
            return partySizes[partySize - 1];
        }
        return partySize + " vs " + partySize;
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

        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnLoad = new javax.swing.JButton();
        chkRated = new javax.swing.JCheckBox();
        panelSprites = new TeamBox();
        jPanel4 = new javax.swing.JPanel();
        btnFind = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cmbGeneration = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        cmbLadder = new javax.swing.JComboBox();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        lblDescription = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        lblMode = new javax.swing.JLabel();
        lblTeamLength = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listBans = new javax.swing.JList();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listClauses = new javax.swing.JList();

        setOpaque(false);

        jPanel5.setLayout(new java.awt.GridLayout(1, 2));

        jPanel1.setOpaque(false);

        btnLoad.setText("Load Team");
        btnLoad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoadActionPerformed(evt);
            }
        });

        chkRated.setSelected(true);
        chkRated.setText("Rated?");

        panelSprites.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        panelSprites.setOpaque(false);
        panelSprites.setLayout(new java.awt.GridLayout(2, 3));

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.GridLayout(1, 2, 2, 0));

        btnFind.setText("Find");
        btnFind.setEnabled(false);
        btnFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFindActionPerformed(evt);
            }
        });
        jPanel4.add(btnFind);

        btnCancel.setText("Cancel");
        btnCancel.setEnabled(false);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel4.add(btnCancel);

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new java.awt.GridBagLayout());

        jLabel2.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel2.setText("Generation:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 3);
        jPanel2.add(jLabel2, gridBagConstraints);

        cmbGeneration.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Generation 4", "Generation 5" }));
        cmbGeneration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbGenerationActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 2.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 3, 0);
        jPanel2.add(cmbGeneration, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jLabel1.setText("Ladder:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 3);
        jPanel2.add(jLabel1, gridBagConstraints);

        cmbLadder.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Standard", "Uber", "UU", "Suspect" }));
        cmbLadder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLadderActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        jPanel2.add(cmbLadder, gridBagConstraints);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(12, 12, 12)
                        .add(chkRated, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 72, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(18, 18, 18)
                        .add(btnLoad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 125, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 221, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(panelSprites, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 221, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 12, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(chkRated)
                    .add(btnLoad))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(panelSprites, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 91, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setOpaque(false);

        jPanel8.setOpaque(false);
        jPanel8.setLayout(new javax.swing.BoxLayout(jPanel8, javax.swing.BoxLayout.PAGE_AXIS));

        lblDescription.setFont(new java.awt.Font("Lucida Grande", 0, 12)); // NOI18N
        lblDescription.setText("<html>This is some information about this ladder. This should fit 3 lines worth I hope</html>");
        lblDescription.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel8.add(lblDescription);

        jPanel9.setMinimumSize(new java.awt.Dimension(0, 15));
        jPanel9.setOpaque(false);

        org.jdesktop.layout.GroupLayout jPanel9Layout = new org.jdesktop.layout.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 228, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 10, Short.MAX_VALUE)
        );

        jPanel8.add(jPanel9);

        lblMode.setText("Singles");
        jPanel8.add(lblMode);

        lblTeamLength.setText("6 Pokemon per team");
        jPanel8.add(lblTeamLength);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(198, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Rules", jPanel3);

        jPanel6.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 11)); // NOI18N
        jLabel3.setText("Ban List:");

        jScrollPane2.setViewportView(listBans);

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .add(jLabel3))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel3)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Banlist", jPanel6);

        jPanel7.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 11));
        jLabel4.setText("Clauses:");

        jScrollPane1.setViewportView(listClauses);

        org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE)
                    .add(jLabel4))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel4)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Clauses", jPanel7);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(139, 139, 139)
                .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(90, 90, 90))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(94, Short.MAX_VALUE))
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoadActionPerformed
        FileDialog fd = new FileDialog(m_link.getLobby(), "Choose a team to load", FileDialog.LOAD);
        fd.setVisible(true);
        if (fd.getFile() == null) return;
        String file = fd.getDirectory() + fd.getFile();
        TeamBox box = (TeamBox)panelSprites;
        m_team = box.loadFromTeam(file, m_link.getGeneration());
        btnFind.setEnabled(m_team != null);
        if (m_team == null) {
            JOptionPane.showMessageDialog(this, "Selected team file could not be loaded");
        }
    }//GEN-LAST:event_btnLoadActionPerformed

    public static String join(List<?> items, String glue) {
        int length = items.size();
        if (length == 0)
            return "";
        StringBuilder ret = new StringBuilder(items.get(0).toString());
        for (int i = 1; i < length; ++i) {
            ret.append(glue);
            ret.append(items.get(i).toString());
        }
        return ret.toString();
    }

    public void informMatchStarted() {
        btnFind.setEnabled(m_team != null);
        btnCancel.setEnabled(false);
        btnLoad.setEnabled(true);
    }

    private void cmbLadderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLadderActionPerformed
        Metagame metagame = (Metagame)cmbLadder.getSelectedItem();
        if (metagame != null) {
            String description = metagame.getDescription();
            int teamLength = metagame.getMaxTeamLength();
            List<String> banList = Arrays.asList(metagame.getBanList());
            Collections.sort(banList);
            
            lblDescription.setText("<html>" + description + "</html>");
            lblMode.setText(getFriendlyPartySize(metagame.getPartySize()));
            lblTeamLength.setText(teamLength + " Pokemon per team");
            listBans.setListData(banList.toArray());
            listClauses.setListData(metagame.getClauseList());
            ((TeamBox)panelSprites).setTeamLength(metagame.getMaxTeamLength());
        }
    }//GEN-LAST:event_cmbLadderActionPerformed

    private void btnFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFindActionPerformed
        int generationId = cmbGeneration.getSelectedIndex();
        int metagameId = cmbLadder.getSelectedIndex();
        if (metagameId < 0) {
            return;
        }
        
        boolean rated = chkRated.isSelected();
        m_link.queueTeam(generationId, metagameId, rated, m_team);
        btnFind.setEnabled(false);
        btnLoad.setEnabled(false);
        btnCancel.setEnabled(true);
    }//GEN-LAST:event_btnFindActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        int generationId = cmbGeneration.getSelectedIndex();
        int metagameId = cmbLadder.getSelectedIndex();
        if (metagameId < 0) {
            return;
        }

        boolean rated = chkRated.isSelected();
        m_link.cancelQueue(generationId, metagameId, rated);
        btnFind.setEnabled(true);
        btnLoad.setEnabled(true);
        btnCancel.setEnabled(false);
    }//GEN-LAST:event_btnCancelActionPerformed

    private void cmbGenerationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbGenerationActionPerformed
        Generation generation = (Generation)cmbGeneration.getSelectedItem();
        if (generation == null) {
            return;
        }
        
        cmbLadder.removeAllItems();
        for (Metagame metagame : generation.getMetagames()) {
            cmbLadder.addItem(metagame);
        }
    }//GEN-LAST:event_cmbGenerationActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnFind;
    private javax.swing.JButton btnLoad;
    private javax.swing.JCheckBox chkRated;
    private javax.swing.JComboBox cmbGeneration;
    private javax.swing.JComboBox cmbLadder;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblDescription;
    private javax.swing.JLabel lblMode;
    private javax.swing.JLabel lblTeamLength;
    private javax.swing.JList listBans;
    private javax.swing.JList listClauses;
    private javax.swing.JPanel panelSprites;
    // End of variables declaration//GEN-END:variables

}
