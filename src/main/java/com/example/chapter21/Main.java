package com.example.chapter21;

import com.beust.jcommander.JCommander;
import com.example.utils.SystemOut;

public class Main {

    public static void main(String[] args) {
        CommandParameters parameters = new CommandParameters();
        JCommander commander = JCommander.newBuilder()
                .addObject(parameters)
                .build();
        commander.usage();

        String[] argv = {"-v", "-port", "1234"};
        commander.parse(argv);
        SystemOut.println(argv);
        SystemOut.println(parameters);
    }

}
