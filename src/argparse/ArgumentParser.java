package argparse;

import java.util.ArrayList;
import java.util.Collection;

import argparse.arguments.*;
import argparse.arguments.exceptions.DuplicateOptionException;

public class ArgumentParser {
    private String description;
    private String[] args;
    private ArgumentList<Argument> arguments = new ArgumentList<>();
    private Argument helpArg = new FlagArgument("--help", "-h", "Show this help message and exit");

    public ArgumentParser(String description, String[] args) {
        this.description = description;
        this.args = args;
        arguments.add(helpArg);
    }

    public void addArgument(Argument arg) {
        if (arguments.contains(arg)) {
            throw new DuplicateOptionException();
        }
        arguments.add(arg);
    }

    private class ArgumentList<E extends Argument> extends ArrayList<E> {
        ArgumentList(int initialCapacity) {
            super(initialCapacity);
        }

        ArgumentList() {
        }

        ArgumentList(Collection<? extends E> c) {
            super(c);
        }

        boolean containsArg(String stringArg) {
            for (E arg : this) {
                if (arg.argEquals(stringArg)) {
                    return true;
                }
            }
            return false;
        }

        void checkArgPassed(String stringArg) {
            for (E arg : this) {
                if (arg.argEquals(stringArg)) {
                    arg.setPassed();
                }
            }

        }
    }

    private void printHelp() {
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

        System.exit(0);
    }

    public ArrayList<Argument> parseArguments() {

        // If help flag is passed, print help menu and quit
        if (helpArg.isPassed()) {
            printHelp();
        }

        // Parse positional args
        int posArgIndex = 0;
        for (Argument arg : arguments) {
            if (arg instanceof PositionalArgument) {
                if (args.length == 0) {
                    System.out.println("Positional arguments not specified\n");
                    printHelp();
                }
                if (!arguments.containsArg(args[posArgIndex])) {
                    ((PositionalArgument) arg).setInput(args[posArgIndex++]);
                    arg.setPassed();
                } else {
                    System.out.println(String.format("Positional argument \"%s\" not specified\n", arg.getArgument()));
                    printHelp();
                }
            }
        }

        // Cover passed optional arguments
        for (String arg : args) {
            arguments.checkArgPassed(arg);
        }

        return arguments;
    }

    @Override
    public String toString() {
        return description;
    }
}