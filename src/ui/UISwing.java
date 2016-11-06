/*
 * The MIT License
 *
 * Copyright 2016 Diana.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package ui;

import business.GameEngine;
import data.Player;
import data.Table;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/**
 *
 * @author Diana
 */
public class UISwing extends javax.swing.JFrame implements UI {
    
    private int menu;
    PlayerInfoPanel[] playersPanels;

    /**
     * Creates new form GUI1
     */
    public UISwing() {
        try {
            initComponents();
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            
            this.setBackground(Color.black);
            SwingUtilities.updateComponentTreeUI(this);
            //this.setIconImage(new ImageIcon(getClass().getResource("/ui/resources/poker.png")));
            this.pack();
            this.setVisible(true);
            playersPanels = new PlayerInfoPanel[8];
            playersPanels[0] = userInfoPanel.getUserPanel();
            playersPanels[1] = player1InfoPanel;
            playersPanels[2] = player2InfoPanel;
            playersPanels[3] = player3InfoPanel;
            playersPanels[4] = player4InfoPanel;
            playersPanels[5] = player5InfoPanel;
            playersPanels[6] = player6InfoPanel;
            playersPanels[7] = player7InfoPanel;
            tableHandLabel.setFont(new Font("Helvetica",Font.BOLD ,32));
        } catch (Exception e) {
            printError(e);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        exitButton = new javax.swing.JButton();
        cardPanel = new javax.swing.JPanel();
        welcomePanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        helpButton = new javax.swing.JButton();
        handsButton = new javax.swing.JButton();
        infoButton = new javax.swing.JButton();
        roundMenuButton = new javax.swing.JButton();
        welcomeLabel = new javax.swing.JLabel();
        roundPanel = new javax.swing.JPanel();
        startRoundButton = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        logTextArea = new javax.swing.JTextArea();
        player1InfoPanel = new ui.PlayerInfoPanel();
        player3InfoPanel = new ui.PlayerInfoPanel();
        player4InfoPanel = new ui.PlayerInfoPanel();
        player5InfoPanel = new ui.PlayerInfoPanel();
        player2InfoPanel = new ui.PlayerInfoPanel();
        player6InfoPanel = new ui.PlayerInfoPanel();
        player7InfoPanel = new ui.PlayerInfoPanel();
        userInfoPanel = new ui.RoundMenuPanel();
        tableHandPanel = new javax.swing.JPanel();
        tableHandLabel = new javax.swing.JLabel();
        roundStatusLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("UN'Hold Em");
        setBackground(new java.awt.Color(0, 0, 0));
        setMaximumSize(new java.awt.Dimension(1000, 500));
        setMinimumSize(new java.awt.Dimension(600, 400));
        setPreferredSize(new java.awt.Dimension(800, 500));
        setResizable(false);

        mainPanel.setBackground(new java.awt.Color(0, 0, 0));
        mainPanel.setMaximumSize(new java.awt.Dimension(800, 500));
        mainPanel.setPreferredSize(new java.awt.Dimension(800, 500));

        exitButton.setBackground(new java.awt.Color(255, 0, 0));
        exitButton.setFont(new java.awt.Font("Lucida Calligraphy", 1, 18)); // NOI18N
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/resources/Users-Exit-icon.png"))); // NOI18N
        exitButton.setText("Exit");
        exitButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.red, java.awt.Color.red, java.awt.Color.red, java.awt.Color.red));
        exitButton.setContentAreaFilled(false);
        exitButton.setOpaque(true);
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        cardPanel.setMaximumSize(new java.awt.Dimension(700, 600));
        cardPanel.setMinimumSize(new java.awt.Dimension(500, 400));
        cardPanel.setPreferredSize(new java.awt.Dimension(700, 400));
        cardPanel.setLayout(new java.awt.CardLayout());

        welcomePanel.setBackground(new java.awt.Color(255, 255, 255));
        welcomePanel.setMaximumSize(new java.awt.Dimension(500, 600));
        welcomePanel.setMinimumSize(new java.awt.Dimension(500, 500));
        welcomePanel.setPreferredSize(new java.awt.Dimension(500, 600));

        titleLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/resources/UN Hold'em LOGO.png"))); // NOI18N
        titleLabel.setMaximumSize(new java.awt.Dimension(300, 250));

        helpButton.setBackground(new java.awt.Color(0, 153, 0));
        helpButton.setFont(new java.awt.Font("Lucida Calligraphy", 1, 14)); // NOI18N
        helpButton.setForeground(new java.awt.Color(255, 255, 255));
        helpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/resources/Very-Basic-Help-icon.png"))); // NOI18N
        helpButton.setText("Never Played Poker Before?");
        helpButton.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(0, 102, 0), new java.awt.Color(0, 102, 0), new java.awt.Color(0, 102, 0), new java.awt.Color(0, 102, 0)));
        helpButton.setContentAreaFilled(false);
        helpButton.setOpaque(true);
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        handsButton.setBackground(new java.awt.Color(204, 102, 0));
        handsButton.setFont(new java.awt.Font("Lucida Calligraphy", 1, 14)); // NOI18N
        handsButton.setForeground(new java.awt.Color(255, 255, 255));
        handsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/resources/poker.png"))); // NOI18N
        handsButton.setText("Hands");
        handsButton.setContentAreaFilled(false);
        handsButton.setOpaque(true);
        handsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                handsButtonActionPerformed(evt);
            }
        });

        infoButton.setBackground(new java.awt.Color(0, 102, 204));
        infoButton.setFont(new java.awt.Font("Lucida Calligraphy", 1, 14)); // NOI18N
        infoButton.setForeground(new java.awt.Color(255, 255, 255));
        infoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ui/resources/sign-info-icon.png"))); // NOI18N
        infoButton.setText("Info");
        infoButton.setContentAreaFilled(false);
        infoButton.setOpaque(true);
        infoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                infoButtonActionPerformed(evt);
            }
        });

        roundMenuButton.setText("Show Round Menu");
        roundMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                roundMenuButtonActionPerformed(evt);
            }
        });

        welcomeLabel.setBackground(new java.awt.Color(0, 0, 0));
        welcomeLabel.setFont(new java.awt.Font("Lucida Calligraphy", 1, 24)); // NOI18N
        welcomeLabel.setForeground(new java.awt.Color(255, 255, 255));
        welcomeLabel.setText("Welcome to ...");

        javax.swing.GroupLayout welcomePanelLayout = new javax.swing.GroupLayout(welcomePanel);
        welcomePanel.setLayout(welcomePanelLayout);
        welcomePanelLayout.setHorizontalGroup(
            welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, welcomePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(welcomePanelLayout.createSequentialGroup()
                        .addGroup(welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(helpButton)
                            .addComponent(handsButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(welcomeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(welcomePanelLayout.createSequentialGroup()
                                .addComponent(roundMenuButton)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(welcomePanelLayout.createSequentialGroup()
                        .addComponent(infoButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        welcomePanelLayout.setVerticalGroup(
            welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(welcomePanelLayout.createSequentialGroup()
                .addGroup(welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(welcomePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(welcomePanelLayout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(roundMenuButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(welcomePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(welcomePanelLayout.createSequentialGroup()
                                .addComponent(helpButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(handsButton)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(infoButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cardPanel.add(welcomePanel, "WELCOMEPANEL");

        roundPanel.setBackground(new java.awt.Color(0, 0, 0));
        roundPanel.setMaximumSize(new java.awt.Dimension(700, 600));
        roundPanel.setMinimumSize(new java.awt.Dimension(700, 400));
        roundPanel.setPreferredSize(new java.awt.Dimension(700, 600));
        roundPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        startRoundButton.setText("Start Round");
        startRoundButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startRoundButtonActionPerformed(evt);
            }
        });
        roundPanel.add(startRoundButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        logTextArea.setColumns(35);
        logTextArea.setRows(15);
        scrollPane.setViewportView(logTextArea);

        roundPanel.add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(404, 275, -1, 90));
        roundPanel.add(player1InfoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 189, -1, 70));
        roundPanel.add(player3InfoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, 70));
        roundPanel.add(player4InfoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 41, -1, 70));
        roundPanel.add(player5InfoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 41, -1, 70));
        roundPanel.add(player2InfoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 115, -1, 70));
        roundPanel.add(player6InfoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 115, -1, 70));
        roundPanel.add(player7InfoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 190, -1, 70));
        roundPanel.add(userInfoPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 275, -1, -1));

        tableHandPanel.setMaximumSize(new java.awt.Dimension(300, 100));
        tableHandPanel.setPreferredSize(new java.awt.Dimension(300, 100));

        tableHandLabel.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout tableHandPanelLayout = new javax.swing.GroupLayout(tableHandPanel);
        tableHandPanel.setLayout(tableHandPanelLayout);
        tableHandPanelLayout.setHorizontalGroup(
            tableHandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tableHandPanelLayout.createSequentialGroup()
                .addGroup(tableHandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(tableHandPanelLayout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(roundStatusLabel))
                    .addGroup(tableHandPanelLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(tableHandLabel)))
                .addContainerGap(167, Short.MAX_VALUE))
        );
        tableHandPanelLayout.setVerticalGroup(
            tableHandPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tableHandPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(roundStatusLabel)
                .addGap(28, 28, 28)
                .addComponent(tableHandLabel)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        roundPanel.add(tableHandPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(198, 146, -1, -1));

        cardPanel.add(roundPanel, "card4");

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(exitButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addGap(0, 77, Short.MAX_VALUE)
                .addComponent(cardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(exitButton)
                .addGap(5, 5, 5)
                .addComponent(cardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        printExit();
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        JOptionPane.showMessageDialog(rootPane, "Necesitas Ayuda?");
    }//GEN-LAST:event_helpButtonActionPerformed

    private void handsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_handsButtonActionPerformed
        JOptionPane.showMessageDialog(rootPane, "Las manos son...");
    }//GEN-LAST:event_handsButtonActionPerformed

    private void infoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_infoButtonActionPerformed
        JOptionPane.showMessageDialog(rootPane, "Desarrollado por..");
    }//GEN-LAST:event_infoButtonActionPerformed

    private void roundMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_roundMenuButtonActionPerformed
        ((CardLayout) (cardPanel.getLayout())).next(cardPanel);
    }//GEN-LAST:event_roundMenuButtonActionPerformed

    private void startRoundButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startRoundButtonActionPerformed
        startRoundButton.setEnabled(false);
        GameEngine.getInstance().startRound();
    }//GEN-LAST:event_startRoundButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UISwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UISwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UISwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UISwing.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UISwing().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cardPanel;
    private javax.swing.JButton exitButton;
    private javax.swing.JButton handsButton;
    private javax.swing.JButton helpButton;
    private javax.swing.JButton infoButton;
    private javax.swing.JTextArea logTextArea;
    private javax.swing.JPanel mainPanel;
    private ui.PlayerInfoPanel player1InfoPanel;
    private ui.PlayerInfoPanel player2InfoPanel;
    private ui.PlayerInfoPanel player3InfoPanel;
    private ui.PlayerInfoPanel player4InfoPanel;
    private ui.PlayerInfoPanel player5InfoPanel;
    private ui.PlayerInfoPanel player6InfoPanel;
    private ui.PlayerInfoPanel player7InfoPanel;
    private javax.swing.JButton roundMenuButton;
    private javax.swing.JPanel roundPanel;
    private javax.swing.JLabel roundStatusLabel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JButton startRoundButton;
    private javax.swing.JLabel tableHandLabel;
    private javax.swing.JPanel tableHandPanel;
    private javax.swing.JLabel titleLabel;
    private ui.RoundMenuPanel userInfoPanel;
    private javax.swing.JLabel welcomeLabel;
    private javax.swing.JPanel welcomePanel;
    // End of variables declaration//GEN-END:variables

    @Override
    public String askMsg(String question) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void printWelcome() {
        JOptionPane.showMessageDialog(this.rootPane, "Hola", "Welcome to UN Hold'em", JOptionPane.PLAIN_MESSAGE);
    }
    
    @Override
    public void printError(Exception ex) {
        ex.printStackTrace();
        String error = ex.getMessage();
        JOptionPane.showMessageDialog(rootPane,
                error,
                error,
                JOptionPane.ERROR_MESSAGE);
    }
    
    @Override
    public void printHelp() {
        String msg = "Poker Hold'em is a game of bets in which each player has two cards and the goal is to assemble the \n"
                + " best set of five cards between yours and the five comunitary cards.\n"
                + "The comunitary cards are showed to all the players in this way: After each round of bets, a card \n"
                + " of the deck is 'burned'(discarded)\n"
                + "and three cards(first round) or one card(other rounds) are showed until five cards were at sight.\n"
                + "You can match the bet, backing out, or check (pass) on your turn\n"
                + "bet more if you have a good hand in the game and win more points ";
        JOptionPane.showMessageDialog(rootPane, msg, "Help", JOptionPane.PLAIN_MESSAGE);
    }
    
    @Override
    public void printCommands() {
    }
    
    @Override
    public void printHands() {
    }
    
    @Override
    public void printExit() {
        JOptionPane.showMessageDialog(null, "Hasta luego..");
    }
    
    @Override
    public void printInfo() {
    }
    
    @Override
    public void printRoundStandings(Table table, int winners) {
        String out = ("Round Standings(from best to worst):\n");
        for (Player plyr : table.getPlayers()) {
            out += ("Player " + plyr.getId() + " - " + plyr.getHand().toString() + "\n");
        }
        out += ("Winners:");
        for (int i = 0; i < winners; i++) {
            out += ("Player " + table.getPlayer(i).getId() + " Wins");
        }
        JOptionPane.showMessageDialog(null, out);
    }
    
    @Override
    public void printStandings(Table table) {
        String out = ("Tournament Standings: \n");
        for (int i = 0; i < 8; i++) {
            if (table.getSeats()[i].getPlayer() != null) {
                out += (table.getSeats()[i] + "\n");
            }
        }
        JOptionPane.showMessageDialog(null, out);
    }
    
    @Override
    public void printUser(Player user) {
        userInfoPanel.setPlayer(user);
        userInfoPanel.repaint();
    }
    
    @Override
    public void printMsg(String msg) {
        synchronized (this) {
            logTextArea.append(msg);
            logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
        }
    }
    
    @Override
    public void printMenuOption(String menuName, String msg, String[] options) {
        menu = 1 + JOptionPane.showOptionDialog(null,
                msg,
                menuName,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }
    
    @Override
    public int askInt(String question) {
        //incompleto
        int result = Integer.parseInt(JOptionPane.showInputDialog(question));
        return result;
    }
    
    @Override
    public void notifyRoundMenu() {
        userInfoPanel.enableButtons(true);
        printMsg("Your turn!!");
        if (GameEngine.getInstance().getRoundThread().getTable().isOpenBet()) {
            userInfoPanel.disableCheck();
        }
    }
    
    @Override
    public void notifyMainMenu() {
        
    }
    
    @Override
    public void printTable(Table table,String status) {
        roundStatusLabel.setText(status);
        tableHandLabel.setText("" + table.getTableHand());
        userInfoPanel.setPlayer(table.getPlayer(0));
        userInfoPanel.repaint();
        for (int i = 1; i < 8; i++) {
            if (table.getSeat(i).getPlayer() != null) {
                playersPanels[i].setPlayer(table.getSeat(i).getPlayer());
                playersPanels[i].repaint();
            }
        }
    }
}
