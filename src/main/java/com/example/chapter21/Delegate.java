package com.example.chapter21;

import com.beust.jcommander.Parameter;
import lombok.ToString;

@ToString
public class Delegate {

    @Parameter(names = "-port")
    private int port;

}
