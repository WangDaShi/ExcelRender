package com.loatr.excel.mapper;

import com.loatr.excel.format.SimpleFormatter;
import com.loatr.excel.format.ValueFormatter;
import com.loatr.excel.visitor.MapperVisitor;

import java.util.Map;

public class RowMapper extends ExcelMapper{

    private int col;
    private Map<Integer,String> expressMap;
    private String express;

    public static RowMapper create(int row,int col,String express){
        return null;
    }

    @Override
    public <T> T accept(MapperVisitor<T> v) {
        return v.forRow(this);
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Map<Integer, String> getExpressMap() {
        return expressMap;
    }

    public void setExpressMap(Map<Integer, String> expressMap) {
        this.expressMap = expressMap;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

}
