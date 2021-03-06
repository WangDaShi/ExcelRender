package com.loatr.excel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loatr.excel.mapper.ExcelMapper;
import com.loatr.excel.mapper.MapperFactory;
import com.loatr.excel.tools.AssertTools;
import com.loatr.excel.tools.ExcelTools;
import com.loatr.excel.visitor.MapperVisitor;
import com.loatr.excel.visitor.ValueSetterVisitor;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.*;
import java.util.*;

/**
 * 主类
 */
public class ExcelBuilder {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 主方法入口
     * @param path 要输出的文件路径 + 文件名
     * @param json json配置文件
     * @param template excel的模板文件
     * @param data 需要填入excel的数据
     * @throws IOException 解析出错抛异常
     */
    public static void create(String path, File json, File template, Object ... data) throws IOException {
        assertArgument(path,json,template,data);
        JsonConfig config = objectMapper.readValue(json,JsonConfig.class);
        Map<String,Object> dataMap = extraData(config.getData(),data);
        List<ExcelMapper> mappers = parseMapper(config);
        int sheetNum = config.getSheet();
        ExcelTools.genExcel(path,template,sheetNum, s->ExcelBuilder.rend(s,mappers,dataMap));
    }

    /**
     * 校验参数
     */
    private static void assertArgument(String path, File json, File template, Object[] data){
        if(Objects.isNull(path) || path.isBlank()){
            throw new NullPointerException("参数path不能为null");
        }
        AssertTools.assertExist(new File(path).getParentFile(),"目标文件所在目录不存在，path:" + path);
        AssertTools.assertExist(json,"参数json不能为null");
        AssertTools.assertExist(json,"参数template不能为null");
        AssertTools.assertEachNonNull(data,"参数data不能为null");
    }

    /**
     * 把值填到excel里面
     * @param sheet excel中的一个sheet页
     * @param mappers 映射关系
     * @param data 数据
     */
    private static void rend(Sheet sheet, List<ExcelMapper> mappers, Map<String,Object> data){
        MapperVisitor valueSetter= ValueSetterVisitor.create(sheet,data);
        for(ExcelMapper mapper : mappers){
            mapper.accept(valueSetter);
        }
        // TODO 后续可以考虑添加style的visitor,单元格merge的visitor
    }

    /**
     * 从json配置信息中解析出mapper的配置信息
     *
     * @param config 配置信息
     * @return mapper数组，顺序按json文件格式从上到下
     */
    private static List<ExcelMapper> parseMapper(JsonConfig config) {
        JsonNode[] nodes = config.getMappers();
        List<ExcelMapper> list = new ArrayList<>();
        for(int i = 0;i<nodes.length;i++){
            ExcelMapper mapper = MapperFactory.create(nodes[i]);
            list.add(mapper);
        }
        return list;
    }

    /**
     * 将传入的数据与json中的配置信息一一匹配
     * @param dataNames 数据名
     * @param data bean数组
     * @return bean名称与对象的映射
     */
    private static Map<String, Object> extraData(String[] dataNames, Object[] data) {
        Objects.requireNonNull(data,"传入数据不能为null");
        Objects.requireNonNull(dataNames,"json的data属性不能填空");
        if(dataNames.length != data.length){
            throw new IllegalArgumentException("json中data属性与传入参数数量不一致");
        }
        Map<String,Object> map = new HashMap<>();
        for(int i =0;i<dataNames.length;i++){
            if(map.containsKey(dataNames[i])){
                throw new IllegalArgumentException("json中data属性名不能重复");
            }
            map.put(dataNames[i],data[i]);
        }
        return map;
    }

    /**
     * 把json文件解析成配置对象
     * @param json json配置文件
     * @return 配置对象
     */
    private static JsonConfig parseJson(File json){
        try {
            return objectMapper.readValue(json,JsonConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("json解析出错");
        }
    }

}
