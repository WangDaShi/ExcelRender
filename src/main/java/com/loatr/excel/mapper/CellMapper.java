package com.loatr.excel.mapper;

import com.loatr.excel.format.SimpleFormatter;
import com.loatr.excel.format.ValueFormatter;
import com.loatr.excel.visitor.MapperVisitor;

/**
 * 处理单个单元格的赋值
 */
public class CellMapper extends ExcelMapper {

    private int col;// 列id
    private String express;// 解析表达式
    private String message;// 指定的值

    @Override
    public <T> T accept(MapperVisitor<T> v) {
        return v.forCell(this);
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

}
