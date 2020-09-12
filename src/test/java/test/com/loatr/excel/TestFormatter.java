package test.com.loatr.excel;

import com.loatr.excel.format.SimpleFormatter;
import org.junit.Test;

public class TestFormatter {

    @Test
    public void test(){
        SimpleFormatter simpleFormatter = new SimpleFormatter();

        System.out.println(simpleFormatter.format(123.45f));
        System.out.println(simpleFormatter.format(Float.valueOf(123.45f)));
    }
}
