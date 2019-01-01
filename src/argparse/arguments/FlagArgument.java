package argparse.arguments;

/**
 * FlagArgument class.
 *
 * @author Daniel Copley
 * @version 0.3
 */
public class FlagArgument extends OptionalArgument {
    /**
     * Constructor method takes two string parameters (token and help), then passes them to the super class
     * constructor.
     *
     * @param token Argument object added to parser
     * @param help  text to be shown to user when help flag is passed
     */
    public FlagArgument(String token, String help) {
        super(token, help);
    }

    /**
     * Constructor method takes two string parameters (token and help), then passes them to the super class
     * constructor.
     *
     * @param token Argument object added to parser
     * @param alias alternative command line token
     * @param help  text to be shown to user when help flag is passed
     */
    public FlagArgument(String token, String alias, String help) {
        super(token, alias, help);
    }

    /**
     * Method parses input and returns true if valid, else false.
     *
     * @param args array of string arguments to parse
     * @return true if the arguments passed is valid, else false
     */
    public boolean resolveArgument(String[] args) {
        for (String str : args) {
            if (stringArgEquals(str)) {
                setPassed();
            }
        }
        return true;
    }
}