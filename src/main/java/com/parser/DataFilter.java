package com.parser;

import com.entities.ISOField;
import java.util.HashSet;
import java.util.Map;

public class DataFilter {

    public static Map<Integer, ISOField> filterData(Map<Integer,ISOField> data, HashSet<Integer> output100 ){
        HashSet<Integer> removedData = new HashSet<>();
        for(Map.Entry<Integer,ISOField> entry : data.entrySet()) {
            if(!output100.contains(entry.getKey())) removedData.add(entry.getKey());
        }
        for(Integer elem : removedData){
            data.remove(elem);
        }
        return data;
    }

}
