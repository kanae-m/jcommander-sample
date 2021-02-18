package com.example.chapter14.sample01;

import com.beust.jcommander.Parameter;
import lombok.ToString;

@ToString
public class CommandParameters {

    @Parameter(names = "-level")
    private Integer logLevel = 3;

}
