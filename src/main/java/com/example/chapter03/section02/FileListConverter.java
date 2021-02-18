package com.example.chapter03.section02;

import com.beust.jcommander.IStringConverter;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class FileListConverter implements IStringConverter<List<File>> {

    @Override
    public List<File> convert(String value) {
        List<String> strList = List.of(value.split(","));
        return strList.stream()
                .map(File::new)
                .collect(Collectors.toList());
    }

}
