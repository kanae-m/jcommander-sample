package com.example.chapter01.sample02;

import com.beust.jcommander.Parameter;
import lombok.ToString;

@ToString
public class CommandParameters {

    @Parameter(names = {"--length", "-l"})
    private int length;

    @Parameter(names = {"--pattern", "-p"})
    private int pattern;

}
