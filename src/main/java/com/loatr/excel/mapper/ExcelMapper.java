package com.loatr.excel.mapper;

import com.loatr.excel.format.SimpleFormatter;
import com.loatr.excel.format.ValueFormatter;
import com.loatr.excel.visitor.MapperVisitor;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Map;

/**
 * 表示一个对excel复制的一组操作
 */
public abstract class ExcelMapper {

    private int row;// excel的行id
    private ValueFormatter formatter = new SimpleFormatter();// 默认格式化工具

    public abstract <T> T accept(MapperVisitor<T> v);

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public ValueFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(ValueFormatter formatter) {
        this.formatter = formatter;
    }
}
