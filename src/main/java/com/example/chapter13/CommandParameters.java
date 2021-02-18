package com.example.chapter13;

import com.beust.jcommander.Parameter;
import lombok.ToString;

@ToString
public class CommandParameters {

    @Parameter(names = "-host", required = true)
    private String host;

}
