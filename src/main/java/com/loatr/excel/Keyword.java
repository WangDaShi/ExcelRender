package com.loatr.excel;

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

    private Keyword(String value){
        this.value = value;
    }

    public String value(){
        return this.value;
    }
}
