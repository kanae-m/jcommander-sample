package com.example.chapter03.section03;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;
import lombok.ToString;

import java.io.File;
import java.util.List;

@ToString
public class CommandParameters {

    @Parameter(names = "-files", converter = FileConverter.class, splitter = SemiColonSplitter.class)
    private List<File> files;

}
