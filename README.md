# A Java Argument Parser

Parse command line arguments easily.

### Usage example

    import argparse.*;
    import argparse.arguments.*;

    public class Main {
        public static void main(String[] args) {
            ArgumentParser parser = new ArgumentParser("Test program", args);
    
            PositionalArgument arg1 = new PositionalArgument("file", "Enter the output filename");
            FlagInputArgument arg2 = new FlagInputArgument("--file", "-f", "The input file path");
            FlagArgument arg3 = new FlagArgument("--verbose", "-v", "Display verbose options");
            
            parser.addArgument(arg1);
            parser.addArgument(arg2);
            parser.addArgument(arg3);
    
            parser.parseArguments();
            
            // Prints true if arg1 is specified in args (PositionalArguments are required, this will always be true)
            System.out.println(arg1.isPassed());
            
            // Prints arg1's input
            System.out.println(arg1.getInput());
            
            // Prints true if arg2 is specified in args 
            System.out.println(arg2.isPassed());
            
            // Prints text arg2's input. Example: {"-f", "hello"} -> hello
            System.out.println(arg2.getInput());
            
            // Prints if arg3 is specified in args. Arg 3 is a FlagArgument and has no input. If getInput method is called, null is returned.
            System.out.println(arg3.isPassed());
        }
    }