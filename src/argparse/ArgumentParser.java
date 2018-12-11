package argparse;

import java.util.ArrayList;

import argparse.arguments.*;
import argparse.arguments.exceptions.DuplicateOptionException;

public class ArgumentParser {
    private String description;
    private String[] args;
    private ArrayList<Argument> arguments = new ArrayList<>();

    public ArgumentParser(String description, String[] args) {
        this.description = description;
        this.args = args;
        arguments.add(new FlagArgument("--help", "-h", "Show this help message and exit"));
    }

    public void addArgument(Argument arg) {
        if (arguments.contains(arg)) {
            throw new DuplicateOptionException();
        }
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
                    posArgsHelp.append("\n").append(arg);
                    posArgs.append(" ").append(arg.getKeyword());
                } else if (arg instanceof OptionalArgument) {
                    // Format optional arguments inside square brackets
                    optArgsHelp.append("\n").append(arg);
                    optArgs.append(" ").append("[").append(arg.getKeyword()).append("]");
                }
            }
        }

        System.out.println("Usage: java MyProgram" + posArgs + optArgs);

        System.out.print("\nPositional Arguments:");
        System.out.println(posArgsHelp);

        System.out.print("\nOptional Arguments:");
        System.out.println(optArgsHelp);
    }

    public ArrayList<Argument> parseArguments() {
        return null;
    }

    public String toString() {
        return description;
    }
}