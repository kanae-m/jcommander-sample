package com.example.chapter03.section01.item01;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;
import lombok.ToString;

import java.io.File;

@ToString
public class CommandParameters {

    @Parameter(names = "-file", converter = FileConverter.class)
    private File file;

}
