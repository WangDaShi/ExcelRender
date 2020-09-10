package com.loatr.excel;

import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * 根据赋值表达式从数据集合中提取出对应的值。
 * 表达式支持四种语法：
 * a.b : 从bean对象a中获取属性b的值
 * a.method(): 调用对象a的method()方法并返回返回值
 * 结合律: 以上语法可以相互组合(例： a.b.c.m1())
 */
public class ValueExtractor {

    public static Object extract(String expression,Map<String,Object> data){
        String[] parts = expression.split("\\.");
        Object value = data.get(parts[0]);
        for(int i = 1;i< parts.length;i++){
            String ex = parts[i];
            if(ex.endsWith("()")){
                value = getMethodValue(value,ex.substring(0,ex.length()-2));
            }else{
                value = getProperty(value,ex);
            }
        }
        return value;
    }

    private static Object getProperty(Object value, String ex) {
        PropertyDescriptor descriptor = BeanUtils.getPropertyDescriptor(value.getClass(), ex);
        try {
            return descriptor.getReadMethod().invoke(value);
        } catch (IllegalAccessException | InvocationTargetException  e) {
            e.printStackTrace();
            throw new RuntimeException("属性不存在");
        }
    }

    private static Object getMethodValue(Object value, String ex) {
        Method method = BeanUtils.findMethod(value.getClass(), ex);
        try {
            return method.invoke(value);
        } catch (IllegalAccessException | InvocationTargetException  e) {
            e.printStackTrace();
            throw new RuntimeException("属性不存在");
        }
    }

}
