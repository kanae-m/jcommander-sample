package com.example.chapter01.sample02;

import com.beust.jcommander.JCommander;
import com.example.utils.SystemOut;

public class Main {

    public static void main(String[] args) {
        CommandParameters parameters = new CommandParameters();
        JCommander commander = JCommander.newBuilder()
                .addObject(parameters)
                .build();
        commander.usage();

        String[] argv = {"-l", "512", "--pattern", "2"};
        commander.parse(argv);

        SystemOut.println(argv);
        SystemOut.println(parameters);
    }

}
