package com.loatr.excel.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.loatr.excel.Keyword;
import com.loatr.excel.tools.JsonTools;

/**
 * 生成映射关系的工厂类
 */
public class MapperFactory {

    public static ExcelMapper create(JsonNode node){
        // dispatch
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
        mapper.setRow(JsonTools.readInt(node,Keyword.ROW));
        mapper.setRow(JsonTools.readInt(node,Keyword.COLUMN));
        mapper.setExpress(JsonTools.readString(node,Keyword.DATA));
        mapper.setExMap(JsonTools.readStringArray(node,Keyword.MAPPER));
        return mapper;
    }

    private static ExcelMapper parseNestMapper(JsonNode node) {
        NestMapper mapper = new NestMapper();
        mapper.setRow(JsonTools.readInt(node,Keyword.ROW));
        mapper.setRow(JsonTools.readInt(node,Keyword.COLUMN));
        mapper.setExpress(JsonTools.readString(node,Keyword.DATA));
        mapper.setExMap(JsonTools.readStringArray(node,Keyword.MAPPER));
        return mapper;
    }

    private static ExcelMapper parseCellMapper(JsonNode node) {
        CellMapper mapper = new CellMapper();
        mapper.setRow(JsonTools.readInt(node,Keyword.ROW));
        mapper.setCol(JsonTools.readInt(node,Keyword.COLUMN));
        if(node.has("message")){
            mapper.setMessage(JsonTools.readString(node,Keyword.MESSAGE));
        }else{
            mapper.setExpress(JsonTools.readString(node,Keyword.DATA));
        }
        return mapper;
    }

}
