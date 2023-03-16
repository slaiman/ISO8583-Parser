package com.server;

import com.entities.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.parser.DataFilter;
import com.parser.ISO8583Encoder;
import com.parser.ISO8683Decoder;
import com.parser.Initializer;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
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
            //isoParser.parseMessageValues(isoParser);
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
                                //prepare and extract the variables for the call of service
                                String apiRequest;
                                String cardNumber = "";
                                String amount = "";
                                String currencyCode = "";
                                String reference = "";
                                String date = "";
                                String AuthCode = "";
                                String transactionFee = "";

                                String sessionId = callLogin();
                                if(!sessionId.isEmpty()) {
                                    Random rand = new Random();
                                    int upperBound = 1000000000;
                                    apiRequest = String.valueOf(rand.nextInt(upperBound));

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
                                        if (t.length() == 6)
                                            t = t.substring(0, 2) + ":" + t.substring(2, 4) + ":" + t.substring(4);
                                        String d = data.get(13).value;
                                        if (d.length() == 4) d = d.substring(0, 2) + ":" + d.substring(2);
                                        date = d + " " + t;
                                    }
                                    if (data.containsKey(28) && data.get(28) != null) {
                                        transactionFee = data.get(28).value;
                                    }
                                    if (data.containsKey(38) && data.get(38) != null) {
                                        AuthCode = data.get(38).value;
                                    }
                                    RefundEntity refund = new RefundEntity();
                                    refund.apiRequest = apiRequest;
                                    refund.cardNumber = cardNumber = "200";
                                    refund.transDate = date;
                                    refund.transAmount = amount;
                                    refund.currency = currencyCode = "550";
                                    refund.referenceNo = reference;
                                    refund.transactionFee = transactionFee = "490";
                                    refund.authCode = AuthCode;

                                    String server = "136.232.232.118";
                                    String port = "9090";
                                    String url = "http://" + server + ":" + port + "/NIBServer/Account/PosReversal";

                                    String result = sendRefundHTTPRequest(url, refund, sessionId);
                                    RefundResponseEntity refundResponseEntity = new RefundResponseEntity();
                                    refundResponseEntity = new ObjectMapper().readValue(result, RefundResponseEntity.class);
                                    System.out.println(result);
                                    response = refundResponseEntity.errorDescription;
                                }
                            }
                        }
                    }
                    String type = "Authorization Request";
                    bufferedWriter.write(type);
                    bufferedWriter.newLine();
                    bufferedWriter.flush();

                    TreeSet<Integer> output100 = Initializer.initializeData100();
                    filteredData = DataFilter.filterData(data,output100);
                    if(filteredData.containsKey(39)) {
                        ISOField field = filteredData.get(39);
                        field.value = response;
                        filteredData.put(39, field);
                    }
                    else{
                        ISOField field = new ISOField();
                        field.id = 39;
                        field.value = response;
                        filteredData.put(39,field);
                    }
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
                                String apiRequest;
                                String accountNumber = "";
                                String reference = "";
                                String date = "";

                                String sessionId = callLogin();
                                if(!sessionId.isEmpty()) {

                                    Random rand = new Random();
                                    int upperBound = 1000000000;
                                    apiRequest = String.valueOf(rand.nextInt(upperBound));

                                    if (data.containsKey(2) && data.get(2) != null) {
                                        accountNumber = data.get(2).value;
                                    }
                                    if (data.containsKey(37) && data.get(37) != null) {
                                        reference = data.get(37).value;
                                    }
                                    if (data.containsKey(12) && data.containsKey(13) && data.get(12) != null && data.get(13) != null) {
                                        String t = data.get(12).value;
                                        if (t.length() == 6)
                                            t = t.substring(0, 2) + ":" + t.substring(2, 4) + ":" + t.substring(4);
                                        String d = data.get(13).value;
                                        if (d.length() == 4) d = d.substring(0, 2) + ":" + d.substring(2);
                                        date = d + " " + t;
                                    }

                                    BalanceInquiryEntity balanceInquiryEntity = new BalanceInquiryEntity();
                                    balanceInquiryEntity.apiRequestId = apiRequest;
                                    balanceInquiryEntity.reference = reference;
                                    balanceInquiryEntity.transDate = date;
                                    balanceInquiryEntity.accountNo = accountNumber;


                                    String server = "136.232.232.118";
                                    String port = "9090";
                                    String url = "http://" + server + ":" + port + "/NIBServer/Account/checkAvailableBalanceAmtOnly";

                                    String result = sendBalanceInquiryHTTPRequest(url, balanceInquiryEntity);
                                    BalanceInquiryResponseEntity balanceInquiryResponseEntity = new BalanceInquiryResponseEntity();
                                    balanceInquiryResponseEntity = new ObjectMapper().readValue(result, BalanceInquiryResponseEntity.class);
                                    System.out.println(result);
                                    response = balanceInquiryResponseEntity.errorDescription;
                                }
                            }
                            else if(processingCode.length() == 6 && processingCode.startsWith("91")){
                                //prepare variables for the call of service
                                String customerId = "";
                                String language = "P";
                                String firstRecord = "0";
                                String maxResult = "10";
                                String accountNumber = "";
                                String fromDate = "";
                                String toDate = "";
                                String creditOrDebitIndicator = "";

                                String sessionId = callLogin();
                                if(!sessionId.isEmpty()) {

                                    if (data.containsKey(102) && data.get(102) != null) {
                                        customerId = data.get(102).value;
                                    }
                                    if (data.containsKey(2) && data.get(2) != null) {
                                        accountNumber = data.get(2).value;
                                    }
                                    if (data.containsKey(12) && data.containsKey(13) && data.get(12) != null && data.get(13) != null) {
                                        String t = data.get(12).value;
                                        if (t.length() == 6)
                                            t = t.substring(0, 2) + ":" + t.substring(2, 4) + ":" + t.substring(4);
                                        String d = data.get(13).value;
                                        if (d.length() == 4) d = d.substring(0, 2) + ":" + d.substring(2);
                                        fromDate = d + " " + t;
                                    }
                                    if (data.containsKey(14) && data.containsKey(12) && data.get(14) != null && data.get(12) != null) {
                                        String t = data.get(12).value;
                                        if (t.length() == 6)
                                            t = t.substring(0, 2) + ":" + t.substring(2, 4) + ":" + t.substring(4);
                                        String d = data.get(14).value;
                                        if (d.length() == 4) d = d.substring(0, 2) + ":" + d.substring(4);
                                        toDate = d + " " + t;
                                    }

                                    StatementEntity statementEntity = new StatementEntity();
                                    statementEntity.accountNo = accountNumber;
                                    statementEntity.customerId = customerId;
                                    statementEntity.fromDate = fromDate;
                                    statementEntity.toDate = toDate;

                                    String server = "136.232.232.118";
                                    String port = "9090";
                                    String url = "http://" + server + ":" + port + "/NIBServer/Account/transactionDetails";

                                    String result = sendStatementHTTPRequest(url, statementEntity);
                                    StatementResponseEntity statementResponseEntity = new StatementResponseEntity();
                                    statementResponseEntity = new ObjectMapper().readValue(result, StatementResponseEntity.class);
                                    System.out.println(result);
                                    response = statementResponseEntity.errorDescription;
                                }
                            }
                            else if(processingCode.length() == 6 && processingCode.startsWith("40")){
                                //prepare variables for the call of service
                                String apiRequest = "";
                                String reference = "";
                                String transDate = "";
                                String fromAccountNumber = "";
                                String toAccountNumber = "";
                                String transAmount = "";
                                String currency = "";
                                String referenceNo = "";
                                String transactionFee = "";
                                String customerId = "";
                                String bankId = "";

                                String sessionId = callLogin();
                                if(!sessionId.isEmpty()) {

                                    Random rand = new Random();
                                    int upperBound = 1000000000;
                                    apiRequest = String.valueOf(rand.nextInt(upperBound));

                                    if (data.containsKey(102) && data.get(102) != null) {
                                        fromAccountNumber = data.get(102).value;
                                    }
                                    if (data.containsKey(103) && data.get(103) != null) {
                                        toAccountNumber = data.get(103).value;
                                    }
                                    if (data.containsKey(4) && data.get(4) != null) {
                                        transAmount = data.get(4).value;
                                    }
                                    if (data.containsKey(49) && data.get(49) != null) {
                                        currency = data.get(49).value;
                                    }
                                    if (data.containsKey(28) && data.get(28) != null) {
                                        transactionFee = data.get(28).value;
                                    }
                                    if (data.containsKey(37) && data.get(37) != null) {
                                        reference = referenceNo = data.get(37).value;
                                    }
                                    if (data.containsKey(12) && data.containsKey(13) && data.get(12) != null && data.get(13) != null) {
                                        String t = data.get(12).value;
                                        if (t.length() == 6)
                                            t = t.substring(0, 2) + ":" + t.substring(2, 4) + ":" + t.substring(4);
                                        String d = data.get(13).value;
                                        if (d.length() == 4) d = d.substring(0, 2) + ":" + d.substring(2);
                                        transDate = d + " " + t;
                                    }
                                    if (data.containsKey(2) && data.get(2) != null) {
                                        customerId = data.get(2).value;
                                    }
                                    if (data.containsKey(32) && data.get(32) != null) {
                                        bankId = data.get(32).value;
                                    }

                                    LocalTransferEntity localTransferEntity = new LocalTransferEntity();
                                    localTransferEntity.apiRequest = apiRequest;
                                    localTransferEntity.reference = reference;
                                    localTransferEntity.transDate = transDate;
                                    localTransferEntity.fromAccountNumber = fromAccountNumber;
                                    localTransferEntity.toAccountNumber = toAccountNumber;
                                    localTransferEntity.transAmount = transAmount;
                                    localTransferEntity.currency = currency;
                                    localTransferEntity.referenceNo = referenceNo;
                                    localTransferEntity.transactionFee = transactionFee;
                                    localTransferEntity.customerId = customerId;
                                    localTransferEntity.bankId = bankId;

                                    String server = "136.232.232.118";
                                    String port = "9090";
                                    String url = "http://" + server + ":" + port + "/NIBServer/Account/LocalTransfer";

                                    String result = sendLocalTransferHTTPRequest(url, localTransferEntity);
                                    LocalTransferResponseEntity localTransferResponseEntity = new LocalTransferResponseEntity();
                                    localTransferResponseEntity = new ObjectMapper().readValue(result, LocalTransferResponseEntity.class);
                                    System.out.println(result);
                                    response = localTransferResponseEntity.errorDescription;
                                }
                            }
                            else if(processingCode.length() == 6 && processingCode.startsWith("01")){
                                //prepare variables for the call of service
                                String transAmount = "";
                                String referenceNo = "";
                                String transDate = "";
                                String currency = "";
                                String apiRequest = "";
                                String cashBoxReference = "";
                                String cardNumber = "";

                                String sessionId = callLogin();
                                if(!sessionId.isEmpty()) {

                                    Random rand = new Random();
                                    int upperBound = 1000000000;
                                    apiRequest = String.valueOf(rand.nextInt(upperBound));

                                    if (data.containsKey(23) && data.get(23) != null) {
                                        cardNumber = data.get(23).value;
                                    }
                                    if (data.containsKey(4) && data.get(4) != null) {
                                        transAmount = data.get(4).value;
                                    }
                                    if (data.containsKey(49) && data.get(49) != null) {
                                        currency = data.get(49).value;
                                    }
                                    if (data.containsKey(37) && data.get(37) != null) {
                                        referenceNo = data.get(37).value;
                                    }
                                    if (data.containsKey(12) && data.containsKey(13) && data.get(12) != null && data.get(13) != null) {
                                        String t = data.get(12).value;
                                        if (t.length() == 6)
                                            t = t.substring(0, 2) + ":" + t.substring(2, 4) + ":" + t.substring(4);
                                        String d = data.get(13).value;
                                        if (d.length() == 4) d = d.substring(0, 2) + ":" + d.substring(2);
                                        transDate = d + " " + t;
                                    }

                                    WithDrawalEntity withDrawalEntity = new WithDrawalEntity();
                                    withDrawalEntity.transAmount = transAmount;
                                    withDrawalEntity.referenceNo = referenceNo;
                                    withDrawalEntity.transDate = transDate;
                                    withDrawalEntity.currency = currency;
                                    withDrawalEntity.apiRequest = apiRequest;
                                    withDrawalEntity.cashBoxReference = cashBoxReference;
                                    withDrawalEntity.cardNumber = cardNumber;

                                    String server = "136.232.232.118";
                                    String port = "9090";
                                    String url = "http://" + server + ":" + port + "/NIBServer/Account/Withdrawal";

                                    String result = sendWithDrawalHTTPRequest(url, withDrawalEntity);
                                    WithDrawalResponseEntity withDrawalResponseEntity = new WithDrawalResponseEntity();
                                    withDrawalResponseEntity = new ObjectMapper().readValue(result, WithDrawalResponseEntity.class);
                                    System.out.println(result);
                                    response = withDrawalResponseEntity.errorDescription;
                                }

                            }
                            else if(processingCode.length() == 6 && processingCode.startsWith("00")){
                                //prepare variables for the call of service
                                String apiRequest = "";
                                String cardNumber = "";
                                String transAmount = "";
                                String currency = "";
                                String referenceNo = "";
                                String transDate = "";
                                String merchantBankId = "";
                                String merchantAccountNo = "";

                                String sessionId = callLogin();
                                if(!sessionId.isEmpty()) {

                                    Random rand = new Random();
                                    int upperBound = 1000000000;
                                    apiRequest = String.valueOf(rand.nextInt(upperBound));

                                    if (data.containsKey(23) && data.get(23) != null) {
                                        cardNumber = data.get(23).value;
                                    }
                                    if (data.containsKey(4) && data.get(4) != null) {
                                        transAmount = data.get(4).value;
                                    }
                                    if (data.containsKey(49) && data.get(49) != null) {
                                        currency = data.get(49).value;
                                    }
                                    if (data.containsKey(37) && data.get(37) != null) {
                                        referenceNo = data.get(37).value;
                                    }
                                    if (data.containsKey(12) && data.containsKey(13) && data.get(12) != null && data.get(13) != null) {
                                        String t = data.get(12).value;
                                        if (t.length() == 6)
                                            t = t.substring(0, 2) + ":" + t.substring(2, 4) + ":" + t.substring(4);
                                        String d = data.get(13).value;
                                        if (d.length() == 4) d = d.substring(0, 2) + ":" + d.substring(2);
                                        transDate = d + " " + t;
                                    }
                                    if (data.containsKey(32) && data.get(32) != null) {
                                        merchantBankId = data.get(32).value;
                                    }
                                    if (data.containsKey(103) && data.get(103) != null) {
                                        merchantAccountNo = data.get(103).value;
                                    }

                                    PurchaseEntity purchaseEntity = new PurchaseEntity();
                                    purchaseEntity.merchantBankId = merchantBankId;
                                    purchaseEntity.transAmount = transAmount;
                                    purchaseEntity.referenceNo = referenceNo;
                                    purchaseEntity.transDate = transDate;
                                    purchaseEntity.currency = currency;
                                    purchaseEntity.apiRequest = apiRequest;
                                    purchaseEntity.merchantAccountNo = merchantAccountNo;
                                    purchaseEntity.cardNumber = cardNumber;

                                    String server = "136.232.232.118";
                                    String port = "9090";
                                    String url = "http://" + server + ":" + port + "/NIBServer/Account/Purchase";

                                    String result = sendPurchaseHTTPRequest(url, purchaseEntity);
                                    PurchaseResponseEntity purchaseResponseEntity = new PurchaseResponseEntity();
                                    purchaseResponseEntity = new ObjectMapper().readValue(result, PurchaseResponseEntity.class);
                                    System.out.println(result);
                                    response = purchaseResponseEntity.errorDescription;
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
                    if(filteredData.containsKey(39)) {
                        ISOField field = filteredData.get(39);
                        field.value = response;
                        filteredData.put(39, field);
                    }
                    else{
                        ISOField field = new ISOField();
                        field.id = 39;
                        field.value = response;
                        filteredData.put(39,field);
                    }
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
                    rawData = msg.substring(4);
                    response = ""+(val + 10);
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

    public static String callLogin() {
        String sessionId = "";
        String output = "";
        try {
            String url = "http://136.232.232.118:9090/NIBServer/SecurityService/login/";
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost  = new HttpPost(url);
            httpPost.setHeader("Content-type", "application/json");
            String body = "{\n" +
                    "\t\"userId\":\"XPI_POS\",\n" +
                    "\t\"password\":\"123456\",\n" +
                    "\t\"company\":\"1\",\n" +
                    "\t\"branch\":\"5\"\n" +
                    "}";
            StringEntity stringEntity = new StringEntity(body);
            stringEntity.setContentType("application/json");
            httpPost.getRequestLine();
            httpPost.setEntity(stringEntity);

            HttpResponse response = httpClient.execute(httpPost);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200 && statusCode != 201) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            System.out.println("Output from Server .... \n");
            String temp = "";
            while ((temp = br.readLine()) != null) {
                System.out.println(temp);
                output += temp;
            }
            LoginResponseEntity loginResponseEntity = new ObjectMapper().readValue(output, LoginResponseEntity.class);
            sessionId = loginResponseEntity.sessionId;

        } catch(Exception ex){
            ex.printStackTrace();
        }

        return sessionId;
    }

    public static String sendRefundHTTPRequest(String url,RefundEntity refundEntity, String sessionId){
        String output = "";
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost  = new HttpPost(url);
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setHeader("session",sessionId);
            String body = "{\n" +
                    "    \"apiRequestId\": \""+refundEntity.apiRequest+"\",\n" +
                    "    \"cardNumber\": \""+refundEntity.cardNumber+"\",\n" +
                    "    \"transDate\": \""+refundEntity.transDate+"\",\n" +
                    "    \"transAmount\": \""+refundEntity.transAmount+"\",\n" +
                    "    \"currency\": \""+refundEntity.currency+"\",\n" +
                    "    \"referenceNo\": \""+refundEntity.referenceNo+"\",\n" +
                    "    \"transactionFee\": \""+refundEntity.transactionFee+"\",\n" +
                    "    \"authCode\": "+refundEntity.authCode+"\n" +
                    "\n" +
                    "}";
            StringEntity stringEntity = new StringEntity(body);
            stringEntity.setContentType("application/json");
            httpPost.getRequestLine();
            httpPost.setEntity(stringEntity);

            HttpResponse response = httpClient.execute(httpPost);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200 && statusCode != 201) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            System.out.println("Output from Server .... \n");
            String temp = "";
            while ((temp = br.readLine()) != null) {
                System.out.println(temp);
                output += temp;
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return output;
    }

    public static String sendBalanceInquiryHTTPRequest(String url,BalanceInquiryEntity balanceInquiryEntity){
        String output = "";
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost  = new HttpPost(url);
            httpPost.setHeader("Content-type", "application/json");
            String body = "{\n" +
                    "    \"apiRequestId\": \""+balanceInquiryEntity.apiRequestId+"\",\n" +
                    "    \"reference\": \""+balanceInquiryEntity.reference+"\",\n" +
                    "    \"transDate\": \""+balanceInquiryEntity.transDate+"\",\n" +
                    "    \"accountNo\": \""+balanceInquiryEntity.accountNo+"\"\n" +
                    "}";
            StringEntity stringEntity = new StringEntity(body);
            stringEntity.setContentType("application/json");
            httpPost.getRequestLine();
            httpPost.setEntity(stringEntity);

            HttpResponse response = httpClient.execute(httpPost);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200 && statusCode != 201) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            System.out.println("Output from Server .... \n");
            String temp = "";
            while ((temp = br.readLine()) != null) {
                System.out.println(temp);
                output += temp;
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return output;
    }

    public static String sendStatementHTTPRequest(String url,StatementEntity statementEntity){
        String output = "";
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost  = new HttpPost(url);
            httpPost.setHeader("Content-type", "application/json");
            String body = "{\n" +
                    "    \"customerId\": \""+statementEntity.customerId+"\",\n" +
                    "    \"language\": \""+statementEntity.language+"\",\n" +
                    "    \"firstRecord\": "+statementEntity.firstRecord+",\n" +
                    "    \"maxResult\": "+statementEntity.maxResult+",\n" +
                    "    \"accountNo\": \""+statementEntity.accountNo+"\",\n" +
                    "    \"fromDate\": \""+statementEntity.fromDate+"\",\n" +
                    "    \"toDate\": \""+statementEntity.toDate+"\",\n" +
                    "    \"creditOrDebitIndicator\": \""+statementEntity.creditOrDebitIndicator+"\"\n" +
                    "}";
            StringEntity stringEntity = new StringEntity(body);
            stringEntity.setContentType("application/json");
            httpPost.getRequestLine();
            httpPost.setEntity(stringEntity);

            HttpResponse response = httpClient.execute(httpPost);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200 && statusCode != 201) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            System.out.println("Output from Server .... \n");
            String temp = "";
            while ((temp = br.readLine()) != null) {
                System.out.println(temp);
                output += temp;
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return output;
    }

    public static String sendWithDrawalHTTPRequest(String url,WithDrawalEntity withDrawalEntity){
        String output = "";
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost  = new HttpPost(url);
            httpPost.setHeader("Content-type", "application/json");
            String body = "{\n" +
                    "    \"transAmount\": \""+withDrawalEntity.transAmount+"\",\n" +
                    "    \"referenceNo\": \""+withDrawalEntity.referenceNo+"\",\n" +
                    "    \"transDate\": \""+withDrawalEntity.transDate+"\",\n" +
                    "    \"currency\": \""+withDrawalEntity.currency+"\",\n" +
                    "    \"apiRequestId\": \""+withDrawalEntity.apiRequest+"\",\n" +
                    "    \"cashBoxReference\": \""+withDrawalEntity.cashBoxReference+"\",\n" +
                    "    \"cardNumber\": \""+withDrawalEntity.cardNumber+"\"\n" +
                    "}";
            StringEntity stringEntity = new StringEntity(body);
            stringEntity.setContentType("application/json");
            httpPost.getRequestLine();
            httpPost.setEntity(stringEntity);

            HttpResponse response = httpClient.execute(httpPost);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200 && statusCode != 201) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            System.out.println("Output from Server .... \n");
            String temp = "";
            while ((temp = br.readLine()) != null) {
                System.out.println(temp);
                output += temp;
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return output;
    }

    public static String sendPurchaseHTTPRequest(String url,PurchaseEntity purchaseEntity){
        String output = "";
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost  = new HttpPost(url);
            httpPost.setHeader("Content-type", "application/json");
            String body = "{\n" +
                    "    \"merchantBankId\": \""+purchaseEntity.merchantBankId+"\",\n" +
                    "    \"transAmount\": \""+purchaseEntity.transAmount+"\",\n" +
                    "    \"referenceNo\": \""+purchaseEntity.referenceNo+"\",\n" +
                    "    \"transDate\": \""+purchaseEntity.transDate+"\",\n" +
                    "    \"merchantAccountNo\": \""+purchaseEntity.merchantAccountNo+"\",\n" +
                    "    \"currency\": \""+purchaseEntity.currency+"\",\n" +
                    "    \"apiRequestId\": \""+purchaseEntity.apiRequest+"\",\n" +
                    "    \"cardNumber\": \""+purchaseEntity.cardNumber+"\"\n" +
                    "}";
            StringEntity stringEntity = new StringEntity(body);
            stringEntity.setContentType("application/json");
            httpPost.getRequestLine();
            httpPost.setEntity(stringEntity);

            HttpResponse response = httpClient.execute(httpPost);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200 && statusCode != 201) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            System.out.println("Output from Server .... \n");
            String temp = "";
            while ((temp = br.readLine()) != null) {
                System.out.println(temp);
                output += temp;
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return output;
    }

    public static String sendLocalTransferHTTPRequest(String url,LocalTransferEntity localTransferEntity){
        String output = "";
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpPost httpPost  = new HttpPost(url);
            httpPost.setHeader("Content-type", "application/json");
            String body = "{\n" +
                    "    \"apiRequestId\": \""+localTransferEntity.apiRequest+"\",\n" +
                    "    \"reference\": \""+localTransferEntity.reference+"\",\n" +
                    "    \"transDate\": \""+localTransferEntity.transDate+"\",\n" +
                    "    \"fromAccountNo\": \""+localTransferEntity.fromAccountNumber+"\",\n" +
                    "    \"toAccountNo\": \""+localTransferEntity.toAccountNumber+"\",\n" +
                    "    \"transAmount\": \""+localTransferEntity.transAmount+"\",\n" +
                    "    \"currency\": \""+localTransferEntity.currency+"\",\n" +
                    "    \"referenceNo\": \""+localTransferEntity.referenceNo+"\",\n" +
                    "    \"transactionFee\": \""+localTransferEntity.transactionFee+"\",\n" +
                    "    \"customerId\": \""+localTransferEntity.customerId+"\",\n" +
                    "    \"bankId\": "+localTransferEntity.bankId+"\n" +
                    "}";
            StringEntity stringEntity = new StringEntity(body);
            stringEntity.setContentType("application/json");
            httpPost.getRequestLine();
            httpPost.setEntity(stringEntity);

            HttpResponse response = httpClient.execute(httpPost);

            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200 && statusCode != 201) {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }

            BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));

            System.out.println("Output from Server .... \n");
            String temp = "";
            while ((temp = br.readLine()) != null) {
                System.out.println(temp);
                output += temp;
            }
        } catch(Exception ex){
            ex.printStackTrace();
        }
        return output;
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
