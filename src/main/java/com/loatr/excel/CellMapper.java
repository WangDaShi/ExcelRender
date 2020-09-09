package com.loatr.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Map;

/**
 * 处理单个单元格的赋值
 */
public class CellMapper implements ExcelMapper{

    private int row;
    private int col;
    private String value;

    @Override
    public void map(Sheet sheet, Map<String, Object> dataMap) {
        Cell cell = ExcelTools.getCell(sheet, row, col);
        cell.setCellValue(value);
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
