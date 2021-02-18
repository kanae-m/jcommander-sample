package com.example.chapter22;

import com.beust.jcommander.DynamicParameter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
public class CommandParameters {

    @DynamicParameter(names = "-D", description = "Dynamic parameters go here")
    private Map<String, String> params = new HashMap<>();

}
