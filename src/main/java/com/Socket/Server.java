package com.Socket;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void startServer(){
        try{

            while(!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("A new Client is connected");

                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();

            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void closeServerSocket() {
        try{

            if(serverSocket != null) {
                serverSocket.close();
            }

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args){
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            Server server = new Server(serverSocket);
            server.startServer();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
