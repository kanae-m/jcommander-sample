package com.example.chapter13;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.example.utils.SystemOut;

public class Main {

    public static void main(String[] args) {
        CommandParameters parameters = new CommandParameters();
        JCommander commander = JCommander.newBuilder()
                .addObject(parameters)
                .build();
        commander.usage();

        try {
            commander.parse();
        } catch (ParameterException e) {
            SystemOut.println(new String[]{});
            SystemOut.printError(e);
        }

    }

}
