package com.example.chapter03.section01.item02;

import com.beust.jcommander.IStringConverter;

import java.util.List;

public class HostPortConverter implements IStringConverter<HostPort> {

    @Override
    public HostPort convert(String value) {
        List<String> strList = List.of(value.split(":"));
        return new HostPort(strList.get(0), Integer.parseInt(strList.get(1)));
    }

}
