package com.example.chapter02.section01.sample01;

import com.beust.jcommander.Parameter;
import lombok.ToString;

@ToString
public class CommandParameters {

    @Parameter(names = "-debug", description = "Debug mode")
    private boolean debug = false;

}
