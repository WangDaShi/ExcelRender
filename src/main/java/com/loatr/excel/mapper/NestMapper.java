package com.loatr.excel.mapper;

import com.loatr.excel.format.SimpleFormatter;
import com.loatr.excel.format.ValueFormatter;
import com.loatr.excel.visitor.MapperVisitor;

public class NestMapper extends ExcelMapper{

    private int row;
    private int col;
    private String[] exMap;
    private String express;
    private ValueFormatter formatter = new SimpleFormatter();

    @Override
    public <T> T accept(MapperVisitor<T> v) {
        return v.forNest(this);
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

    public String[] getExMap() {
        return exMap;
    }

    public void setExMap(String[] exMap) {
        this.exMap = exMap;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public ValueFormatter getFormatter() {
        return formatter;
    }

    public void setFormatter(ValueFormatter formatter) {
        this.formatter = formatter;
    }
}
