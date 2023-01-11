package com.server;

import com.parser.ISO8683Parser;
import com.entities.ISOField;
import org.jpos.iso.packager.GenericPackager;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class Server {

    InetAddress IpAddress;
    int Port = 6238;
    ServerSocket serverSocket;
    private GenericPackager packager;

    public Server(){}

    public void MessageListener(){
        try {
            IpAddress = Inet4Address.getLocalHost();
            serverSocket = new ServerSocket(this.Port,1, this.IpAddress);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        int it = 0;
        while(true) {
            try {
                Socket clientSocket = serverSocket.accept();

                // Create a BufferedReader to read data from the client
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // Read a line of data from the client
                String input = bufferedReader.readLine();

                String[] splts = input.split(";");
                for(int i = 0; i < splts.length; i++) {

                    System.out.println(splts[i]);
                    serviceCall(splts[i], clientSocket);
                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public void serviceCall(String msg, Socket clientSocket) {

        try {
            ISO8683Parser isoParser = new ISO8683Parser();
            Map<Integer, ISOField> data = isoParser.parseMessage(msg);

            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());

            oos.writeObject(data);
            oos.flush();

            oos.writeObject(isoParser.getMit());
            //oos.flush();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
