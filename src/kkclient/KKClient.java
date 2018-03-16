package kkclient;

import enums.FieldStateEnum;
import enums.ShapeEnum;
import interfaces.IKKServer;
import interfaces.IPlayer;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class KKClient {

    public static void main(String[] args) {
        System.setProperty("java.security.policy", "security.policy");
        System.setSecurityManager(new SecurityManager());

        try {
            //MyServerInt myRemoteObject = (MyServerInt) Naming.lookup("//82.139.138.91:1099/ABC");
            IKKServer remoteServer = (IKKServer) Naming.lookup("//localhost/ABC");
            
            System.out.println("Enter your nick:");
            Scanner s = new Scanner(System.in);
            Player player = new Player(s.nextLine().trim(), remoteServer);
            
            
            int result = remoteServer.addPlayer(player);
            if(result != 0){
                if(result == 1){
                    player.myShape = ShapeEnum.X;
                }else{
                    player.myShape = ShapeEnum.O;
                }
                System.out.println("Player had been added.");
            } else{
                System.out.println("Player cannot be added. There are already added two players to that game.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
