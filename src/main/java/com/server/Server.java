package com.server;

import com.parser.ISO8683Parser;
import com.entities.ISOField;

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
            isoParser.parseMessageValues(isoParser);
            checkScenario(isoParser, clientSocket, msg);

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

        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void checkScenario(ISO8683Parser isoParser, Socket clientSocket, String msg) {
        try{
            String mti = msg.substring(0, 4);
            String response = "";
            Map<Integer,ISOField> data = isoParser.getDataElements();
            if(mti.equals("0100") || mti.equals("0200")){

                if(mti.equals("0100")) response = "0110";
                else if(mti.equals("0200")) response = "0210";

                String output;
                output = response + msg.substring(4, msg.length());

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                bufferedWriter.write(output);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            else if(mti.equals("0101") || mti.equals("0201")){
                if(mti.equals("0101")) response = "0110";
                else if(mti.equals("0201")) response = "0210";

                String output;
                output = "Data: "+response + msg.substring(4, msg.length());

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                bufferedWriter.write(output);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                String type = "Open Account Relationship Transaction";
                bufferedWriter.write(type);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            //Negative File Update Messages UseCases
            else if(mti.equals("0300") || mti.equals("0302")) {
                if(mti.equals("0300"))response = "0310";
                else response = "0312";

                String output;
                output = "Data: "+response + msg.substring(4, msg.length());

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                bufferedWriter.write(output);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                String type = "Negative File Update";
                bufferedWriter.write(type);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            //Authorization Processor File Update Messages UseCases
            else if(mti.equals("0382")) {
                response = "0392";
                String output;
                output = "Data: "+response + msg.substring(4, msg.length());

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                bufferedWriter.write(output);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                String type = "Authorization Processor File Update";
                bufferedWriter.write(type);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            //Issuer Reconciliation Advice Messages UseCases
            else if(mti.equals("0520")) {
                response = "0530";
                String output;
                output = "Data: "+response + msg.substring(4, msg.length());

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                bufferedWriter.write(output);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                String type = "Acquirer Reconciliation Advice";
                bufferedWriter.write(type);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            else if(mti.equals("0522")) {
                response = "0532";
                String output;
                output = "Data: "+response + msg.substring(4, msg.length());

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                bufferedWriter.write(output);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                String type = "Issuer Reconciliation Advice";
                bufferedWriter.write(type);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            else if(mti.equals("0600")){
                response = "0610";
                String output;
                output = "Data: "+response + msg.substring(4, msg.length());

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                bufferedWriter.write(output);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                String type = "no value is passed";
                if(data.containsKey(70) && data.get(70) != null) {
                    ISOField field = data.get(70);
                    if(field.value.equals("800") || field.value.equals("005") || field.value.equals("601")){
                        type = "Administrative Messages";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                }
            }
            //Authorization messages
            else if(mti.equals("0800")) {
                response = "0810";
                String output;
                output = "Data: "+response + msg.substring(4, msg.length());

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                bufferedWriter.write(output);
                bufferedWriter.newLine();
                bufferedWriter.flush();

                String type = "no type is detected";
                if(data.containsKey(70) && data.get(70) != null) {
                    ISOField field = data.get(70);
                    //Network management messages UseCases
                    if(field.value.equals("001") || field.value.equals("071") || field.value.equals("061")){
                        type = "Signon";
                    }
                    else if(field.value.equals("002") || field.value.equals("072") || field.value.equals("062")){
                        type = "Signoff";
                    }
                    else if(field.value.equals("101") || field.value.equals("171") || field.value.equals("161")){
                        type = "Key Change";
                    }
                    else if(field.value.equals("180") || field.value.equals("181") || field.value.equals("191")){
                        type = "Key Change Request";
                    }
                    else if(field.value.equals("201") || field.value.equals("271") || field.value.equals("261")){
                        type = "Cutoff Initiation";
                    }
                    else if(field.value.equals("281") || field.value.equals("282")){
                        type = "Terminal Cutoff/Cardholder Application Cutoff";
                    }
                    else if(field.value.equals("202") || field.value.equals("272") || field.value.equals("262")){
                        type = "Cutoff Completion";
                    }
                    else if(field.value.equals("301") || field.value.equals("371") || field.value.equals("361")){
                        type = "EchoTest";
                    }
                }
                bufferedWriter.write(type);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
