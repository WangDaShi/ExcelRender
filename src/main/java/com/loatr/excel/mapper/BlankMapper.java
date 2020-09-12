package com.loatr.excel.mapper;

import com.loatr.excel.visitor.MapperVisitor;

public class BlankMapper extends ExcelMapper{

    private int blankRowLength;

    @Override
    public <T> T accept(MapperVisitor<T> v) {
        return v.fotBlank(this);
    }

    public int getBlankRowLength() {
        return blankRowLength;
    }

    public void setBlankRowLength(int blankRowLength) {
        this.blankRowLength = blankRowLength;
    }
}
