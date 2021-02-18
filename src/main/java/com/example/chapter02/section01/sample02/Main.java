package com.example.chapter02.section01.sample02;

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

        commander.parse();

        SystemOut.println(new String[]{});
        SystemOut.println(parameters);

        String[] argvErr = {"-debug"};
        try {
            commander.parse(argvErr);
        } catch (ParameterException e) {
            SystemOut.println(argvErr);
            SystemOut.printError(e);
        }

        String[] argv = {"-debug", "true"};
        commander.parse(argv);

        SystemOut.println(argv);
        SystemOut.println(parameters);
    }

}
