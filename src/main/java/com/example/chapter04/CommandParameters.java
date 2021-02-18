package com.example.chapter04;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.validators.PositiveInteger;
import lombok.ToString;

@ToString
public class CommandParameters {

    @Parameter(names = "-age", validateWith = PositiveInteger.class)
    private int age;

}
