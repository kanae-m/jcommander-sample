package com.example.utils;

import java.util.Arrays;

public class SystemOut {

    public static void println(String[] strArray) {
        println(Arrays.toString(strArray));
    }

    public static void printError(Exception e) {
        println("Error: " + e.getMessage());
    }

    public static void println(Object str) {
        System.out.println(str);
    }

    private SystemOut() {}

}
