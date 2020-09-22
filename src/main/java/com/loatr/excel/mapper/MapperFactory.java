package com.loatr.excel.mapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.loatr.excel.Keyword;
import com.loatr.excel.exception.JsonParseException;
import com.loatr.excel.tools.AssertTools;
import com.loatr.excel.tools.JsonTools;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * 生成映射关系的工厂类
 */
public class MapperFactory {

    /**
     * 创建一个ExcelMapper
     * @param node json节点
     * @return
     */
    public static ExcelMapper create(JsonNode node){
        // dispatch to the right mapper
        if(node.has(Keyword.COLUMN.value())){
            return parseCellMapper(node);
        }else if(node.has(Keyword.START_COLUMN.value())){
            if(node.has(Keyword.NEST_MAPPER.value())){
                return parseNestMapper(node);
            }else if(node.has(Keyword.MAPPER.value())){
                return parseRowMapper(node);
            }else{
                throw new JsonParseException("格式错误，node无法解析:" + node.toPrettyString());
            }
        }else if(node.has(Keyword.BLANK_ROW.value())) {
            return parseBlankMapper(node);
        }else {
            throw new JsonParseException("格式错误，node无法解析:" + node.toPrettyString());
        }
    }

    private static ExcelMapper parseCellMapper(JsonNode node) {
        CellMapper mapper = new CellMapper();
        if(node.has(Keyword.ROW.value())){
            mapper.setRow(JsonTools.readInt(node,Keyword.ROW));
        }else{
            mapper.setRow(-1);
        }
        mapper.setCol(JsonTools.readInt(node,Keyword.COLUMN));
        // 如果message字段填了值就不去尝试解析data字段
        if(node.has("message")){
            mapper.setMessage(JsonTools.readString(node,Keyword.MESSAGE));
        }else{
            mapper.setExpress(JsonTools.readString(node,Keyword.DATA));
        }
        return mapper;
    }

    private static BlankMapper parseBlankMapper(JsonNode node){
        BlankMapper mapper = new BlankMapper();
        int len = JsonTools.readInt(node,Keyword.BLANK_ROW);
        AssertTools.assertTrue(len >= 0,"blankRow不能小于0");
        mapper.setBlankRowLength(len);
        return mapper;
    }

    private static RowMapper parseRowMapper(JsonNode node) {
        RowMapper mapper = new RowMapper();
        if(node.has(Keyword.START_ROW.value())){
            mapper.setRow(JsonTools.readInt(node,Keyword.START_ROW));
        }else{
            mapper.setRow(-1);
        }
        mapper.setCol(JsonTools.readInt(node,Keyword.START_COLUMN));
        mapper.setExpress(JsonTools.readString(node,Keyword.DATA));
        mapper.setExpressMap(getMap(node,Keyword.MAPPER));
        return mapper;
    }

    private static ExcelMapper parseNestMapper(JsonNode node) {
        NestMapper mapper = new NestMapper();
        if(node.has(Keyword.START_ROW.value())){
            mapper.setRow(JsonTools.readInt(node,Keyword.START_ROW));
        }else{
            mapper.setRow(-1);
        }
        mapper.setCol(JsonTools.readInt(node,Keyword.START_COLUMN));
        mapper.setExpress(JsonTools.readString(node,Keyword.DATA));
        mapper.setExpressMap(getMap(node,Keyword.NEST_MAPPER));
        return mapper;
    }

    private static Map<Integer,String> getMap(JsonNode node,Keyword keyword){
        JsonNode mapper = node.get(keyword.value());
        JsonTools.nonNull(mapper,"属性" + keyword.value() + "不存在");
        if(mapper.isArray()){
            return extractFromArr(mapper);
        }else{
            return extractFromMap(mapper);
        }
    }

    private static TreeMap<Integer, String> extractFromMap(JsonNode mapper) {
        Iterator<Map.Entry<String, JsonNode>> iterator = mapper.fields();
        TreeMap<Integer,String> map = new TreeMap<>();
        while(iterator.hasNext()){
            Map.Entry<String, JsonNode> entry = iterator.next();
            Integer a;
            try{
                a = Integer.valueOf(entry.getKey());
            }catch (NumberFormatException e){
                e.printStackTrace();
                throw new JsonParseException(e.getMessage());
            }
            map.put(a,entry.getValue().asText());
        }
        return map;
    }

    private static TreeMap<Integer, String> extractFromArr(JsonNode node) {
        TreeMap<Integer,String> map = new TreeMap<>();
        int i = 0;
        for(JsonNode n : node){
            map.put(i,n.asText());
            i++;
        }
        return map;
    }

}
