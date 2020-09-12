package com.loatr.excel.visitor;

import com.loatr.excel.tools.ExcelTools;
import com.loatr.excel.ValueExtractor;
import com.loatr.excel.format.ValueFormatter;
import com.loatr.excel.mapper.CellMapper;
import com.loatr.excel.mapper.NestMapper;
import com.loatr.excel.mapper.RowMapper;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Map;

public class ValueSetterVisitor implements MapperVisitor<Void>{

    private Sheet sheet;
    private Map<String,Object> dataMap;

    private ValueSetterVisitor(){};

    public static ValueSetterVisitor create(Sheet sheet,Map<String,Object> dataMap){
        ValueSetterVisitor v = new ValueSetterVisitor();
        v.setSheet(sheet);
        v.setDataMap(dataMap);
        return v;
    }


    @Override
    public Void forCell(CellMapper mapper) {
        Cell cell = ExcelTools.getCell(this.getSheet(),mapper.getRow(),mapper.getCol());
        String message = mapper.getMessage();
        if(message == null || message.isBlank()){
            Object value = ValueExtractor.extract(mapper.getExpress(), dataMap);
            cell.setCellValue(mapper.getFormatter().format(value));
        }else{
            cell.setCellValue(message);
        }
        return null;
    }

    @Override
    public Void forRow(RowMapper mapper) {
        Object value = ValueExtractor.extract(mapper.getExpress(), dataMap);
        String[] exMap = mapper.getExMap();
        int row = mapper.getRow();
        int col = mapper.getCol();
        ValueFormatter formatter = mapper.getFormatter();
        for(int i = 0;i<exMap.length;i++){
            Object property = ValueExtractor.getProperty(value, exMap[i]);
            Cell cell = ExcelTools.getCell(this.sheet, row, col + i);
            cell.setCellValue(formatter.format(property));
        }
        return null;
    }

    @Override
    public Void forNest(NestMapper mapper) {
        Object value = ValueExtractor.extract(mapper.getExpress(), dataMap);
        String[] exMap = mapper.getExMap();
        int row = mapper.getRow();
        int col = mapper.getCol();
        ValueFormatter formatter = mapper.getFormatter();
        if(!value.getClass().isAssignableFrom(Iterable.class)){
            throw new RuntimeException("无法对一个非迭代对象取值");
        }else{
            Iterable<?> iterable = (Iterable)value;
            int j = 0;
            for(Object v : iterable){
                for(int i = 0;i<exMap.length;i++){
                    Object property = ValueExtractor.getProperty(value, exMap[i]);
                    Cell cell = ExcelTools.getCell(sheet, row + j, col+ i);
                    cell.setCellValue(formatter.format(property));
                }
                j++;
            }
        }
        return null;
    }

    public Sheet getSheet() {
        return sheet;
    }

    public void setSheet(Sheet sheet) {
        this.sheet = sheet;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }
}
