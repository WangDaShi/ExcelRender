package com.loatr.excel.mapper;

import com.loatr.excel.format.SimpleFormatter;
import com.loatr.excel.format.ValueFormatter;
import com.loatr.excel.visitor.MapperVisitor;

/**
 * 处理单个单元格的赋值
 */
public class CellMapper extends ExcelMapper {

    private int row;// 单元格id
    private int col;// 列id
    private String express;// 解析表达式
    private String message;// 指定的值
    private ValueFormatter formatter = new SimpleFormatter();// 默认格式化工具

    @Override
    public <T> T accept(MapperVisitor<T> v) {
        return v.forCell(this);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ValueFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(ValueFormatter formatter) {
        this.formatter = formatter;
    }
}
