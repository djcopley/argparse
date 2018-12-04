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
    }

    public void addArgument(Argument arg) {
        arguments.add(arg);
    }

    public void printHelp() {
        StringBuilder posArgsHelp = new StringBuilder();
        StringBuilder optArgsHelp = new StringBuilder();
        StringBuilder usageBanner = new StringBuilder("Usage: java MyProgram");

        if (!arguments.isEmpty()) {
            for (Argument arg : arguments) {
                if (arg instanceof RequiredArgument) {
                    if (posArgsHelp.length() == 0) {
                        // If nothing has been added to the positional arguments help string, added a title
                        posArgsHelp.append("\nPositional Arguments:");
                    }
                    posArgsHelp.append("\n").append(arg);
                    usageBanner.append(" ").append(arg.getArgument());
                } else if (arg instanceof OptionalArgument) {
                    if (optArgsHelp.length() == 0) {
                        // If nothing has been added to the optional arguments help string, added a title
                        optArgsHelp.append("\nOptional Arguments:");
                    }
                    optArgsHelp.append("\n").append(arg);
                    // Format optional arguments inside square brackets
                    usageBanner.append(" ").append("[").append(arg.getArgument()).append("]");
                }
            }
        }

        System.out.println(usageBanner);
        System.out.println(posArgsHelp);
        System.out.println(optArgsHelp);
    }

    public LinkedList<Argument> parseArguments() {
        LinkedList<Argument> presentArgs = new LinkedList<>();
        // Parse arguments
        return null;
    }

    public String toString() {
        return description;
    }
}