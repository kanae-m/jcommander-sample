package com.example.chapter19;

import com.beust.jcommander.Parameter;

public class CommandParameters {

    @Parameter(names = "-name", description = "your name")
    private String name;

    @Parameter(names = "-debug", description = "Debug mode", hidden = true)
    private boolean debug = false;

}
