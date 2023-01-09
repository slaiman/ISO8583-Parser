package com.server;

import com.parser.ISO8683Parser;
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
                    serviceCall(splts[i]);
                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public void serviceCall(String msg) {

        try {
            Map<Integer, String> output = new ISO8683Parser().parseMessage(msg);
            //print the field values
            for (Map.Entry<Integer, String> field : output.entrySet()) {
                System.out.println(field.getKey() + ": " + field.getValue());
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }

    }
}
