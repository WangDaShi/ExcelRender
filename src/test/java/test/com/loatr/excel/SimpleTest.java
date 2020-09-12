package test.com.loatr.excel;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loatr.excel.ExcelBuilder;
import com.loatr.excel.JsonConfig;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.SQLOutput;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class SimpleTest {

    private final String prePath = "D:/test/excel/";
    
    @Test
    public void test(){
        File json = new File(prePath + "config.json");
        File template = new File(prePath + "template.xlsx");
        List<Transaction> models = new ArrayList<>();
        Transaction t1 = new Transaction();
        t1.setName("lalala");
        t1.setFee(123.45f);
        t1.setId("1234455677");
        t1.setNum(19);
        t1.setTime(LocalDateTime.now());
        models.add(t1);
        Transaction t2 = new Transaction();
        t2.setName("lalala");
        t2.setFee(123.45f);
        t2.setId("1234455677");
        t2.setNum(19);
        t2.setTime(LocalDateTime.now());
        models.add(t2);
        ExcelInfo info = new ExcelInfo();
        info.setMessage("info的消息");
        info.setDate(LocalDateTime.now());
        info.setMessage("this is a message");
        String path = prePath + "result.xlsx";
        try {
            ExcelBuilder.create(path,json,template,models,info);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testJson(){
        File json = new File(prePath + "config-1.json");
        JsonConfig jsonConfig;
        try {
            jsonConfig = objectMapper.readValue(json, JsonConfig.class);
            System.out.println(jsonConfig);
            JsonNode[] mappers = jsonConfig.getMappers();
            JsonNode node = mappers[0].get("mapper");
            System.out.println(node.isArray());
            System.out.println(node.size());
            for(JsonNode n : node){
                System.out.println(n.asText());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSplit() {
        String[] parts = "12.13.14.15.16().17".split("\\.");
        Assert.assertArrayEquals("与预期不符",
                new String[]{"12","13","14","15","16()","17"},parts);
    }

    @Test
    public void testSomething(){

    }
}
