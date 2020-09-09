package test.com.loatr.excel;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loatr.excel.ExcelBuilder;
import com.loatr.excel.JsonConfig;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SimpleTest {

    private final String prePath = "D:/test/excel/";
    
    @Test
    public void test(){
        File json = new File(prePath + "config.json");
        File template = new File(prePath + "template.xlsx");
        List<Transaction> models = new ArrayList<>();
        ExcelInfo info = new ExcelInfo();
        String path = prePath + "result.xlsx";
        try {
            ExcelBuilder.create(path,json,template,info,models);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testJson(){
        File json = new File(prePath + "config.json");
        try {
            JsonConfig jsonConfig = objectMapper.readValue(json, JsonConfig.class);
            System.out.println(jsonConfig);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
