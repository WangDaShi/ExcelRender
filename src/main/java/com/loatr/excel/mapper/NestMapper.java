package com.loatr.excel.mapper;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.Map;

public class NestMapper implements ExcelMapper{

    private int row;
    private int col;
    private String dataExpression;
    private RowMapper rowMapper;

    @Override
    public void map(Sheet sheet, Map<String, Object> dataMap) {

    }

}
