package com.example.chapter03.section01.item02;

import com.beust.jcommander.IStringConverter;
import com.beust.jcommander.IStringConverterFactory;

public class Factory implements IStringConverterFactory {

    @Override
    public Class<? extends IStringConverter<?>> getConverter(Class forType) {
        if (forType.equals(HostPort.class)) {
            return HostPortConverter.class;
        }
        return null;
    }

}
