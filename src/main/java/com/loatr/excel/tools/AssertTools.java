package com.loatr.excel.tools;

import java.io.File;
import java.util.Objects;

public final class AssertTools {

    public static void assertFalse(boolean r,String message){
        assertTrue(!r,message);
    }

    public static void assertTrue(boolean r,String message){
        if(!r){
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertExist(File file,String message){
        Objects.requireNonNull(file,message);
        if(!file.exists()){
            throw new IllegalArgumentException(message);
        }
    }

    public static void assertEachNonNull(Object[] arr,String message){
        for(Object obj : arr){
            Objects.requireNonNull(obj,message);
        }
    }
}
