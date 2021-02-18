package com.example.chapter18;

import com.beust.jcommander.JCommander;

public class Main {

    public static void main(String[] args) {
        CommandParameters parameters = new CommandParameters();
        JCommander commander = JCommander.newBuilder()
                .addObject(parameters)
                .build();
        commander.setProgramName("program_name");
        commander.usage();
    }

}
