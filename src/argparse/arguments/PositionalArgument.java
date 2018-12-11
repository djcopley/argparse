package argparse.arguments;

public class PositionalArgument extends RequiredArgument {
    private String input;

    public PositionalArgument(String argument, String help) {
        super(argument, help);
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public boolean argEquals(String arg) {
        // Return false since positional arguments won't be accessed by specific keyword
        return false;
    }
}
