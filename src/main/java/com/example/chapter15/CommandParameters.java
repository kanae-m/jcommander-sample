package com.example.chapter15;

import com.beust.jcommander.Parameter;
import lombok.ToString;

@ToString
public class CommandParameters {

    @Parameter(names = "--name")
    private String name;

    @Parameter(names = "--help", help = true)
    private boolean help;

}
