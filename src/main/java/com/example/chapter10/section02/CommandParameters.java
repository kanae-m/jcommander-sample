package com.example.chapter10.section02;

import com.beust.jcommander.Parameter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class CommandParameters {

    @Parameter(names = "-foo", variableArity = true)
    public List<String> foo = new ArrayList<>();

    @Parameter(names = "-bar")
    public boolean bar = false;

}
