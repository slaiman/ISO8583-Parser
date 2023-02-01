package com.parser;

import com.entities.ISOField;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class DataFilter {

    public static TreeMap<Integer, ISOField> filterData(Map<Integer,ISOField> data, TreeSet<Integer> output100 ){
        TreeMap<Integer,ISOField> choosedData = new TreeMap<>();

        for(Integer entry : output100) {
            if(data.containsKey(entry)) choosedData.put(entry,data.get(entry));
        }
        return choosedData;
    }

}
