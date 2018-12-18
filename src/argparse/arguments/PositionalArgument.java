package argparse.arguments;

/**
 * PositionalArgument class.
 *
 * @author Daniel Copley
 * @version 0.3
 */
public class PositionalArgument extends RequiredArgument {
    /**
     * Variable stores input from the command line.
     */
    private String input;

    /**
     * Member variable; maintains the index of current positional argument.
     */
    private static int posArgIndex = 0;

    /**
     * Index of positional argument.
     */
    private int position;

    /**
     * Constructor method takes two string parameters (token and help), then passes them to the super class
     * constructor.
     *
     * @param token Argument object added to parser
     * @param help  text to be shown to user when help flag is passed
     */
    public PositionalArgument(String token, String help) {
        super(token, help);
        position = posArgIndex++;
    }

    /**
     * Method returns input field. Input field is set by the parser when it identifies the input of a positional
     * argument.
     *
     * @return command line input
     */
    public String getInput() {
        return input;
    }

    /**
     * Method parses input and returns true if valid, else false.
     *
     * @param args array of string arguments to parse
     * @return true if the arguments passed is valid, else false
     */
    @Override
    public boolean resolveArgument(String[] args) {
        if (position < args.length) {
            if (!args[position].substring(0, 1).equals("-")) {
                input = args[position];
                setPassed();
                return true;
            }
        }
        return false;
    }

    /**
     * Positional arguments will not be matched token; therefore, always return false.
     *
     * @param arg string argument
     * @return false
     */
    @Override
    public boolean stringArgEquals(String arg) {
        // Return false since positional arguments won't be accessed by specific token
        return false;
    }
}
