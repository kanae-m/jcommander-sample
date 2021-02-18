package com.example.chapter14.sample02;

import com.beust.jcommander.Parameter;
import lombok.ToString;

@ToString
public class CommandParameters {

    @Parameter(names = "-debug")
    private String debug;

    @Parameter(names = "-num1")
    private int num1;

    @Parameter(names = "-num2")
    private int num2;

}
