package com.example.chapter08;

import com.beust.jcommander.Parameter;
import lombok.ToString;

@ToString
public class MasterParameter {

    @Parameter(names = "-master")
    private String master;

}
