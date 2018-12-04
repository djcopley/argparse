package argparse.arguments;

public abstract class Argument {
    private boolean argumentPassed = false;
    String help;
    String argument;

    Argument(String argument, String help) {
        this.help = help;
        this.argument = argument;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgumentPassed() {
        this.argumentPassed = true;
    }

    public boolean isArgumentPassed() {
        return argumentPassed;
    }

    @Override
    public String toString() {
        return String.format("%s\t\t%s", argument, help);
    }
}
