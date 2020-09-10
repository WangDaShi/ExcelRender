package com.loatr.excel.visitor;

import com.loatr.excel.mapper.ExcelMapper;

public class ValueSetterVisitor implements MapperVisitor<Void>{
    @Override
    public Void forCell(ExcelMapper mapper) {
        return null;
    }

    @Override
    public Void forRow(ExcelMapper mapper) {
        return null;
    }

    @Override
    public Void forNest(ExcelMapper mapper) {
        return null;
    }
}
