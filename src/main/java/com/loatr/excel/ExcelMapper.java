package com.loatr.excel;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.Map;

public interface ExcelMapper {

    void map(Sheet sheet, Map<String,Object> dataMap);
}
