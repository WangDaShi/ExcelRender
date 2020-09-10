package com.loatr.excel.format;

public interface ValueFormatter {

    /**
     * 把传入的值转换成指定格式的字符串
     * @param value 待输出的值
     * @return
     */
    String format(Object value);

}
