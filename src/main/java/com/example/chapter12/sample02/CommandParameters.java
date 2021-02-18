package com.example.chapter12.sample02;

import com.beust.jcommander.Parameter;
import lombok.ToString;

@ToString
public class CommandParameters {

    @Parameter(names = "-param")
    private String param;

}
