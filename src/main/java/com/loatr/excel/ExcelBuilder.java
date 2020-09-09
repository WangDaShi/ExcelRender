package com.loatr.excel;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ExcelBuilder {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static void create(String path, File json, File template, Object ... data) throws IOException {
        JsonConfig config = parseJson(json);
        Map<String,Object> dataMap = extraData(config.getData(),data);
        ExcelMapper[] mappers = parseMapper(config);
        int sheetNum = config.getSheet();
        ExcelTools.genExcel(path,template,sheetNum,s->ExcelBuilder.render(s,mappers,dataMap));
    }

    private static void render(Sheet sheet,ExcelMapper[] mappers,Map<String,Object> data){
        for(ExcelMapper mapper : mappers){
            mapper.map(sheet,data);
        }
    }

    private static ExcelMapper[] parseMapper(JsonConfig config) {
        return new ExcelMapper[0];
    }

    private static Map<String, Object> extraData(String[] dataNames, Object[] data) {
        Objects.requireNonNull(data);
        Objects.requireNonNull(dataNames);
        if(dataNames.length != data.length){
            throw new IllegalArgumentException("数据配置有误");
        }
        Map<String,Object> map = new HashMap<>();
        for(int i =0;i<dataNames.length;i++){
            if(map.containsKey(dataNames[i])){
                throw new IllegalArgumentException("数据名重复");
            }
            map.put(dataNames[i],data[i]);
        }
        return map;
    }

    private static JsonConfig parseJson(File json){
        try {
            return objectMapper.readValue(json,JsonConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("json解析出错");
        }
    }

}
