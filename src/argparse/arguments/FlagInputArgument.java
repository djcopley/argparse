package argparse.arguments;

/**
 * FlagInputArgument class.
 *
 * @author Daniel Copley
 * @version 0.2
 */
public class FlagInputArgument extends OptionalArgument {
    /**
     * The input token name, by default is upper case token
     */
    private String inputToken;

    /**
     * OptionalArgument class constructor. Constructor takes two string params and passes them to the super class
     * constructor.
     *
     * @param token Argument object added to parser
     * @param help  text description of
     */
    public FlagInputArgument(String token, String help) {
        super(token, help);
        inputToken = token.toUpperCase();
        while (inputToken.charAt(0) == '-') {
            inputToken = inputToken.substring(1);
        }
    }

    /**
     * Constructor method takes two string parameters (token and help), then passes them to the super class
     * constructor.
     *
     * @param token Argument object added to parser
     * @param alias sort name of option
     * @param help  text to be shown to user when help flag is passed
     */
    public FlagInputArgument(String token, String alias, String help) {
        super(token, alias, help);
        inputToken = token.toUpperCase();
        while (inputToken.charAt(0) == '-') {
            inputToken = inputToken.substring(1);
        }
    }

    public FlagInputArgument(String token, String inputToken, String alias, String help) {
        super(token, alias, help);
        this.inputToken = inputToken;
    }

    /**
     * Method parses input and returns true if the argument is not contradictory (ie. valid), else false.
     *
     * <p>
     * An example of an invalid argument would be a flag input argument missing its input.
     * </p>
     *
     * @param args array of string arguments to parse
     * @return true if the arguments passed is valid, else false
     */
    @Override
    public boolean resolveArgument(String[] args) {
        for (int index = 0; index < args.length; index++) {
            if (stringArgEquals(args[index])) {
                if (index + 1 < args.length && args[index + 1].charAt(0) != '-') {
                    setInput(args[index + 1]);
                    setPassed();
                } else return false;
            } else if (!args[index].startsWith("=") && args[index].contains("=") &&
                    stringArgEquals(args[index].substring(0, args[index].indexOf('=')))) {
                setInput(args[index].substring(args[index].indexOf('=') + 1));
                setPassed();
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
        return String.format("%s=%s", super.getUsage(), inputToken);
    }

    @Override
    public String toString() {
        if (getAlias() != null) {
            return String.format("%s, %s=%s\t\t%s", getAlias(), getToken(), inputToken, getHelp());
        }
        return String.format("%s=%s\t\t%s", getToken(), inputToken, getHelp());
    }
}