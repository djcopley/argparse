package argparse;

import java.util.ArrayList;
import java.util.Collection;

import argparse.arguments.*;
import argparse.arguments.exceptions.DuplicateOptionException;

/**
 * Class ArgumentParser, contains methods and fields to parse input from the command line. The goal of this parser
 * is to be as versatile as possible, providing interfaces to enable: flag arguments, positional arguments, option
 * arguments, and more.
 *
 * @author Daniel Copley
 * @version 0.5
 */
public class ArgumentParser {
    /**
     * Description of the argument parser / program.
     */
    private String description;

    /**
     * Array of string arguments to parse. Typically these arguments are passed to the main function from the command
     * line; however, this can be any string array.
     */
    private String[] args;

    /**
     * ArrayList of Arguments. These are the arguments that the parser looks for.
     */
    private ArgumentList<Argument> arguments = new ArgumentList<>();

    /**
     * The default help argument. If help flag specified, the help menu will be printed and the program will exit.
     */
    private Argument helpArg = new FlagArgument("--help", "-h", "Show this help message and exit");

    /**
     * Constructor initializes description and input arguments.
     *
     * @param description string description of the argument parser / program
     * @param args        a string array of arguments to parse
     */
    public ArgumentParser(String description, String[] args) {
        this.description = description;
        this.args = args;
    }

    /**
     * Method adds argument to parser.
     *
     * @param arg Argument object added to parser
     */
    public void addArgument(Argument arg) {
        if (arguments.containsArg(arg)) {
            throw new DuplicateOptionException();
        }
        arguments.add(arg);
    }

    /**
     * ArgumentList inner class extends ArrayList adding two key features. First, the method containsStringArg, returns
     * true if argument token / alias matches an inputted string. Second, the method checkArgPassed, sets
     * argumentPassed field to true if matching token / alias is found.
     *
     * @param <E> generic data type; must be a descendent of Argument
     */
    private class ArgumentList<E extends Argument> extends ArrayList<E> {
        ArgumentList() {
        }

        ArgumentList(int initialCapacity) {
            super(initialCapacity);
        }

        ArgumentList(Collection<? extends E> c) {
            super(c);
        }

        boolean containsStringArg(String stringArg) {
            for (E arg : this) {
                if (arg.stringArgEquals(stringArg)) {
                    return true;
                }
            }
            return false;
        }

        boolean containsArg(Argument argument) {
            for (E arg : this) {
                if (arg.argEquals(argument)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Method prints help message. This method is called when either the "help" flag is passed as an argument or not all
     * required arguments are specified.
     */
    public void printHelp() {
        // Argument help strings to be printed
        StringBuilder posArgsHelp = new StringBuilder();
        StringBuilder optArgsHelp = new StringBuilder();

        // Argument names to be sorted for usage banner
        StringBuilder posArgs = new StringBuilder();
        StringBuilder optArgs = new StringBuilder();

        for (Argument arg : arguments) {
            if (arg instanceof RequiredArgument) {
                posArgsHelp.append("\n").append(arg);
                posArgs.append(" ").append(arg.getUsage());
            } else if (arg instanceof OptionalArgument) {
                // Format optional arguments inside square brackets
                optArgsHelp.append("\n").append(arg);
                optArgs.append(" ").append("[").append(arg.getUsage()).append("]");
            }
        }

        System.out.println("Usage: java MyProgram" + posArgs + optArgs);

        if (posArgsHelp.length() > 0) {
            System.out.print("\nPositional Arguments:");
            System.out.println(posArgsHelp);
        }
        if (optArgsHelp.length() > 0) {
            System.out.print("\nOptional Arguments:");
            System.out.println(optArgsHelp);
        }

        System.exit(0);
    }

    /**
     * Getter method for argument ArrayList
     *
     * @return arguments
     */
    public ArrayList<Argument> getArguments() {
        return arguments;
    }

    /**
     * Method parses arguments and returns an ArrayList of all arguments.
     */
    public void parseArguments() {
        helpArg.resolveArgument(args);
        if (helpArg.isPassed()) {
            printHelp();
        }
        for (Argument arg : arguments) {
            boolean success = arg.resolveArgument(args);
            if (!success) {
                System.out.println(String.format("Argument \"%s\" not used, or used incorrectly. See usage.\n",
                        arg.getToken()));
                printHelp();
            }
        }
    }

    /**
     * Method returns the string description of the ArgumentParser class.
     *
     * @return string description of ArgumentParser class
     */
    @Override
    public String toString() {
        return description;
    }
}