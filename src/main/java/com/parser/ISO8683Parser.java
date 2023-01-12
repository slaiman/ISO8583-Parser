package com.parser;

import com.entities.ISOField;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ISO8683Parser {

    private Map<Integer, ISOField> dataElements;
    private HashMap<String,String> mti;
    private HashMap<Integer,Integer> bitMapper;
    private HashMap<Integer,String> fieldsDescriptionMapper;
    private HashMap<Integer,ISOField> fieldsProperties;


    public Map<Integer, ISOField> parseMessage(String message) {

        // Initialize empty maps to store data and meta-data
        mti = new HashMap<String, String>();
        bitMapper = new HashMap<Integer, Integer>();
        fieldsDescriptionMapper = new HashMap<Integer, String>();
        fieldsProperties = new HashMap<Integer, ISOField>();
        dataElements = new TreeMap<Integer, ISOField>();

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
            ISOField field = fieldsProperties.get(id - 1);
            if(id == 1){
                int length = 16;
                String value = msg.substring(index, index + length);
                index += length;
                field.actualLength = length;
                field.value = value;
                dataElements.put(id, field);
            }
            else {
                int length = field.length;
                String type = field.type;
                String value;
                if(index == msg.length()) {
                    dataElements.put(id, null);
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
                        field.actualLength = msg.length() - index;
                        index = msg.length();
                    }
                    else {
                        value = msg.substring(index, index + prefixVal);
                        field.actualLength = prefixVal;
                        index += prefixVal;
                    }
                }
                else {
                    if(index + length > msg.length()){
                        value = msg.substring(index,msg.length());
                        field.actualLength = msg.length() - index;
                        index = msg.length();
                    }
                    else {
                        value = msg.substring(index, index + length);
                        field.actualLength = length;
                        index += length;
                    }
                }
                field.value = value;
                dataElements.put(id, field);
            }
        }
        parseDataElementValues();
    }

    public void parseDataElementValues(){

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
        if (MTI.charAt(0) == '0')  mti.put("ISO Version","ISO 8583:1987");
        else if(MTI.charAt(0) == '1') mti.put("ISO Version","ISO 8583:1993");
        else if(MTI.charAt(0) == '2') mti.put("ISO Version","ISO 8583:2003");
        else if(MTI.charAt(0) == '3') mti.put("ISO Version","Reserved by ISO");
        else if(MTI.charAt(0) == '4') mti.put("ISO Version","Reserved by ISO");
        else if(MTI.charAt(0) == '5') mti.put("ISO Version","Reserved by ISO");
        else if(MTI.charAt(0) == '6') mti.put("ISO Version","Reserved by ISO");
        else if(MTI.charAt(0) == '7') mti.put("ISO Version","Reserved by ISO");
        else if(MTI.charAt(0) == '8') mti.put("ISO Version","National use");
        else if(MTI.charAt(0) == '9') mti.put("ISO Version","Private use");

        if(MTI.charAt(1) == '0') mti.put("Message class","Reserved by ISO");
        else if(MTI.charAt(1) == '1') mti.put("Message class","Authorization message");
        else if(MTI.charAt(1) == '2') mti.put("Message class","Financial messages");
        else if(MTI.charAt(1) == '3') mti.put("Message class","File actions message");
        else if(MTI.charAt(1) == '4') mti.put("Message class","Reversal and chargeback messages");
        else if(MTI.charAt(1) == '5') mti.put("Message class","Reconciliation message");
        else if(MTI.charAt(1) == '6') mti.put("Message class","Administrative message");
        else if(MTI.charAt(1) == '7') mti.put("Message class","Fee collection messages");
        else if(MTI.charAt(1) == '8') mti.put("Message class","Network management message");
        else if(MTI.charAt(1) == '9') mti.put("Message class","Reserved by ISO");

        if(MTI.charAt(2) == '0') mti.put("Message function","Request");
        else if(MTI.charAt(2) == '1') mti.put("Message function","Request response");
        else if(MTI.charAt(2) == '2') mti.put("Message function","Advice");
        else if(MTI.charAt(2) == '3') mti.put("Message function","Advice response");
        else if(MTI.charAt(2) == '4') mti.put("Message function","Notification");
        else if(MTI.charAt(2) == '5') mti.put("Message function","Notification acknowledgement");
        else if(MTI.charAt(2) == '6') mti.put("Message function","Instruction");
        else if(MTI.charAt(2) == '7') mti.put("Message function","Instruction acknowledgement");
        else if(MTI.charAt(2) == '8') mti.put("Message function","Reserved by ISO");
        else if(MTI.charAt(2) == '9') mti.put("Message function","Reserved by ISO");


        if(MTI.charAt(3) == '0') mti.put("Message origin","Acquirer");
        else if(MTI.charAt(3) == '1') mti.put("Message origin","Acquirer repeat");
        else if(MTI.charAt(3) == '2') mti.put("Message origin","Issuer");
        else if(MTI.charAt(3) == '3') mti.put("Message origin","Issuer repeat");
        else if(MTI.charAt(3) == '4') mti.put("Message origin","Other");
        else if(MTI.charAt(3) == '5') mti.put("Message origin","Reserved by ISO");
        else if(MTI.charAt(3) == '6') mti.put("Message origin","Reserved by ISO");
        else if(MTI.charAt(3) == '7') mti.put("Message origin","Reserved by ISO");
        else if(MTI.charAt(3) == '8') mti.put("Message origin","Reserved by ISO");
        else if(MTI.charAt(3) == '9') mti.put("Message origin","Reserved by ISO");
    }

    public Map<Integer, ISOField> getDataElements() {
        return dataElements;
    }

    public void setDataElements(Map<Integer, ISOField> dataElements) {
        this.dataElements = dataElements;
    }

    public HashMap<String, String> getMit() {
        return mti;
    }

    public void setMit(HashMap<String, String> mti) {
        this.mti = mti;
    }

    public HashMap<Integer, Integer> getBitMapper() {
        return bitMapper;
    }

    public void setBitMapper(HashMap<Integer, Integer> bitMapper) {
        this.bitMapper = bitMapper;
    }

    public HashMap<Integer, String> getFieldsDescriptionMapper() {
        return fieldsDescriptionMapper;
    }

    public void setFieldsDescriptionMapper(HashMap<Integer, String> fieldsDescriptionMapper) {
        this.fieldsDescriptionMapper = fieldsDescriptionMapper;
    }

    public HashMap<Integer, ISOField> getFieldsProperties() {
        return fieldsProperties;
    }

    public void setFieldsProperties(HashMap<Integer, ISOField> fieldsProperties) {
        this.fieldsProperties = fieldsProperties;
    }
}
