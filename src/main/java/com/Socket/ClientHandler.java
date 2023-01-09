package com.Socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable {

   public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
   private Socket socket;
   private BufferedReader bufferedReader;
   private BufferedWriter bufferedWriter;
   private String clientUsername;

    public ClientHandler(Socket socket) {
        try {
            this.socket = socket;
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.clientUsername = bufferedReader.readLine();
            clientHandlers.add(this);
            broadcastMessage("Server "+ clientUsername + " has entered the chat");

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        String messageFromClient;

        while(socket.isConnected()) {
            try{
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void broadcastMessage(String messagetoSend){
        for(ClientHandler clientHandler : clientHandlers) {
            try{
                if(!clientHandler.clientUsername.equals(clientUsername)) {
                    clientHandler.bufferedWriter.write(messagetoSend);
                    clientHandler.bufferedWriter.newLine();
                    clientHandler.bufferedWriter.flush();
                }

            } catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public void removeClientHandler(){
        clientHandlers.remove(this);
        broadcastMessage("SERVER " + clientUsername + " has left the chat");
    }

    public void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        removeClientHandler();
        try{

            if(bufferedReader != null) {
                bufferedReader.close();
            }

            if(bufferedWriter != null) {
                bufferedWriter.close();
            }
            if(socket != null) {
                socket.close();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

}
