package com.example.chapter01.sample01;

import com.beust.jcommander.JCommander;
import com.example.utils.SystemOut;

public class Main {

    public static void main(String[] args) {
        CommandParameters parameters = new CommandParameters();
        JCommander commander = JCommander.newBuilder()
                .addObject(parameters)
                .build();
        commander.usage();

        String[] argv = {"-log", "2", "-groups", "unit"};
        commander.parse(argv);

        SystemOut.println(argv);
        SystemOut.println(parameters);
    }

}
