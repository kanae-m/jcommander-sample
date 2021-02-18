package com.example.chapter19;

import com.beust.jcommander.JCommander;

public class Main {

    public static void main(String[] args) {
        CommandParameters parameters = new CommandParameters();
        JCommander commander = JCommander.newBuilder()
                .addObject(parameters)
                .build();
        commander.usage();
    }

}
