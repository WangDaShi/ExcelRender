package com.loatr.excel.mapper;

import com.loatr.excel.visitor.MapperVisitor;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.Map;

/**
 * 表示一个对excel复制的一组操作
 */
public abstract class ExcelMapper {

    public abstract <T> T accept(MapperVisitor<T> v);

}
