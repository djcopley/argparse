package argparse;

import java.util.LinkedList;

import argparse.arguments.*;

public class ArgumentParser {
    private String description;
    private String[] args;
    private LinkedList<Argument> arguments = new LinkedList<>();

    public ArgumentParser(String description, String[] args) {
        this.description = description;
        this.args = args;
        arguments.add(new FlagArgument("--help" , "-h", "show this help message and exit"));
    }

    public void addArgument(Argument arg) {
        arguments.add(arg);
    }

    public void printHelp() {
        // Argument help strings to be printed
        StringBuilder posArgsHelp = new StringBuilder();
        StringBuilder optArgsHelp = new StringBuilder();

        // Argument names to be sorted for usage banner
        StringBuilder posArgs = new StringBuilder();
        StringBuilder optArgs = new StringBuilder();

        if (!arguments.isEmpty()) {
            for (Argument arg : arguments) {
                if (arg instanceof RequiredArgument) {
                    if (posArgsHelp.length() == 0) {
                        // If nothing has been added to the positional arguments help string, added a title
                        posArgsHelp.append("\nPositional Arguments:");
                    }
                    posArgsHelp.append("\n").append(arg);
                    posArgs.append(" ").append(arg.getArgument());
                } else if (arg instanceof OptionalArgument) {
                    if (optArgsHelp.length() == 0) {
                        // If nothing has been added to the optional arguments help string, added a title
                        optArgsHelp.append("\nOptional Arguments:");
                    }
                    optArgsHelp.append("\n").append(arg);
                    // Format optional arguments inside square brackets
                    optArgs.append(" ").append("[").append(arg.getArgument()).append("]");
                }
            }
        }

        String usageBanner = "\nUsage: java MyProgram" + posArgs + optArgs;

        System.out.println(usageBanner);
        System.out.println(posArgsHelp);
        System.out.println(optArgsHelp);
    }

    public LinkedList<Argument> parseArguments() {
        return null;
    }

    public String toString() {
        return description;
    }
}