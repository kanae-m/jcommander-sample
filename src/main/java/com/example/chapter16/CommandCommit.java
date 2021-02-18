package com.example.chapter16;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import lombok.ToString;

import java.util.List;

@ToString
@Parameters(separators = "=", commandDescription = "Record changes to the repository")
public class CommandCommit {

    @Parameter(description = "The list of files to commit")
    private List<String> files;

    @Parameter(names = "--amend", description = "Amend")
    private boolean amend = false;

    @Parameter(names = "--author")
    private String author;

}
