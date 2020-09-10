package com.loatr.excel.mapper;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.Map;

public class RowMapper implements ExcelMapper{

    private int row;
    private int col;
    private String[] exMap;
    private String dataExpression;

    @Override
    public void map(Sheet sheet, Map<String, Object> dataMap) {

    }
}
