package com.loatr.excel.visitor;

import com.loatr.excel.mapper.CellMapper;
import com.loatr.excel.mapper.ExcelMapper;
import com.loatr.excel.mapper.NestMapper;
import com.loatr.excel.mapper.RowMapper;

/**
 * ExcelMapper对应的versitor接口
 * @param <T>
 */
public interface MapperVisitor<T> {

    T forCell(CellMapper mapper);

    T forRow(RowMapper mapper);

    T forNest(NestMapper mapper);
}
