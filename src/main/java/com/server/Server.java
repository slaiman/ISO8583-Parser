package com.server;

import com.parser.DataFilter;
import com.parser.ISO8583Encoder;
import com.parser.ISO8683Decoder;
import com.entities.ISOField;
import com.parser.Initializer;
import jdk.incubator.http.HttpClient;
import jdk.incubator.http.HttpRequest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.*;
import java.util.Map;
import java.util.Random;
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
        ISO8683Decoder isoParser = null;
        try {
            isoParser = new ISO8683Decoder();
            isoParser.parseMessage(msg);
            sendToClient(isoParser, clientSocket);
            isoParser.parseMessageValues(isoParser);
            checkScenario(isoParser, clientSocket, msg);

        } catch(Exception ex){
            ex.printStackTrace();
            checkScenario(isoParser, clientSocket, msg);
        }
    }

    private void sendToClient(ISO8683Decoder isoParser, Socket clientSocket) {
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
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            String response = "";
            String rawData = "";
            String output;

            Map<Integer,ISOField> data = isoParser.getDataElements();
            Map<Integer,ISOField> filteredData;

            if(mti.equals("0100") || mti.equals("0101") || mti.equals("0120") || mti.equals("0121")){

                if(mti.equals("0100")) {
                    if(data.containsKey(3) && data.get(3) != null) {
                        String processingCode = data.get(3).value;
                        if(processingCode != null && !processingCode.isEmpty()) {
                            if (processingCode.length() == 6 && processingCode.startsWith("20")) {
                                //prepare variables for the call of service
                                int apiRequest;
                                String cardNumber;
                                String amount;
                                String currencyCode;
                                String reference;
                                String date;
                                String AuthCode;

                                Random rand = new Random();
                                int upperBound = 1000000000;
                                apiRequest = rand.nextInt(upperBound);

                                if (data.containsKey(23) && data.get(23) != null) {
                                    cardNumber = data.get(23).value;
                                }
                                if (data.containsKey(4) && data.get(4) != null) {
                                    amount = data.get(4).value;
                                }
                                if (data.containsKey(49) && data.get(49) != null) {
                                    currencyCode = data.get(49).value;
                                }
                                if (data.containsKey(37) && data.get(37) != null) {
                                    reference = data.get(37).value;
                                }
                                if (data.containsKey(12) && data.containsKey(13) && data.get(12) != null && data.get(13) != null) {
                                    String t = data.get(12).value;
                                    String d = data.get(13).value;
                                    date = d + " " + t;
                                }
                                if (data.containsKey(38) && data.get(38) != null) {
                                    AuthCode = data.get(38).value;
                                }

                                String server = "10.111.3.41";
                                String url = server + ":8080/NIBServer/Account";

                                String result = sendHTTPRequest(url);
                                System.out.println(result);
                            }
                        }
                    }
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
                else {
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
            }
            else if(mti.equals("0200") || mti.equals("0201") || mti.equals("0220")|| mti.equals("0221")){

                if(mti.equals("0200") || mti.equals("0201")) {

                    if(data.containsKey(3) && data.get(3) != null) {
                        String processingCode = data.get(3).value;
                        if(processingCode != null && !processingCode.isEmpty()){
                            if(processingCode.length() == 6 && processingCode.startsWith("31")) {
                                //prepare variables for the call of service
                                int apiRequest;
                                String accountNumber;
                                String reference;
                                String date;

                                Random rand = new Random();
                                int upperBound = 1000000000;
                                apiRequest = rand.nextInt(upperBound);

                                if(data.containsKey(2) && data.get(2) != null) {
                                    accountNumber = data.get(2).value;
                                }
                                if(data.containsKey(37) && data.get(37) != null){
                                    reference = data.get(37).value;
                                }
                                if(data.containsKey(12) && data.containsKey(13) && data.get(12) != null && data.get(13) != null){
                                    String t = data.get(12).value;
                                    String d = data.get(13).value;
                                    date = d + " "+ t;
                                }
                            }
                            else if(processingCode.length() == 6 && processingCode.startsWith("91")){
                                //prepare variables for the call of service
                                int apiRequest;
                                String accountNumber;
                                String reference;
                                String date;
                                int stmtSize;

                                Random rand = new Random();
                                int upperBound = 1000000000;
                                apiRequest = rand.nextInt(upperBound);

                                if(data.containsKey(2) && data.get(2) != null) {
                                    accountNumber = data.get(2).value;
                                }
                                if(data.containsKey(37) && data.get(37) != null){
                                    reference = data.get(37).value;
                                }
                                if(data.containsKey(12) && data.containsKey(13) && data.get(12) != null && data.get(13) != null){
                                    String t = data.get(12).value;
                                    String d = data.get(13).value;
                                    date = d + " "+ t;
                                }
                                stmtSize = 2;
                            }
                            else if(processingCode.length() == 6 && processingCode.startsWith("40")){
                                //prepare variables for the call of service
                                int apiRequest;
                                String fromAccountNumber;
                                String toAccountNumber;
                                String amount;
                                String currencyCode;
                                String transactionFee;
                                String reference;
                                String date;
                                String bankId;

                                Random rand = new Random();
                                int upperBound = 1000000000;
                                apiRequest = rand.nextInt(upperBound);

                                if(data.containsKey(102) && data.get(102) != null) {
                                    fromAccountNumber = data.get(102).value;
                                }
                                if(data.containsKey(103) && data.get(103) != null) {
                                    toAccountNumber = data.get(103).value;
                                }
                                if(data.containsKey(4) && data.get(4) != null) {
                                    amount = data.get(4).value;
                                }
                                if(data.containsKey(49) && data.get(49) != null) {
                                    currencyCode = data.get(49).value;
                                }
                                if(data.containsKey(28) && data.get(28) != null) {
                                    transactionFee = data.get(28).value;
                                }
                                if(data.containsKey(37) && data.get(37) != null){
                                    reference = data.get(37).value;
                                }
                                if(data.containsKey(12) && data.containsKey(13) && data.get(12) != null && data.get(13) != null){
                                    String t = data.get(12).value;
                                    String d = data.get(13).value;
                                    date = d + " "+ t;
                                }
                                if(data.containsKey(32) && data.get(32) != null){
                                    bankId = data.get(32).value;
                                }
                            }
                            else if(processingCode.length() == 6 && processingCode.startsWith("01")){
                                //prepare variables for the call of service
                                int apiRequest;
                                String cardNumber;
                                String amount;
                                String accountNumber;
                                String currencyCode;
                                String transactionFee;
                                String reference;
                                String date;
                                String terminalId;

                                Random rand = new Random();
                                int upperBound = 1000000000;
                                apiRequest = rand.nextInt(upperBound);

                                if(data.containsKey(23) && data.get(23) != null) {
                                    cardNumber = data.get(23).value;
                                }
                                if(data.containsKey(4) && data.get(4) != null) {
                                    amount = data.get(4).value;
                                }
                                if(data.containsKey(2) && data.get(2) != null) {
                                    accountNumber = data.get(2).value;
                                }
                                if(data.containsKey(49) && data.get(49) != null) {
                                    currencyCode = data.get(49).value;
                                }
                                if(data.containsKey(28) && data.get(28) != null) {
                                    transactionFee = data.get(28).value;
                                }
                                if(data.containsKey(37) && data.get(37) != null){
                                    reference = data.get(37).value;
                                }
                                if(data.containsKey(12) && data.containsKey(13) && data.get(12) != null && data.get(13) != null){
                                    String t = data.get(12).value;
                                    String d = data.get(13).value;
                                    date = d + " "+ t;
                                }
                                if(data.containsKey(41) && data.get(41) != null){
                                    terminalId = data.get(41).value;
                                }
                            }
                            else if(processingCode.length() == 6 && processingCode.startsWith("00")){
                                //prepare variables for the call of service
                                int apiRequest;
                                String cardNumber;
                                String amount;
                                String currencyCode;
                                String transactionFee;
                                String reference;
                                String date;
                                String merchantBankId;
                                String merchantAccount;

                                Random rand = new Random();
                                int upperBound = 1000000000;
                                apiRequest = rand.nextInt(upperBound);

                                if(data.containsKey(23) && data.get(23) != null) {
                                    cardNumber = data.get(23).value;
                                }
                                if(data.containsKey(4) && data.get(4) != null) {
                                    amount = data.get(4).value;
                                }
                                if(data.containsKey(49) && data.get(49) != null) {
                                    currencyCode = data.get(49).value;
                                }
                                if(data.containsKey(28) && data.get(28) != null) {
                                    transactionFee = data.get(28).value;
                                }
                                if(data.containsKey(37) && data.get(37) != null){
                                    reference = data.get(37).value;
                                }
                                if(data.containsKey(12) && data.containsKey(13) && data.get(12) != null && data.get(13) != null){
                                    String t = data.get(12).value;
                                    String d = data.get(13).value;
                                    date = d + " "+ t;
                                }
                                if(data.containsKey(32) && data.get(32) != null){
                                    merchantBankId = data.get(32).value;
                                }
                                if(data.containsKey(103) && data.get(103) != null){
                                    merchantAccount = data.get(103).value;
                                }
                            }
                        }
                    }

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
                else {
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
            }
            else if(mti.equals("0300") || mti.equals("0302")|| mti.equals("0382")){

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
                else {
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
            }
            else if(mti.equals("0420") || mti.equals("0421")) {

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
            else if(mti.equals("0520") || mti.equals("0521") || mti.equals("0522") || mti.equals("0523")) {

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
                else {
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
            }
            else if(mti.equals("0600") || mti.equals("0601") || mti.equals("0620")){

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
            }
            //Network Management messages
            else if(mti.equals("0800") || mti.equals("0820")) {

                String type;
                if(data.containsKey(70) && data.get(70) != null) {
                    ISOField field = data.get(70);
                    if (mti.equals("0800") && field.value.equals("001")) {

                        type = "Sign on, both issuer and acquirer";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("002")) {

                        type = "Sign off, both issuer and acquirer";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("005")) {

                        type = "Special instructions (message to network operator)";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("0061")) {

                        type = "Sign on as issuer";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("0062")) {

                        type = "Sign off as issuer";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("0071")) {

                        type = "Sign on as acquirer";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("0072")) {

                        type = "Sign off as acquirer";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("0101")) {

                        type = "Key change, both issuer and acquirer";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("0161")) {

                        type = "Issuer key change";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("0171")) {

                        type = "Acquirer key change";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("0180")) {

                        type = "Issuer/acquirer key change request";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("0181")) {

                        type = "Acquirer key change request";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("0191")) {

                        type = "Issuer key change request";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("0201")) {

                        type = "Initiate cutoff, issuer and acquirer";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("0202")) {

                        type = "Cutoff complete, issuer and acquirer";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("0261")) {

                        type = "Initiate cutoff as issuer";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("0262")) {

                        type = "Cutoff complete as issuer";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("0271")) {

                        type = "Initiate cutoff as acquirer";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("0272")) {

                        type = "Cutoff complete as acquirer";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("0281")) {

                        type = "Terminal cutoff";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("0282")) {

                        type = "Cardholder application cutoff";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("0301")) {

                        type = "Echo test (reply required handshake)";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0820") && field.value.equals("0301")) {

                        type = "Echo test (protocol acknowledgment handshake))";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("0361")) {

                        type = "Issuer echo test (reply required)";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0820") && field.value.equals("0361")) {

                        type = "Issuer echo test (protocol acknowledgment)";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0800") && field.value.equals("0371")) {

                        type = "Acquirer echo test (reply required)";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();

                    } else if (mti.equals("0820") && ((Integer.parseInt(field.value) == 371) || (field.value.equals("0371")) )) {

                        type = "Acquirer echo test (protocol acknowledgment)";
                        bufferedWriter.write(type);
                        bufferedWriter.newLine();
                        bufferedWriter.flush();
                    }
                }
                else {
                    type = "Network Management Request";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
                TreeSet<Integer> output800 = Initializer.initializeData800();
                filteredData = DataFilter.filterData(data,output800);
                ISO8583Encoder iso8583Encoder = new ISO8583Encoder();
                rawData = iso8583Encoder.encodeMessage(filteredData);
                response = "0810";
            }
            else{
                int val = Integer.parseInt(msg.substring(0,4));
                if(isoParser.getMessageTypesMapper().containsKey(val)) {
                    bufferedWriter.write(isoParser.getMessageTypesMapper().get(val));
                    bufferedWriter.newLine();
                    bufferedWriter.flush();
                }
            }
            output = "Data sent from server : "+response + rawData;

            bufferedWriter.write(output);
            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public String sendHTTPRequest(String urlStr) {
        String result = "";
        try {
            //URL url = new URL("http://localhost:8080/RESTfulExample/json/product/get");
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            result = "";
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                result += output;
            }

            conn.disconnect();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return  result;
    }

    public String sendHttpRequest2(String url){
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("X-RapidAPI-Host", "jokes-by-api-ninjas.p.rapidapi.com")
                .header("X-RapidAPI-Key", "your-rapidapi-key")
                .method("GET", HttpRequest.BodyPublisher.noBody())
                .build();
        HttpResponse<String> response = null;

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandler.asString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(response.body());
        return response.body();
    }
    public String sendHTTPRequest3(String url) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        String apiOutput = "";
        try {
            //Define a HttpGet request; You can choose between HttpPost, HttpDelete or HttpPut also.
            //Choice depends on type of method you will be invoking.
            HttpGet getRequest = new HttpGet(url);

            //Set the API media type in http accept header
            getRequest.addHeader("accept", "application/xml");

            //Send the request; It will immediately return the response in HttpResponse object
            HttpResponse response = httpClient.execute(getRequest);

            //verify the valid error code first
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }

            //Now pull back the response object
            HttpEntity httpEntity = response.getEntity();
            apiOutput = EntityUtils.toString(httpEntity);

            //Lets see what we got from API
            System.out.println(apiOutput);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return apiOutput;
    }
}
