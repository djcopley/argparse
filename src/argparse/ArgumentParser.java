package argparse;

import java.util.LinkedList;

import argparse.arguments.*;

public class ArgumentParser {
    private String description;
    private String[] args;
    private LinkedList<Argument> optionalArguments = new LinkedList<>();
    private LinkedList<Argument> positionalArguments = new LinkedList<>();

    public ArgumentParser(String description, String[] args) {
        this.description = description;
        this.args = args;
    }

    public void addArgument(Argument arg) {
        if (arg instanceof OptionalArgument) {
            optionalArguments.add(arg);
        } else if (arg instanceof RequiredArgument) {
            positionalArguments.add(arg);
        }
    }

    public void printHelp() {
        StringBuilder posArgsHelp = new StringBuilder();
        StringBuilder optArgsHelp = new StringBuilder();
        StringBuilder usageBanner = new StringBuilder("Usage: java MyProgram");

        if (!positionalArguments.isEmpty()) {
            posArgsHelp.append("\nPositional Arguments:");
            for (Argument arg : positionalArguments) {
                posArgsHelp.append("\n").append(arg);
                usageBanner.append(" ").append(arg.getArgument());
            }
        }

        if (!optionalArguments.isEmpty()) {
            optArgsHelp.append("\nOptional Arguments:");
            for (Argument arg : optionalArguments) {
                optArgsHelp.append("\n").append(arg);
                usageBanner.append(" ").append("[").append(arg.getArgument()).append("]");
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