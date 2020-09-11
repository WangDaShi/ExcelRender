package com.loatr.excel.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.loatr.excel.Keyword;

/**
 * 生成映射关系的工厂类
 */
public class MapperFactory {

    public static ExcelMapper create(JsonNode node){
        if(node.has("row") && node.has("column")){
            return parseCellMapper(node);
        }else if(node.has("startRow") && node.has("startColumn")){
            if(node.has("nestMapper")){
                return parseNestMapper(node);
            }else if(node.has("mapper")){
                return parseRowMapper(node);
            }else{
                throw new RuntimeException("格式错误，无法解析");
            }
        }else{
            throw new RuntimeException("格式错误，无法解析");
        }
    }


    private static ExcelMapper parseRowMapper(JsonNode node) {
        RowMapper mapper = new RowMapper();
        mapper.setRow(readInt(node,Keyword.ROW));
        mapper.setRow(readInt(node,Keyword.COLUMN));
        mapper.setExpress(readString(node,Keyword.DATA));
        mapper.setExMap(readStringArray(node,Keyword.MAPPER));
        return mapper;
    }

    private static ExcelMapper parseNestMapper(JsonNode node) {
        NestMapper mapper = new NestMapper();
        mapper.setRow(readInt(node,Keyword.ROW));
        mapper.setRow(readInt(node,Keyword.COLUMN));
        mapper.setExpress(readString(node,Keyword.DATA));
        mapper.setExMap(readStringArray(node,Keyword.MAPPER));
        return mapper;
    }

    private static ExcelMapper parseCellMapper(JsonNode node) {
        CellMapper mapper = new CellMapper();
        mapper.setRow(readInt(node,Keyword.ROW));
        mapper.setCol(readInt(node,Keyword.COLUMN));
        if(node.has("message")){
            mapper.setMessage(readString(node,Keyword.MESSAGE));
        }else{
            mapper.setExpress(readString(node,Keyword.DATA));
        }
        return mapper;
    }


    private static int readInt(JsonNode node,Keyword keyword){
        JsonNode n = node.get(keyword.getValue());
        int a = n.asInt();
        if(a < 0){
            throw new IllegalArgumentException("row 不能小于0");
        }else{
            return a;
        }
    }

    private static String readString(JsonNode node,Keyword keyword){
        JsonNode n = node.get(keyword.getValue());
        return n.asText();
    }

    private static String[] readStringArray(JsonNode node,Keyword keyword){
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
