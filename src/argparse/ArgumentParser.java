package argparse;

import java.util.ArrayList;

import argparse.arguments.*;

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
	 * Name of program, will be print in usage of help.
	 */
	private final String usage;
	
    /**
     * Description of the argument parser / program.
     */
    private final String description;

    /**
     * Array of string arguments to parse. Typically these arguments are passed to the main function from the command
     * line; however, this can be any string array.
     */
    private String[] args;

    /**
     * ArrayList of Arguments. These are the arguments that the parser looks for.
     */
    private ArrayList<Argument> arguments = new ArrayList<>();

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
    	this.usage = "java " + new java.io.File(ArgumentParser.class.getProtectionDomain().getCodeSource().getLocation().getPath()).getName();
		this.description = description;
        this.args = args;
        
        arguments.add(helpArg);
    }
    
    /**
     * Constructor initializes description and input arguments.
     *
     * @param usage       custom usage name (e.g. 'java myProgram')
     * @param description string description of the argument parser / program
     * @param args        a string array of arguments to parse
     */
    public ArgumentParser(String usage, String description, String[] args) {
    	this.usage = usage;
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
    	if (containsArg(arg)) throw new IllegalArgumentException("duplicated option");
        arguments.add(arg);
    }
    
    
    /**
     * Method test if Arguement object is in the list.
     */
    public boolean containsArg(Argument argument) {
        for (Argument arg : arguments) {
            if (arg.argEquals(argument)) return true;
        }
        return false;
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
        
        System.out.println("Usage: " + usage + posArgs + optArgs);

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
        if (helpArg.isPassed()) printHelp();
        
        for (Argument arg : arguments) {
            if (!arg.resolveArgument(args)) {
                System.out.println(String.format("Argument \"%s\" not used, or used incorrectly. See usage.\n", arg.getToken()));
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