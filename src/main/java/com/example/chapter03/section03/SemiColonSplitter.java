package com.example.chapter03.section03;

import com.beust.jcommander.converters.IParameterSplitter;

import java.util.List;

public class SemiColonSplitter implements IParameterSplitter {

    @Override
    public List<String> split(String value) {
        return List.of(value.split(";"));
    }

}
