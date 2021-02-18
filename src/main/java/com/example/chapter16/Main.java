package com.example.chapter16;

import com.beust.jcommander.JCommander;
import com.example.utils.SystemOut;

public class Main {

    public static void main(String[] args) {
        CommandParameters parameters = new CommandParameters();
        CommandAdd add = new CommandAdd();
        CommandCommit commit = new CommandCommit();
        JCommander commander = JCommander.newBuilder()
                .addObject(parameters)
                .addCommand("add", add)
                .addCommand("commit", commit)
                .build();
        commander.usage();

        String[] argv = {"-v", "commit", "--amend", "--author=cbeust", "A.class", "B.class"};
        commander.parse(argv);
        SystemOut.println(argv);
        SystemOut.println(parameters);
        SystemOut.println(add);
        SystemOut.println(commit);
    }

}
