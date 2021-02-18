package com.example.chapter20;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(resourceBundle = "MessageBundle")
public class CommandParameters {

    @Parameter(names = "-host", description = "Host", descriptionKey = "host")
    private String host;

}
