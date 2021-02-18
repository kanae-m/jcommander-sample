package com.example.chapter02.section02;

import com.beust.jcommander.Parameter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class CommandParameters {

    @Parameter(names = "-host", description = "The host")
    private List<String> hosts = new ArrayList<>();

}
