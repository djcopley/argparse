package argparse.arguments;

/**
 * FlagInputArgument class.
 *
 * @author Daniel Copley
 * @version 0.2
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
     * @param help  text description of
     */
    public FlagInputArgument(String token, String help) {
        super(token, help);
    }

    /**
     * Constructor method takes two string parameters (token and help), then passes them to the super class
     * constructor.
     *
     * @param token Argument object added to parser
     * @param help  text to be shown to user when help flag is passed
     */
    public FlagInputArgument(String token, String alias, String help) {
        super(token, alias, help);
    }

    /**
     * Variable stores input from the command line.
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
        for (int index = 0; index < args.length; index++) {
            if (stringArgEquals(args[index])) {
                if (index + 1 < args.length && !args[index + 1].substring(0, 1).equals("-")) {
                    input = args[index + 1];
                    setPassed();
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Method returns argument usage.
     *
     * @return command line token
     */
    @Override
    public String getUsage() {
        return super.getUsage() + " INPUT";
    }
}
