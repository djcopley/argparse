import argparse.*;
import argparse.arguments.*;

public class Main {
    public static void main(String[] args) {
        Argument arg1 = new FlagArgument("--test", "-t", "Test argument");
        Argument arg2 = new PositionalArgument("foo", "Test argument");
        Argument arg3 = new FlagArgument("--tesst", "Test argument");

        ArgumentParser parser = new ArgumentParser("Argument parser", args);
        parser.addArgument(arg1);
        parser.addArgument(arg2);
        parser.addArgument(arg3);

        parser.printHelp();
    }
}