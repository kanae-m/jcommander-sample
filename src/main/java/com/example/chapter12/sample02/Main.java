package com.example.chapter12.sample02;

import com.beust.jcommander.JCommander;
import com.example.utils.SystemOut;

public class Main {

    public static void main(String[] args) {
        CommandParameters parameters = new CommandParameters();
        JCommander commander = JCommander.newBuilder()
                .addObject(parameters)
                .build();
        commander.setAllowAbbreviatedOptions(true);
        commander.usage();

        String[] argv = {"-par", "param"};
        commander.parse(argv);
        SystemOut.println(argv);
        SystemOut.println(parameters);
    }

}
