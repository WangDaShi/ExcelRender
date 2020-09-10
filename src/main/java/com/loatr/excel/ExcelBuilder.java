package com.loatr.excel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loatr.excel.mapper.ExcelMapper;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 主类
 */
public class ExcelBuilder {

    private static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 主方法入口
     * @param path 要输出的文件路径
     * @param json json配置文件
     * @param template excel的模板文件
     * @param data 需要填入excel的数据
     * @throws IOException
     */
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

    /**
     * 从json配置信息中解析出mapper的配置信息
     *
     * @param config 配置信息
     * @return mapper数组，顺序按json文件格式从上到下
     */
    private static ExcelMapper[] parseMapper(JsonConfig config) {
        return new ExcelMapper[0];
    }

    /**
     * 将传入的数据与json中的配置信息一一匹配
     * @param dataNames 数据名
     * @param data bean数组
     * @return bean名称与对象的映射
     */
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
