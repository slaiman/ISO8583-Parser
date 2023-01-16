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

                //call the service of parsing
                serviceCall(input, clientSocket);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    public void serviceCall(String msg, Socket clientSocket) {

        try {
            ISO8683Parser isoParser = new ISO8683Parser();
            isoParser.parseMessage(msg);
            sendToClient(isoParser, clientSocket, msg);

        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public void sendToClient(ISO8683Parser isoParser, Socket clientSocket, String msg) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());

            oos.writeObject(isoParser.getDataElements());
            oos.flush();

            oos.writeObject(isoParser.getMti());
            oos.flush();

           String mti = msg.substring(0, 4);
           Map<Integer,ISOField> data = isoParser.getDataElements();

            if(mti.equals("0800")) {
               mti = "0810";
               String output;
               output = mti + msg.substring(4, msg.length());

               BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
               bufferedWriter.write(output);
               bufferedWriter.newLine();
               bufferedWriter.flush();


               String type = "no value is passed";
               if(data.containsKey(70)) {
                   ISOField field = data.get(70);
                   //Network management messages UseCases
                   if(field.value.equals("001") || field.value.equals("071") || field.value.equals("061")){
                       type = "Signon";
                   }
                   else if(field.value.equals("002") || field.value.equals("072") || field.value.equals("062")){
                       type = "Signoff";
                   }
                   else if(field.value.equals("301") || field.value.equals("371") || field.value.equals("361")){
                       type = "EchoTest";
                   }
               }
               bufferedWriter.write(type);
               bufferedWriter.newLine();
               bufferedWriter.flush();
           }
           //Issuer Reconciliation Advice Messages UseCases
           else if(mti.equals("0520")) {
                mti = "0530";

           }
            //Negative File Update Messages UseCases
            else if(mti.equals("0300") || mti.equals("0302")) {
                if(mti.equals("0300"))mti = "0310";
                else mti = "0312";

            }
            //Authorization Processor File Update Messages UseCases
            else if(mti.equals("0382")) {
                mti = "0392";

            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
