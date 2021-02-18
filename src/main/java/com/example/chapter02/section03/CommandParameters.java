package com.example.chapter02.section03;

import com.beust.jcommander.Parameter;
import lombok.ToString;

@ToString
public class CommandParameters {

    @Parameter(names = "-password", description = "Connection password", password = true)
    private String password;

}
