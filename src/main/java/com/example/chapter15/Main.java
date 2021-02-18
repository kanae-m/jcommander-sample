package com.example.chapter15;

import com.beust.jcommander.JCommander;
import com.example.utils.SystemOut;

public class Main {

    public static void main(String[] args) {
        CommandParameters parameters = new CommandParameters();
        JCommander commander = JCommander.newBuilder()
                .addObject(parameters)
                .build();
        commander.usage();

        commander.parse();
        SystemOut.println(new String[]{});
        SystemOut.println(parameters);

        String[] argv = {"--help"};
        commander.parse(argv);
        SystemOut.println(argv);
        SystemOut.println(parameters);
    }

}
