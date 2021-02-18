package com.example.chapter21;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParametersDelegate;
import lombok.ToString;

@ToString
public class CommandParameters {

    @Parameter(names = "-v")
    private boolean verbose;

    @ParametersDelegate
    private Delegate delegate = new Delegate();

}
