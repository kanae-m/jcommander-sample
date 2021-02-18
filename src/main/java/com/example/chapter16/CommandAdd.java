package com.example.chapter16;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import lombok.ToString;

import java.util.List;

@ToString
@Parameters(commandDescription = "Add file contents to the index")
public class CommandAdd {

    @Parameter(description = "File patterns to add to the index")
    private List<String> patterns;

    @Parameter(names = "-i")
    private boolean interactive = false;

}
