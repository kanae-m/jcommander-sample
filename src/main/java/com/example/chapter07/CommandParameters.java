package com.example.chapter07;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import lombok.ToString;

@ToString
@Parameters(separators = "=")
public class CommandParameters {

    @Parameter(names = "-level")
    private int level = 2;

}
