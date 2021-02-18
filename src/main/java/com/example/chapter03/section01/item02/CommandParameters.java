package com.example.chapter03.section01.item02;

import com.beust.jcommander.Parameter;
import lombok.ToString;

@ToString
public class CommandParameters {

    @Parameter(names = "-hostport")
    private HostPort hostPort;

}
