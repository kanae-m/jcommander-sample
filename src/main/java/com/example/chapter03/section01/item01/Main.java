package com.example.chapter03.section01.item01;

import com.beust.jcommander.JCommander;
import com.example.utils.SystemOut;

public class Main {

    public static void main(String[] args) {
        CommandParameters parameters = new CommandParameters();
        JCommander commander = JCommander.newBuilder()
                .addObject(parameters)
                .build();
        commander.usage();

        String[] argv = {"-file", "/tmp/file"};
        commander.parse(argv);

        SystemOut.println(argv);
        SystemOut.println(parameters);
    }

}
