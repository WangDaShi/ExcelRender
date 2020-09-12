package com.loatr.excel.visitor;

import com.loatr.excel.mapper.*;

/**
 * ExcelMapper对应的versitor接口
 * @param <T>
 */
public interface MapperVisitor<T> {

    T forCell(CellMapper mapper);

    T forRow(RowMapper mapper);

    T forNest(NestMapper mapper);

    T fotBlank(BlankMapper mapper);
}
