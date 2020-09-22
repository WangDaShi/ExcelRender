package com.loatr.excel;

/**
 * json配置文件中可以出现的关键字
 */
public enum Keyword {

    ROW("row"),
    COLUMN("column"),
    MESSAGE("message"),
    DATA("data"),
    MAPPER("mapper"),
    BLANK_ROW("blankRow"),
    START_ROW("startRow"),
    START_COLUMN("startColumn"),
    NEST_MAPPER("nestmapper");

    private String value;

    Keyword(String value){
        this.value = value;
    }

    public String value(){
        return this.value;
    }
}
