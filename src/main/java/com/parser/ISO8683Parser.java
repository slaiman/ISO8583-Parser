package com.parser;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ISO8683Parser {

    private Map<Integer, String> dataElements;
    private HashMap<String,String> mit;
    private HashMap<Integer,Integer> bitMapper;
    private HashMap<Integer,String> fieldsDescriptionMapper;
    private HashMap<Integer,ISOField> fieldsProperties;


    public Map<Integer, String> parseMessage(String message) {

        // Initialize empty maps to store data and meta-data
        mit = new HashMap<String, String>();
        bitMapper = new HashMap<Integer, Integer>();
        fieldsDescriptionMapper = new HashMap<Integer, String>();
        fieldsProperties = new HashMap<Integer, ISOField>();
        dataElements = new TreeMap<Integer, String>();

        // First, extract the message header and the bitmap from the message
        String MTI = message.substring(0, 4);
        parseMTI(MTI);

        String bitMap = message.substring(4, 20);

        String binaryString = strToBinary(bitMap);

        if(binaryString.charAt(0) == '1'){
            bitMap += message.substring(20,36);
            binaryString = strToBinary(bitMap);
        }

        fieldsDescriptionMapper = Initializer.initializeFieldsMapper();
        fieldsProperties = Initializer.initializeFieldProperties();

        parseBitMap(binaryString);

        int index = 20;

        parseMessage(message,index);

        // Return the map of data elements
        return dataElements;
    }

    public void parseMessage(String msg, int index){
        //filter key-value mappers according to value = 1
        Map<Integer,Integer> filtered = bitMapper.entrySet().stream().filter(a->a.getValue().intValue() == 1).collect(Collectors.toMap(a-> a.getKey(), a -> a.getValue()));
        Map<Integer, Integer> sortedFiltered = new TreeMap<>(filtered);

        for(Integer id : sortedFiltered.keySet()) {
            if(id == 1){
                int length = 16;
                String value = msg.substring(index, index + length);
                index += length;
                dataElements.put(id,value);
            }
            else {
                int length = fieldsProperties.get(id - 1).length;
                String type = fieldsProperties.get(id - 1).type;
                String value;
                if(index == msg.length()) {
                    dataElements.put(id, "");
                    continue;
                }
                if (type.contains("IFA_LLL") || type.contains("IFA_LL")) {
                    String prefix = "";
                    int prefixLength = 0;
                    if(type.contains("IFA_LLL")) {
                       prefixLength = 3;
                    }
                    else if(type.contains("IFA_LL")){
                        prefixLength = 2;
                    }
                    prefix = msg.substring(index, index + prefixLength);
                    index = index + prefixLength;
                    Integer prefixVal = Integer.parseInt(prefix);

                    if(index + prefixVal > msg.length()){
                        value = msg.substring(index, msg.length());
                        index = msg.length();
                    }
                    else {
                        value = msg.substring(index, index + prefixVal);
                        index += prefixVal;
                    }
                }
                else {
                    if(index + length > msg.length()){
                        value = msg.substring(index,msg.length());
                        index = msg.length();
                    }
                    else {
                        value = msg.substring(index, index + length);
                        index += length;
                    }
                }
                dataElements.put(id, value);
            }
        }
    }

    public int calcPrefix(String prefix) {
        int val =  0;
        String res = "";
        for(int i = 0; i < prefix.length(); i++) {
            if(prefix.charAt(i) != '0') res += prefix.charAt(i);
        }
        if(!res.isEmpty()) val = Integer.parseInt(res);
        return val;
    }

    public void parseBitMap(String binaryBitMap) {

       for(int i = 1; i < binaryBitMap.length(); i++) {
            if(binaryBitMap.charAt(i-1) == '1') bitMapper.put(i,1);
            else bitMapper.put(i,0);
       }
    }

    public String strToBinary(String s)
    {
        // variable to store the converted
        // Binary Sequence
        String binary = "";

        // converting the accepted Hexadecimal
        // string to upper case
        s = s.toUpperCase();

        // initializing the HashMap class
        HashMap<Character, String> hashMap
                = new HashMap<Character, String>();

        // storing the key value pairs
        hashMap.put('0', "0000");
        hashMap.put('1', "0001");
        hashMap.put('2', "0010");
        hashMap.put('3', "0011");
        hashMap.put('4', "0100");
        hashMap.put('5', "0101");
        hashMap.put('6', "0110");
        hashMap.put('7', "0111");
        hashMap.put('8', "1000");
        hashMap.put('9', "1001");
        hashMap.put('A', "1010");
        hashMap.put('B', "1011");
        hashMap.put('C', "1100");
        hashMap.put('D', "1101");
        hashMap.put('E', "1110");
        hashMap.put('F', "1111");

        int i;
        char ch;

        // loop to iterate through the length
        // of the Hexadecimal String
        for (i = 0; i < s.length(); i++) {
            // extracting each character
            ch = s.charAt(i);

            // checking if the character is
            // present in the keys
            if (hashMap.containsKey(ch))

                // adding to the Binary Sequence
                // the corresponding value of
                // the key
                binary += hashMap.get(ch);

                // returning Invalid Hexadecimal
                // String if the character is
                // not present in the keys
            else {
                binary = "Invalid Hexadecimal String";
                return binary;
            }
        }

        // returning the converted Binary
        return binary;
    }

    public void parseMTI(String MTI) {
        switch (MTI.charAt(0)) {

            case 0: mit.put("ISO Version","ISO 8583:1987");
            case 1: mit.put("ISO Version","ISO 8583:1993");
            case 2: mit.put("ISO Version","ISO 8583:2003");
            case 3: mit.put("ISO Version","Reserved by ISO");
            case 4: mit.put("ISO Version","Reserved by ISO");
            case 5: mit.put("ISO Version","Reserved by ISO");
            case 6: mit.put("ISO Version","Reserved by ISO");
            case 7: mit.put("ISO Version","Reserved by ISO");
            case 8: mit.put("ISO Version","National use");
            case 9: mit.put("ISO Version","Private use");
        }

        switch (MTI.charAt(1)) {

            case 0: mit.put("Message class","Reserved by ISO");
            case 1: mit.put("Message class","Authorization message");
            case 2: mit.put("Message class","Financial messages");
            case 3: mit.put("Message class","File actions message");
            case 4: mit.put("Message class","Reversal and chargeback messages");
            case 5: mit.put("Message class","Reconciliation message");
            case 6: mit.put("Message class","Administrative message");
            case 7: mit.put("Message class","Fee collection messages");
            case 8: mit.put("Message class","Network management message");
            case 9: mit.put("Message class","Reserved by ISO");
        }

        switch (MTI.charAt(2)) {

            case 0: mit.put("Message function","Request");
            case 1: mit.put("Message function","Request response");
            case 2: mit.put("Message function","Advice");
            case 3: mit.put("Message function","Advice response");
            case 4: mit.put("Message function","Notification");
            case 5: mit.put("Message function","Notification acknowledgement");
            case 6: mit.put("Message function","Instruction");
            case 7: mit.put("Message function","Instruction acknowledgement");
            case 8: mit.put("Message function","Reserved by ISO");
            case 9: mit.put("Message function","Reserved by ISO");
        }

        switch (MTI.charAt(3)) {

            case 0: mit.put("Message origin","Acquirer");
            case 1: mit.put("Message origin","Acquirer repeat");
            case 2: mit.put("Message origin","Issuer");
            case 3: mit.put("Message origin","Issuer repeat");
            case 4: mit.put("Message origin","Other");
            case 5: mit.put("Message origin","Reserved by ISO");
            case 6: mit.put("Message origin","Reserved by ISO");
            case 7: mit.put("Message origin","Reserved by ISO");
            case 8: mit.put("Message origin","Reserved by ISO");
            case 9: mit.put("Message origin","Reserved by ISO");
        }
    }
}
