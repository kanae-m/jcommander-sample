package com.example.chapter04;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.example.utils.SystemOut;

public class Main {

    public static void main(String[] args) {
        CommandParameters parameters = new CommandParameters();
        JCommander commander = JCommander.newBuilder()
                .addObject(parameters)
                .build();
        commander.usage();

        String[] argvErr = {"-age", "-1"};
        try {
            commander.parse(argvErr);
        } catch (ParameterException e) {
            SystemOut.println(argvErr);
            SystemOut.printError(e);
        }

        String[] argv = {"-age", "1"};
        commander.parse(argv);

        SystemOut.println(argv);
        SystemOut.println(parameters);
    }

}
