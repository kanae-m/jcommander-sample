package com.example.chapter14.sample02;

import com.beust.jcommander.IDefaultProvider;
import com.beust.jcommander.JCommander;
import com.example.utils.SystemOut;

public class Main {

    private static final IDefaultProvider DEFAULT_PROVIDER = new IDefaultProvider() {
        @Override
        public String getDefaultValueFor(String optionName) {
            return "-debug".equals(optionName) ? "false" : "42";
        }
    };

    public static void main(String[] args) {
        CommandParameters parameters = new CommandParameters();
        JCommander commander = JCommander.newBuilder()
                .addObject(parameters)
                .defaultProvider(DEFAULT_PROVIDER)
                .build();
        commander.usage();

        commander.parse();
        SystemOut.println(new String[]{});
        SystemOut.println(parameters);
    }

}
