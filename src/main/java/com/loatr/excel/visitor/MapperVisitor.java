package com.loatr.excel.visitor;

import com.loatr.excel.mapper.ExcelMapper;

public interface MapperVisitor<T> {

    T forCell(ExcelMapper mapper);

    T forRow(ExcelMapper mapper);

    T forNest(ExcelMapper mapper);
}
