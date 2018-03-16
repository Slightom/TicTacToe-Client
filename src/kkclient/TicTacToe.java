package kkclient;

import enums.FieldStateEnum;
import interfaces.IKKServer;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;

public class TicTacToe extends JFrame implements Serializable {

    public JButton[] button;
    Player player;
    

    public TicTacToe() throws RemoteException {
        this.player = new Player();       
    }

    public TicTacToe(Player player) throws RemoteException {
        this.player = player;       
        initComponents();
    }

    public void buttonActionPerformed(ActionEvent e) throws RemoteException {
        JButton btn = (JButton) e.getSource();
        if (player.myShape != null && player.myTurn && btn.getText().equals("")) {
            btn.setText(player.myShape.toString());           
            player.myTurn = false;
            player.updateBoardAfterClick();
        }
    }

    public void initComponents() throws RemoteException {
        button = new JButton[9];
        for (int i = 0; i < 9; i++) {
            button[i] = new JButton();
        }

        //======== this ========
        setTitle("KiK - " + player.getName());
        Container contentPane = getContentPane();

        for (JButton b : button) {
            b.setText("");
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        buttonActionPerformed(e);
                    } catch (RemoteException ex) {
                        Logger.getLogger(TicTacToe.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup()
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(button[0], GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(button[1], GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(button[2], GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(button[3], GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(button[4], GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(button[5], GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(contentPaneLayout.createSequentialGroup()
                                                .addComponent(button[6], GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(button[7], GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(button[8], GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(11, Short.MAX_VALUE))
        );
        contentPaneLayout.setVerticalGroup(
                contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(button[0], GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button[1], GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button[2], GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(button[3], GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button[4], GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button[5], GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(button[6], GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button[7], GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button[8], GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(15, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
    }

}
