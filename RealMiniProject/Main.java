package RealMiniProject;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static Socket socket;
    public static ObjectInputStream inputStream;
    private static Connection connection;
    public static void main(String[]args){
        try{
            ServerSocket server = new ServerSocket(2000);
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/jdbc_samples?useUnicode=true&serverTimezone=UTC","root", ""
            );
            System.out.println("CONNECTED MYSQL");
            while (true){
                Socket socket = server.accept();
                ClientHandler socketThread = new ClientHandler(socket, connection);
                socketThread.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}