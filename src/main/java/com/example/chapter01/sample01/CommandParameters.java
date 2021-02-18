package com.example.chapter01.sample01;

import com.beust.jcommander.Parameter;
import lombok.ToString;

@ToString
public class CommandParameters {

    @Parameter(names = {"-log", "-verbose"}, description = "Level of verbosity")
    private int verbose;

    @Parameter(names = "-groups", description = "Comma-sperated list of group names to be run")
    private String groups;

    @Parameter(names = "-debug", description = "Debug mode")
    private boolean debug = false;

}
