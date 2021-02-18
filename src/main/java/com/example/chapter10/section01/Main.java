package com.example.chapter10.section01;

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

        String[] argvErr1 = {"-pairs", "slave"};
        try {
            commander.parse(argvErr1);
        } catch (ParameterException e) {
            SystemOut.println(argvErr1);
            SystemOut.printError(e);
        }

        String[] argv = {"-pairs", "slave", "master"};
        commander.parse(argv);
        SystemOut.println(argv);
        SystemOut.println(parameters);

        String[] argvErr2 = {"-pairs", "slave", "master", "foo.xml"};
        try {
            commander.parse(argvErr2);
        } catch (ParameterException e) {
            SystemOut.println(argvErr2);
            SystemOut.printError(e);
        }
    }

}
