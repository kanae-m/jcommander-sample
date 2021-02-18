package com.example.chapter16;

import com.beust.jcommander.Parameter;
import lombok.ToString;

@ToString
public class CommandParameters {

    @Parameter(names = "-v")
    private boolean verbose;

}
