package com.mudita.movies.http.utils;

public class StringUtils {

    public static boolean isNullOrEmpty(String s){
        return s == null || s.trim().length() == 0;
    }

    public static boolean isNull(long n){
        return n == 0;
    }
}
