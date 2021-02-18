package com.example.chapter02.section01.sample02;

import com.beust.jcommander.Parameter;
import lombok.ToString;

@ToString
public class CommandParameters {

    @Parameter(names = "-debug", description = "Debug mode", arity = 1)
    private boolean debug = false;

}
