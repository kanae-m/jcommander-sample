package com.example.chapter11;

import com.beust.jcommander.Parameter;
import lombok.ToString;

@ToString
public class CommandParameters {

    @Parameter(names = {"-d", "--outputDirectory"}, description = "Directory")
    private String outputDirectory;

}
