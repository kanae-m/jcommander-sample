package com.example.chapter12.sample01;

import com.beust.jcommander.Parameter;
import lombok.ToString;

@ToString
public class CommandParameters {

    @Parameter(names = "-name")
    private String name;

}
