package com.server;

import com.parser.DataFilter;
import com.parser.ISO8583Encoder;
import com.parser.ISO8683Decoder;
import com.entities.ISOField;
import com.parser.Initializer;

import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

public class Server {

    InetAddress IpAddress;
    int Port = 6238;
    ServerSocket serverSocket;


    public Server(){}

    protected void MessageListener(){
        try {
            IpAddress = Inet4Address.getLocalHost();
            serverSocket = new ServerSocket(this.Port,1, this.IpAddress);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        //this while loop is for accepting multiple client socket for connection
        while(true) {
            try {
                Socket clientSocket = serverSocket.accept();

                // Create a BufferedReader to read data from the client
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // Read a line of data from the client
                String input = bufferedReader.readLine();

                //call the service of parsing
                parserCaller(input, clientSocket);
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }

    private void parserCaller(String msg, Socket clientSocket) {

        try {
            ISO8683Decoder isoParser = new ISO8683Decoder();
            isoParser.parseMessage(msg);
            sendToClient(isoParser, clientSocket, msg);
            isoParser.parseMessageValues(isoParser);
            checkScenario(isoParser, clientSocket, msg);

        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void sendToClient(ISO8683Decoder isoParser, Socket clientSocket, String msg) {
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

    private void checkScenario(ISO8683Decoder isoParser, Socket clientSocket, String msg) {
        try{
            String mti = msg.substring(0, 4);

            String response = "";

            Map<Integer,ISOField> data = isoParser.getDataElements();
            Map<Integer,ISOField> filteredData = new HashMap();

            if(mti.equals("0100") || mti.equals("0101") || mti.equals("0120") || mti.equals("0121")){

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                String rawData = "";
                String output;

                if(mti.equals("0100")) {
                    String type = "Authorization Request";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    TreeSet<Integer> output100 = Initializer.initializeData100();
                    filteredData = DataFilter.filterData(data,output100);
                    ISO8583Encoder iso8583Encoder = new ISO8583Encoder();
                    rawData = iso8583Encoder.encodeMessage(filteredData);
                    response = "0110";
                }
                else if(mti.equals("0101")) {
                    String type = "Check Verification/Guarantee Request";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    TreeSet<Integer> output101 = Initializer.initializeData101();
                    filteredData = DataFilter.filterData(data,output101);
                    ISO8583Encoder iso8583Encoder = new ISO8583Encoder();
                    rawData = iso8583Encoder.encodeMessage(filteredData);
                    response = "0110";
                }
                else if(mti.equals("0121") || mti.equals("0120")) {
                    String type = "Authorization Advice";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    TreeSet<Integer> output120 = Initializer.initializeData120();
                    filteredData = DataFilter.filterData(data,output120);
                    ISO8583Encoder iso8583Encoder = new ISO8583Encoder();
                    rawData = iso8583Encoder.encodeMessage(filteredData);
                    response = "0130";
                }

                output = "Content: "+response + rawData;

                bufferedWriter.write(output);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            else if(mti.equals("0200") || mti.equals("0201") || mti.equals("0220")|| mti.equals("0221")){

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                String output;
                String rawData = "";

                if(mti.equals("0200") || mti.equals("0201")) {
                    String type = "Financial Transaction Request";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    TreeSet<Integer> output200 = Initializer.initializeData200();
                    filteredData = DataFilter.filterData(data,output200);
                    ISO8583Encoder iso8583Encoder = new ISO8583Encoder();
                    rawData = iso8583Encoder.encodeMessage(filteredData);
                    response = "0210";
                }
                else if(mti.equals("0220") || mti.equals("0221")) {
                    String type = "Financial Transaction Advice";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    TreeSet<Integer> output220 = Initializer.initializeData220();
                    filteredData = DataFilter.filterData(data,output220);
                    ISO8583Encoder iso8583Encoder = new ISO8583Encoder();
                    rawData = iso8583Encoder.encodeMessage(filteredData);
                    response = "0230";
                }

                output = "Content: "+response + rawData;

                bufferedWriter.write(output);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            else if(mti.equals("0300") || mti.equals("0302")|| mti.equals("0382")){

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                String rawData = "";
                String output;

                if(mti.equals("0300")) {
                    String type = "Acquirer File Update Request";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    TreeSet<Integer> output300 = Initializer.initializeData300();
                    filteredData = DataFilter.filterData(data,output300);
                    ISO8583Encoder iso8583Encoder = new ISO8583Encoder();
                    rawData = iso8583Encoder.encodeMessage(filteredData);
                    response = "0310";
                }
                else if(mti.equals("0302")) {
                    String type = "Card Issuer File Update Request";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    TreeSet<Integer> output302 = Initializer.initializeData302();
                    filteredData = DataFilter.filterData(data,output302);
                    ISO8583Encoder iso8583Encoder = new ISO8583Encoder();
                    rawData = iso8583Encoder.encodeMessage(filteredData);
                    response = "0312";
                }
                else if(mti.equals("0382")) {
                    String type = "Card Issuer File Update Request";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    TreeSet<Integer> output382 = Initializer.initializeData382();
                    filteredData = DataFilter.filterData(data,output382);
                    ISO8583Encoder iso8583Encoder = new ISO8583Encoder();
                    rawData = iso8583Encoder.encodeMessage(filteredData);
                    response = "0392";
                }

                output = "Content: "+response + rawData;

                bufferedWriter.write(output);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            else if(mti.equals("0420") || mti.equals("0421")) {

                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                String rawData = "";
                String output;

                if(mti.equals("0420") || mti.equals("0421")) {
                    String type = "Acquirer Reversal Advice";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    TreeSet<Integer> output420 = Initializer.initializeData420();
                    filteredData = DataFilter.filterData(data,output420);
                    ISO8583Encoder iso8583Encoder = new ISO8583Encoder();
                    rawData = iso8583Encoder.encodeMessage(filteredData);
                    response = "0430";
                }
                output = "Content: "+response + rawData;

                bufferedWriter.write(output);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            else if(mti.equals("0520") || mti.equals("0521") || mti.equals("0522") || mti.equals("0523")) {

                String rawData = "";
                String output;
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                if(mti.equals("0520") || mti.equals("0521")) {
                    String type = "Acquirer Reconciliation Advice";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    TreeSet<Integer> output520 = Initializer.initializeData520();
                    filteredData = DataFilter.filterData(data,output520);
                    ISO8583Encoder iso8583Encoder = new ISO8583Encoder();
                    rawData = iso8583Encoder.encodeMessage(filteredData);
                    response = "0530";
                }
                else if(mti.equals("0522") || mti.equals("0523")) {
                    String type = "Issuer Reconciliation Advice";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    TreeSet<Integer> output522 = Initializer.initializeData522();
                    filteredData = DataFilter.filterData(data,output522);
                    ISO8583Encoder iso8583Encoder = new ISO8583Encoder();
                    rawData = iso8583Encoder.encodeMessage(filteredData);
                    response = "0532";
                }
                output = "Content: "+response + rawData;

                bufferedWriter.write(output);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            else if(mti.equals("0600") || mti.equals("0601") || mti.equals("0620")){
                String rawData = "";
                String output;
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                if(mti.equals("0600") || mti.equals("0601")) {
                    String type = "no value is passed";
                    if(data.containsKey(70) && data.get(70) != null) {
                        ISOField field = data.get(70);
                        if(field.value.equals("0601")){
                            type = "Administrative Risk Message";
                            bufferedWriter.write(type);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();

                            TreeSet<Integer> output600601 = Initializer.initializeData600601();
                            filteredData = DataFilter.filterData(data,output600601);
                            ISO8583Encoder iso8583Encoder = new ISO8583Encoder();
                            rawData = iso8583Encoder.encodeMessage(filteredData);
                            response = "0610";

                        }
                        else if(field.value.equals("0800")){
                            type = "Electronic Mail Request Message";
                            bufferedWriter.write(type);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();

                            TreeSet<Integer> output600800 = Initializer.initializeData600800();
                            filteredData = DataFilter.filterData(data,output600800);
                            ISO8583Encoder iso8583Encoder = new ISO8583Encoder();
                            rawData = iso8583Encoder.encodeMessage(filteredData);
                            response = "0610";
                        }
                        else if(field.value.equals("0810") || field.value.equals("0811") || field.value.equals("0812") || field.value.equals("0821") || field.value.equals("0822")){
                            type = "Administrative Request";
                            bufferedWriter.write(type);
                            bufferedWriter.newLine();
                            bufferedWriter.flush();

                            TreeSet<Integer> output600810 = Initializer.initializeData600810();
                            filteredData = DataFilter.filterData(data,output600810);
                            ISO8583Encoder iso8583Encoder = new ISO8583Encoder();
                            rawData = iso8583Encoder.encodeMessage(filteredData);
                            response = "0610";
                        }
                    }

                }
                output = "Content: "+response + rawData;
                bufferedWriter.write(output);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            //Network Management messages
            else if(mti.equals("0800") || mti.equals("0820")) {
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                String rawData = "";
                String output;
                String type = "";

                if(mti.equals("0800") && data.get(70).value.equals("001")) {
                    type = "Sign on, both issuer and acquirer";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("002")){
                    type = "Sign off, both issuer and acquirer";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("005")){
                    type = "Special instructions (message to network operator)";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("0061")){
                    type = "Sign on as issuer";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("0062")){
                    type = "Sign off as issuer";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("0071")){
                    type = "Sign on as acquirer";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("0072")){
                    type = "Sign off as acquirer";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("0101")){
                    type = "Key change, both issuer and acquirer";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("0161")){
                    type = "Issuer key change";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("0171")){
                    type = "Acquirer key change";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("0180")){
                    type = "Issuer/acquirer key change request";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("0181")){
                    type = "Acquirer key change request";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("0191")){
                    type = "Issuer key change request";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("0201")){
                    type = "Initiate cutoff, issuer and acquirer";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("0202")){
                    type = "Cutoff complete, issuer and acquirer";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("0261")){
                    type = "Initiate cutoff as issuer";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("0262")){
                    type = "Cutoff complete as issuer";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("0271")){
                    type = "Initiate cutoff as acquirer";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("0272")){
                    type = "Cutoff complete as acquirer";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("0281")){
                    type = "Terminal cutoff";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("0282")){
                    type = "Cardholder application cutoff";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("0301")){
                    type = "Echo test (reply required handshake)";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0820") && data.get(70).value.equals("0301")){
                    type = "Echo test (protocol acknowledgment handshake))";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("0361")){
                    type = "Issuer echo test (reply required)";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0820") && data.get(70).value.equals("0361")){
                    type = "Issuer echo test (protocol acknowledgment)";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0800") && data.get(70).value.equals("0371")){
                    type = "Acquirer echo test (reply required)";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                else if(mti.equals("0820") && data.get(70).value.equals("0371")){
                    type = "Acquirer echo test (protocol acknowledgment)";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                TreeSet<Integer> output800 = Initializer.initializeData800();
                filteredData = DataFilter.filterData(data,output800);
                ISO8583Encoder iso8583Encoder = new ISO8583Encoder();
                rawData = iso8583Encoder.encodeMessage(filteredData);
                response = "0810";

                output = "Content: "+response + rawData;
                bufferedWriter.write(output);
                bufferedWriter.newLine();
                bufferedWriter.flush();
            }
            else{
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                bufferedWriter.newLine();
                bufferedWriter.write("Content : "+msg);
                bufferedWriter.newLine();
                bufferedWriter.flush();
                int val = Integer.parseInt(msg.substring(0,4));
                if(isoParser.getMessageTypesMapper().containsKey(val)) {
                    bufferedWriter.write(isoParser.getMessageTypesMapper().get(val));
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
            }
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
