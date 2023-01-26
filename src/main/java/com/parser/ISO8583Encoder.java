package com.parser;

import com.entities.ISOField;

import java.util.HashMap;
import java.util.Map;

public class ISO8583Encoder {

    public String encodeMessage(Map<Integer, ISOField> fields) {

        String bitmap1 = "";
        String bitmap2 = "";
        String dataelements = "";
        for(int i = 1; i <= 128; i++){
            if(i <= 64) {
                if (fields.containsKey(i)){
                    bitmap1 += "1";
                    if(fields.get(i) != null) {
                        ISOField field = fields.get(i);
                        if (field.type.contains("IFA_LLL") || field.type.contains("IFA_LL")) {
                            int fieldLength = field.actualLength;
                            String strLength = String.valueOf(fieldLength);
                            dataelements += strLength + field.value;
                        } else dataelements += field.value;
                    }
                }
                else bitmap1 += "0";
            }
            else if(bitmap1.charAt(0) == '1'){
                if (fields.containsKey(i)) {
                    bitmap2 += "1";
                    if (fields.get(i) != null) {
                        ISOField field = fields.get(i);
                        if (field.type.contains("IFA_LLL") || field.type.contains("IFA_LL")) {
                            int fieldLength = field.actualLength;
                            String strLength = String.valueOf(fieldLength);
                            dataelements += strLength + field.value;
                        } else dataelements += field.value;
                    }
                }
                else bitmap2 += "0";
            }
            else break;
        }
        String hex1 = binaryToHex(bitmap1);
        String hex2 = binaryToHex(bitmap2);
        return hex1+hex2+dataelements;
    }

    private String binaryToHex(String s)
    {
        // variable to store the converted
        // Hex Sequence
        String hex = "";

        // initializing the HashMap class
        HashMap<String, Character> hashMap
                = new HashMap<>();

        // storing the key value pairs
        hashMap.put("0000", '0');
        hashMap.put("0001", '1');
        hashMap.put("0010", '2');
        hashMap.put("0011", '3');
        hashMap.put("0100", '4');
        hashMap.put("0101", '5');
        hashMap.put("0110", '6');
        hashMap.put("0111", '7');
        hashMap.put("1000", '8');
        hashMap.put("1001", '9');
        hashMap.put("1010", 'A');
        hashMap.put("1011", 'B');
        hashMap.put("1100", 'C');
        hashMap.put("1101", 'D');
        hashMap.put("1110", 'E');
        hashMap.put("1111", 'F');

        // loop to iterate through the length
        // of the binary String
        for (int i = 0; i < s.length(); i += 4) {
            String str = s.substring(i,i+4);
            Character c = hashMap.get(str);
            hex += c;
        }
        // returning the converted Binary
        return hex;
    }
}
