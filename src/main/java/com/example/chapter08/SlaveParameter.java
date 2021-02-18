package com.example.chapter08;

import com.beust.jcommander.Parameter;
import lombok.ToString;

@ToString
public class SlaveParameter {

    @Parameter(names = "-slave")
    private String slave;

}
