package kkclient;

import enums.FieldStateEnum;
import interfaces.IKKServer;
import interfaces.IPlayer;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javax.swing.JOptionPane;

public class Player extends UnicastRemoteObject implements IPlayer {

    private String name;
    public TicTacToe board;
    public boolean myTurn;
    public enums.ShapeEnum myShape;
    private FieldStateEnum[] boardStatus;
    public IKKServer ikkserver;

    Player() throws RemoteException {

    }

    Player(String nick, IKKServer ikkserver) throws RemoteException {
        this.name = nick;
        this.board = new TicTacToe(this);
        myTurn = false;
        myShape = null;
        board.setVisible(true);
        this.ikkserver = ikkserver;

        boardStatus = new FieldStateEnum[9];
        for (FieldStateEnum f : boardStatus) {
            f = FieldStateEnum.empty;
        }
    }

    @Override
    public void updateBoard(FieldStateEnum[] fieldState) throws RemoteException {
        for (int i = 0; i < 9; i++) {
            if (fieldState[i] == FieldStateEnum.empty) {
                board.button[i].setText("");
            } else {
                board.button[i].setText(fieldState[i].toString());
            }
        }
    }

    @Override
    public void unlockPlayer() throws RemoteException {
        this.myTurn = true;
    }

    void updateBoardAfterClick() throws RemoteException {
        for (int i = 0; i < 9; i++) {
            if (board.button[i].getText().equals("")) {
                boardStatus[i] = enums.FieldStateEnum.empty;
            } else {
                boardStatus[i] = enums.FieldStateEnum.valueOf(board.button[i].getText());
            }
        }

        ikkserver.updateBoard(boardStatus);
    }

    @Override
    public void gameResult(String info) throws RemoteException {

        System.out.println("info: " + info);

        try {
            System.in.read();
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("waiting for second player accept..");
        ikkserver.playAgain();

    }

    @Override
    public String getName() throws RemoteException {
        return this.name;
    }

}
