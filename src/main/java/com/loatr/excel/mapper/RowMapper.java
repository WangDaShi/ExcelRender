package com.loatr.excel.mapper;

import com.loatr.excel.format.SimpleFormatter;
import com.loatr.excel.format.ValueFormatter;
import com.loatr.excel.visitor.MapperVisitor;

import java.util.Map;

public class RowMapper extends AbstractRowMapper{

    public static RowMapper create(int row,int col,String express){
        return null;
    }

    @Override
    public <T> T accept(MapperVisitor<T> v) {
        return v.forRow(this);
    }

}
