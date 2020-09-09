package com.loatr.excel;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.Arrays;

/**
 * json文件对应的bean对象
 */
public class JsonConfig {

    private Integer sheet;
    private String[] data;
    private JsonNode[] mappers;

    @Override
    public String toString() {
        return "JsonConfig{" +
                "sheet=" + sheet +
                ", data=" + Arrays.toString(data) +
                ", mappers=" + Arrays.toString(mappers) +
                '}';
    }

    public Integer getSheet() {
        return sheet;
    }

    public void setSheet(Integer sheet) {
        this.sheet = sheet;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public JsonNode[] getMappers() {
        return mappers;
    }

    public void setMappers(JsonNode[] mappers) {
        this.mappers = mappers;
    }
}
