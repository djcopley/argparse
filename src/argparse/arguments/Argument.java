package argparse.arguments;

public abstract class Argument {
    private String help;
    private String argument;
    private boolean argumentPassed = false;

    Argument(String argument, String help) {
        this.help = help;
        this.argument = argument;
    }

    public String getHelp() {
        return help;
    }

    public String getArgument() {
        return argument;
    }

    public String getKeyword() {
        return argument;
    }

    public void setPassed() {
        this.argumentPassed = true;
    }

    public boolean isPassed() {
        return argumentPassed;
    }

    public boolean argEquals(String arg) {
        return arg.equals(getArgument());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Argument)) {
            return false;
        }
        return getArgument().equals(((Argument) other).getArgument());
    }

    @Override
    public String toString() {
        return String.format("%s\t\t%s", getArgument(), getHelp());
    }
}
