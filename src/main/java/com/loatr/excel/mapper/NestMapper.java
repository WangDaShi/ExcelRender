package com.loatr.excel.mapper;

import com.loatr.excel.format.SimpleFormatter;
import com.loatr.excel.format.ValueFormatter;
import com.loatr.excel.visitor.MapperVisitor;

import java.util.Map;

public class NestMapper extends AbstractRowMapper{

    @Override
    public <T> T accept(MapperVisitor<T> v) {
        return v.forNest(this);
    }

}
