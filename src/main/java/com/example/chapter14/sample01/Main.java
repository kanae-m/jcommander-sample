package com.example.chapter14.sample01;

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

        String[] argv = {"-level", "5"};
        commander.parse(argv);
        SystemOut.println(argv);
        SystemOut.println(parameters);
    }

}
