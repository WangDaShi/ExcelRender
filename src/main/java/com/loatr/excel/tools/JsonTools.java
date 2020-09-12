package com.loatr.excel.tools;

import com.fasterxml.jackson.databind.JsonNode;
import com.loatr.excel.Keyword;
import com.loatr.excel.exception.JsonParseException;

public class JsonTools {

    public static int readInt(JsonNode node, Keyword keyword){
        JsonNode n = node.get(keyword.value());
        nonNull(n,"属性" + keyword.value() + "不存在");
        int a = n.asInt();
        if(a < 0){
            throw new IllegalArgumentException("row 不能小于0");
        }else{
            return a;
        }
    }

    public static String readString(JsonNode node,Keyword keyword){
        JsonNode n = node.get(keyword.value());
        nonNull(n,"属性" + keyword.value() + "不存在");
        return n.asText();
    }

    public static String[] readStringArray(JsonNode node,Keyword keyword){
        JsonNode jsonNode = node.get(keyword.value());
        nonNull(jsonNode,"属性" + keyword.value() + "不存在");
        String[] arr = new String[jsonNode.size()];
        int i = 0;
        for(JsonNode n : jsonNode){
            arr[i] = n.asText();
        }
        return arr;
    }

    public static void nonNull(JsonNode node,String message){
        if(node.isNull()){
            throw new JsonParseException(message);
        }
    }
}
