package com.example.chapter10.section01;

import com.beust.jcommander.Parameter;
import lombok.ToString;

import java.util.List;

@ToString
public class CommandParameters {

    @Parameter(names = "-pairs", arity = 2, description = "Pairs")
    private List<String> pairs;

}
