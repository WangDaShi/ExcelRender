package com.loatr.excel.mapper;

import com.loatr.excel.ExcelTools;
import com.loatr.excel.ValueExtractor;
import com.loatr.excel.format.SimpleFormatter;
import com.loatr.excel.format.ValueFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Map;

public class RowMapper implements ExcelMapper{

    private int row;
    private int col;
    private String[] exMap;
    private String express;
    private ValueFormatter formatter = new SimpleFormatter();

    public static RowMapper create(int row,int col,String express){
        return null;
    }

    @Override
    public void map(Sheet sheet, Map<String, Object> dataMap) {
        Object value = ValueExtractor.extract(express, dataMap);
        for(int i = 0;i<exMap.length;i++){
            Object property = ValueExtractor.getProperty(value, exMap[i]);
            Cell cell = ExcelTools.getCell(sheet, row, col + i);
            cell.setCellValue(formatter.format(property));
        }
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
}
