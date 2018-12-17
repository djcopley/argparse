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
 * @version 0.3
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
        arguments.add(helpArg);
    }

    /**
     * Method adds argument to parser.
     *
     * @param arg Argument object added to parser
     */
    public void addArgument(Argument arg) {
        if (arguments.contains(arg)) {
            throw new DuplicateOptionException();
        }
        arguments.add(arg);
    }

    /**
     * ArgumentList inner class extends ArrayList adding two key features. First, the method containsArg, returns
     * true if argument token / alias matches an inputted string. Second, the method checkArgPassed, sets
     * argumentPassed field to true if matching token / alias is found.
     *
     * @param <E> generic data type; must be a descendent of Argument
     */
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

    /**
     * Method prints help message. This method is called when either the "help" flag is passed as an argument or not all
     * required arguments are specified.
     */
    private void printHelp() {
        // Argument help strings to be printed
        StringBuilder posArgsHelp = new StringBuilder();
        StringBuilder optArgsHelp = new StringBuilder();

        // Argument names to be sorted for usage banner
        StringBuilder posArgs = new StringBuilder();
        StringBuilder optArgs = new StringBuilder();

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

        System.out.println("Usage: java MyProgram" + posArgs + optArgs);

        System.out.print("\nPositional Arguments:");
        System.out.println(posArgsHelp);

        System.out.print("\nOptional Arguments:");
        System.out.println(optArgsHelp);

        System.exit(0);
    }

    /**
     * Method parses arguments and returns an ArrayList of all arguments.
     *
     * @return ArrayList of all arguments
     */
    public ArrayList<Argument> parseArguments() {

        // Cover passed optional arguments
        for (String arg : args) {
            arguments.checkArgPassed(arg);
        }

        // If help flag is passed, print help menu and quit
        if (helpArg.isPassed()) {
            printHelp();
        }

        // Parse positional args
        int posArgIndex = 0;
        for (Argument arg : arguments) {
            if (arg instanceof RequiredArgument) {
                if (posArgIndex >= args.length || arguments.containsArg(args[posArgIndex])) {
                    System.out.println(String.format("Positional argument \"%s\" not specified\n", arg.getToken()));
                    printHelp();
                } else {
                    ((PositionalArgument) arg).setInput(args[posArgIndex++]);
                    arg.setPassed();
                }
            }
        }

        return arguments;
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