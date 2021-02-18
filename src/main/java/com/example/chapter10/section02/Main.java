package com.example.chapter10.section02;

import com.beust.jcommander.JCommander;
import com.example.utils.SystemOut;

public class Main {

    public static void main(String[] args) {
        CommandParameters parameters = new CommandParameters();
        JCommander commander = JCommander.newBuilder()
                .addObject(parameters)
                .build();
        commander.usage();

        String[] argv1 = {"-foo", "a1", "-bar"};
        commander.parse(argv1);
        SystemOut.println(argv1);
        SystemOut.println(parameters);

        String[] argv2 = {"-foo", "a1", "a2", "-bar"};
        commander.parse(argv2);
        SystemOut.println(argv2);
        SystemOut.println(parameters);
    }

}
