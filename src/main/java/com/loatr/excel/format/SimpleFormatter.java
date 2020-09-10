package com.loatr.excel.format;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class SimpleFormatter implements ValueFormatter{

    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public String format(Object value) {
        Class<?> colType = value.getClass();
        if (colType.getName().equals("int") || colType.equals(Integer.class)) {
            return value.toString();
        } else if (colType.getName().equals("byte") || colType.equals(Byte.class)) {
            return value.toString();
        } else if (colType.getName().equals("short") || colType.equals(Short.class)) {
            return value.toString();
        } else if (colType.getName().equals("boolean") || colType.equals(Boolean.class)) {
            return value.toString();
        } else if (colType.getName().equals("long") || colType.equals(Long.class)) {
            return value.toString();
        } else if (colType.getName().equals("float") || colType.equals(Float.class)) {
            return null;
        } else if (colType.getName().equals("double") || colType.equals(Double.class)) {
            return null;
        } else if (colType.getName().equals("char") || colType.equals(Character.class)
                || colType.equals(String.class)) {
            return value.toString();
        } else if (java.util.Date.class.isAssignableFrom(colType)) {
            return ((Date)value).toString();
        } else if (java.sql.Time.class.isAssignableFrom(colType)) {
            return null;
        } else if (colType.equals(LocalDate.class)) {
            return value == null ? null : ((LocalDate) value).format(dateFormatter);
        } else if (colType.equals(LocalDateTime.class)) {
            return value == null ? null : ((LocalDate) value).format(dateTimeFormatter);
        } else if (BigDecimal.class.isAssignableFrom(colType)) {
            return value == null ? null : ((BigDecimal)value).toString();
        } else if (colType.isEnum()) {
            Enum<?> e = (Enum<?>) value;
            return e == null ? null : e.name();
        } else{
            return value.toString();
        }
    }

}
