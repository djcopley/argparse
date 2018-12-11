package argparse.arguments;

public abstract class Argument {
    private String help;
    private String argument;
    private boolean argumentPassed = false;

    Argument(String argument, String help) {
        this.help = help;
        this.argument = argument;
    }

    // TODO Think about the name of this and if there is a better way to check for args
    public abstract boolean contains(String[] args);

    public String getHelp() {
        return help;
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
    public boolean equals(Object other) {
        if (!(other instanceof Argument)) {
            return false;
        }
        return this.argument.equals(((Argument) other).argument);
    }

    @Override
    public String toString() {
        return String.format("%s\t\t%s", argument, help);
    }
}
