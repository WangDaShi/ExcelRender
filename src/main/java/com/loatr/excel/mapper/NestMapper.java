package com.loatr.excel.mapper;

import com.loatr.excel.format.SimpleFormatter;
import com.loatr.excel.format.ValueFormatter;
import com.loatr.excel.visitor.MapperVisitor;

import java.util.Map;

public class NestMapper extends ExcelMapper{

    private int col;
    private String express;
    private Map<Integer,String> expressMap;

    @Override
    public <T> T accept(MapperVisitor<T> v) {
        return v.forNest(this);
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

    public Map<Integer, String> getExpressMap() {
        return expressMap;
    }

    public void setExpressMap(Map<Integer, String> expressMap) {
        this.expressMap = expressMap;
    }
}
