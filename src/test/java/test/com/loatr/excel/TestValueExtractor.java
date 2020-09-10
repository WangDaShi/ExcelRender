package test.com.loatr.excel;

import com.loatr.excel.ValueExtractor;
import com.loatr.excel.mapper.ExcelMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestValueExtractor {

    @Test
    public void testBeanProperty(){
        Map<String,Object> map = new HashMap<>();
        Transaction tran = new Transaction();
        tran.setName("my name");
        map.put("data1",tran);
        String expression = "data1.name";
        Object value = ValueExtractor.extract(expression, map);
        System.out.println(value.getClass());
        System.out.println(value.toString());
    }

    @Test
    public void testMethod(){
        Map<String,Object> map = new HashMap<>();
        Transaction tran = new Transaction();
        tran.setName("my name");
        List<Transaction> list = new ArrayList<>();
        list.add(tran);
        map.put("data1",list);
        String expression = "data1.size()";
        Object value = ValueExtractor.extract(expression, map);
        System.out.println(value.getClass());
        System.out.println(value.toString());
    }

    @Test
    public void testNest(){
        Map<String,Object> map = new HashMap<>();
        Transaction tran = new Transaction();
        tran.setName("my name");
        Transaction tran1 = new Transaction();
        tran1.setTran(tran);
        map.put("data1",tran1);
        String expression = "data1.tran.name.length()";
        Object value = ValueExtractor.extract(expression, map);
        System.out.println(value.getClass());
        System.out.println(value.toString());
    }
}
