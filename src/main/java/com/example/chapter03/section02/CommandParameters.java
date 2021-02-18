package com.example.chapter03.section02;

import com.beust.jcommander.Parameter;
import lombok.ToString;

import java.io.File;
import java.util.List;

@ToString
public class CommandParameters {

    @Parameter(names = "-files", listConverter = FileListConverter.class)
    private List<File> files;

}
