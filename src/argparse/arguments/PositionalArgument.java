package argparse.arguments;

/**
 * PositionalArgument class.
 *
 * @author Daniel Copley
 * @version 0.3
 */
public class PositionalArgument extends RequiredArgument {
    /**
     * Constructor method takes two string parameters (token and help), then passes them to the super class
     * constructor.
     *
     * @param token argument token
     * @param help  text to be shown to user when help flag is passed
     */
    public PositionalArgument(String token, String help) {
        super(token, help);
    }

    /**
     * Method parses input and returns true if valid, else false.
     *
     * @param args array of string arguments to parse
     * @return true if the arguments passed is valid, else false
     */
    @Override
    public boolean resolveArgument(String[] args) {
        if (getPosition() < args.length) {
            if (!args[getPosition()].substring(0, 1).equals("-")) {
                setInput(args[getPosition()]);
                setPassed();
                return true;
            }
        }
        return false;
    }
}