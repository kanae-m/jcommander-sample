package com.example.chapter05;

import com.beust.jcommander.Parameter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class CommandParameters {

    @Parameter(description = "Files")
    private List<String> files = new ArrayList<>();

    @Parameter(names = "-debug", description = "Debugging level")
    private boolean debug = false;

}
