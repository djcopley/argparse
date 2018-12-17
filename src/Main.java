import argparse.*;
import argparse.arguments.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        FlagArgument arg1 = new FlagArgument("--test", "-t", "Test argument");
        PositionalArgument arg2 = new PositionalArgument("foo", "Test argument");
        FlagArgument arg3 = new FlagArgument("--tesst", "Test argument");
        PositionalArgument arg4 = new PositionalArgument("doink", "Test argument");
        PositionalArgument arg5 = new PositionalArgument("bar", "This argument is a positional argument");

        ArgumentParser parser = new ArgumentParser("Argument parser", args);
        parser.addArgument(arg1);
        parser.addArgument(arg2);
        parser.addArgument(arg3);
        parser.addArgument(arg4);
        parser.addArgument(arg5);

        ArrayList<Argument> t = parser.parseArguments();

        System.out.println("Positional argument input:");
        System.out.println(arg2.getInput());
        System.out.println(arg4.getInput());
        System.out.println(arg5.getInput());

        System.out.println("\nArguments passed:");
        for (Argument a : t) {
            if(a.isPassed()) {
                System.out.println(a.getToken());
            }
        }
    }
}
