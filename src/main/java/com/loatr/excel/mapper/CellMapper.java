package com.loatr.excel.mapper;

import com.loatr.excel.ExcelTools;
import com.loatr.excel.ValueExtractor;
import com.loatr.excel.format.DefaultFormatter;
import com.loatr.excel.format.ValueFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Map;

/**
 * 处理单个单元格的赋值
 */
public class CellMapper implements ExcelMapper {

    private int row;// 单元格id
    private int col;// 列id
    private String value;// 解析表达式
    private String message;// 指定的值
    private ValueFormatter formatter = new DefaultFormatter();// 默认格式化工具

    @Override
    public void map(Sheet sheet, Map<String, Object> dataMap) {
        Cell cell = ExcelTools.getCell(sheet, row, col);
        if(message == null || message.isBlank()){
            Object value = ValueExtractor.extract(this.value, dataMap);
            cell.setCellValue(formatter.format(value));
        }else{
            cell.setCellValue(message);
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
