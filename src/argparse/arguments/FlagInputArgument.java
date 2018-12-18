package argparse.arguments;

/**
 * FlagInputArgument class.
 *
 * @author Daniel Copley
 * @version 0.1
 */
public class FlagInputArgument extends OptionalArgument {
    /**
     * Variable stores input from the command line.
     */
    private String input;

    /**
     * OptionalArgument class constructor. Constructor takes two string params and passes them to the super class
     * constructor.
     *
     * @param token Argument object added to parser
     * @param help     text description of
     */
    FlagInputArgument(String token, String help) {
        super(token, help);
    }

    /**
     * Constructor method takes two string parameters (token and help), then passes them to the super class
     * constructor.
     *
     * @param token Argument object added to parser
     * @param help  text to be shown to user when help flag is passed
     */
    FlagInputArgument(String token, String alias, String help) {
        super(token, alias, help);
    }

    /**
     * Method parses input and returns true if valid, else false.
     *
     * @param args array of string arguments to parse
     * @return true if the arguments passed is valid, else false
     */
    @Override
    public boolean resolveArgument(String[] args) {
        return false;
    }
}
