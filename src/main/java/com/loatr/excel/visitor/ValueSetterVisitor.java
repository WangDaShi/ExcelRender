package com.loatr.excel.visitor;

import com.loatr.excel.mapper.*;
import com.loatr.excel.tools.ExcelTools;
import com.loatr.excel.ValueExtractor;
import com.loatr.excel.format.ValueFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Map;
import java.util.Set;

public class ValueSetterVisitor implements MapperVisitor<Void>{

    private Sheet sheet;
    private Map<String,Object> dataMap;
    private int currRowId = 0;

    private ValueSetterVisitor(){};

    public static ValueSetterVisitor create(Sheet sheet,Map<String,Object> dataMap){
        ValueSetterVisitor v = new ValueSetterVisitor();
        v.setSheet(sheet);
        v.setDataMap(dataMap);
        return v;
    }


    @Override
    public Void forCell(CellMapper mapper) {
        setRowIdIfAbsent(mapper);
        Cell cell = ExcelTools.getCell(this.getSheet(),mapper.getRow(),mapper.getCol());
        String message = mapper.getMessage();
        if(message == null || message.isBlank()){
            Object value = ValueExtractor.extract(mapper.getExpress(), dataMap);
            cell.setCellValue(mapper.getFormatter().format(value));
        }else{
            cell.setCellValue(message);
        }
        currRowId = mapper.getRow();
        return null;
    }

    @Override
    public Void forRow(RowMapper mapper) {
        setRowIdIfAbsent(mapper);
        Object value = ValueExtractor.extract(mapper.getExpress(), dataMap);
        Map<Integer, String> expressMap = mapper.getExpressMap();
        int row = mapper.getRow();
        int col = mapper.getCol();
        fillValue(mapper, value, row, col);
        currRowId = mapper.getRow();
        return null;
    }

    private void fillValue(AbstractRowMapper mapper, Object value, int row, int col) {
        ValueFormatter formatter = mapper.getFormatter();
        Map<Integer, String> expressMap = mapper.getExpressMap();
        for(Map.Entry<Integer, String> entry : expressMap.entrySet()){
            Object property = ValueExtractor.getProperty(value, entry.getValue());
            Cell cell = ExcelTools.getCell(this.sheet, row, col + entry.getKey());
            cell.setCellValue(formatter.format(property));
        }
    }

    @Override
    public Void forNest(NestMapper mapper) {
        setRowIdIfAbsent(mapper);
        Object value = ValueExtractor.extract(mapper.getExpress(), dataMap);
        if(value.getClass().isArray()){
            return null;// TODO
        }else if(Iterable.class.isAssignableFrom(value.getClass())){
            Iterable<?> iterable = (Iterable)value;
            int j = 0;
            for(Object v : iterable){
                fillValue(mapper,v,mapper.getRow() + j, mapper.getCol());
                j++;
            }
            currRowId =  mapper.getRow() + j -1;
        }else {
            throw new RuntimeException("无法对一个非迭代对象取值");
        }
        return null;
    }

    @Override
    public Void fotBlank(BlankMapper mapper) {
        currRowId = currRowId + mapper.getBlankRowLength();
        return null;
    }

    private void setRowIdIfAbsent(ExcelMapper mapper){
        if(mapper.getRow() < 0){
            mapper.setRow(currRowId + 1);
        }
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
