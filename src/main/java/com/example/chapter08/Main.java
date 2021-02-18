package com.example.chapter08;

import com.beust.jcommander.JCommander;
import com.example.utils.SystemOut;

public class Main {

    public static void main(String[] args) {
        MasterParameter masterParam = new MasterParameter();
        SlaveParameter slaveParam = new SlaveParameter();
        JCommander commander = JCommander.newBuilder()
                .addObject(new Object[]{masterParam, slaveParam})
                .build();
        commander.usage();

        String[] argv = {"-master", "master", "-slave", "slave"};
        commander.parse(argv);

        SystemOut.println(argv);
        SystemOut.println(masterParam);
        SystemOut.println(slaveParam);
    }

}
