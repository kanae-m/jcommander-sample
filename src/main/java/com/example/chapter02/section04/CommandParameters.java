package com.example.chapter02.section04;

import com.beust.jcommander.Parameter;
import lombok.ToString;

@ToString
public class CommandParameters {

    @Parameter(names = "-password", description = "Connection password", password = true, echoInput = true)
    private String password;

}
