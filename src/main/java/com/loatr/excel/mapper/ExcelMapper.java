package com.loatr.excel.mapper;

import org.apache.poi.ss.usermodel.Sheet;

import java.util.Map;

/**
 * 表示一个对excel复制的一组操作
 */
public interface ExcelMapper {

    void map(Sheet sheet, Map<String,Object> dataMap);

}
