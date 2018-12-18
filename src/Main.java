import argparse.*;
import argparse.arguments.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        PositionalArgument arg1 = new PositionalArgument("foo", "Test argument");
        FlagInputArgument arg2 = new FlagInputArgument("--input", "-i", "Test argument");
        FlagArgument arg3 = new FlagArgument("--verbose", "-v", "Test argument");

        ArgumentParser parser = new ArgumentParser("Argument parser", args);

        parser.addArgument(arg1);
        parser.addArgument(arg2);
        parser.addArgument(arg3);

        ArrayList<Argument> t = parser.parseArguments();

        System.out.println("Positional argument input:");
        System.out.println(arg1.getInput());

        System.out.println("\nFlag argument input:");
        System.out.println(arg2.getInput());

        System.out.println("\nArguments passed:");
        for (Argument a : t) {
            if (a.isPassed()) {
                System.out.println(a.getToken());
            }
        }
    }
}
