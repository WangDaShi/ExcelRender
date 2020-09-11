package com.loatr.excel;

public enum Keyword {

    ROW("row"),
    COLUMN("column"),
    MESSAGE("message"),
    DATA("data"),
    MAPPER("mapper"),
    STARTROW("startRow"),
    STARTCOLUMN("startColumn");

    private String value;

    private Keyword(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
