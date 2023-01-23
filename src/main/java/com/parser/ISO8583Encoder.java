package com.parser;

import com.entities.ISOField;
import java.util.Map;

public class ISO8583Encoder {

    public String encode(Map<Integer, ISOField> fields) {
        String res = "";

        String bitmap1 = "";
        String bitmap2 = "";
        for(int i = 1; i <= 128; i++){
            if(i <= 64) {
                if (fields.containsKey(i)) bitmap1 += "1";
                else bitmap1 += "0";
            }
            else {
                if (fields.containsKey(i)) bitmap2 += "1";
                else bitmap2 += "0";
            }
        }

        return res;
    }

}
