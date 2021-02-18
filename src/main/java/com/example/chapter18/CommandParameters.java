package com.example.chapter18;

import com.beust.jcommander.Parameter;

public class CommandParameters {

    @Parameter(names = "-name", description = "your name", required = true, order = 1)
    private String name;

    @Parameter(names = "-age", description = "your age", order = 2)
    private int age;

}
