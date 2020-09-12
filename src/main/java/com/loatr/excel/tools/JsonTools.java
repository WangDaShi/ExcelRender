package com.loatr.excel.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.loatr.excel.Keyword;

public class JsonTools {

    public static int readInt(JsonNode node, Keyword keyword){
        JsonNode n = node.get(keyword.getValue());
        int a = n.asInt();
        if(a < 0){
            throw new IllegalArgumentException("row 不能小于0");
        }else{
            return a;
        }
    }

    public static String readString(JsonNode node,Keyword keyword){
        JsonNode n = node.get(keyword.getValue());
        return n.asText();
    }

    public static String[] readStringArray(JsonNode node,Keyword keyword){
        if(!node.has(keyword.getValue())){
            throw new IllegalArgumentException("属性" + keyword + "不存在");
        }
        JsonNode jsonNode = node.get(keyword.getValue());
        String[] arr = new String[jsonNode.size()];
        int i = 0;
        for(JsonNode n : jsonNode){
            arr[i] = n.asText();
        }
        return arr;
    }
}
