package argparse.arguments;

/**
 * OptionalArgument class.
 *
 * @author Daniel Copley
 * @version 0.3
 */
public abstract class OptionalArgument extends Argument {
    /**
     * OptionalArgument class constructor. Constructor takes two string params and passes them to the super class
     * constructor.
     *
     * @param token argument token
     * @param help  text to be shown to user when help flag is passed
     */
    protected OptionalArgument(String token, String help) {
        super(token, help);
    }

    /**
     * OptionsArgument class constructor. Constructor takes two string params and passes them to the super class
     * constructor.
     *
     * @param token argument token
     * @param alias alternative token
     * @param help  text to be shown to user when help flag is passed
     */
    protected OptionalArgument(String token, String alias, String help) {
        super(token, alias, help);
    }

    /**
     * Method compares a string argument to it's token / alias and returns true if the match.
     *
     * @param arg string argument
     * @return true if arg matches token / alias, else false
     */
    @Override
    public boolean stringArgEquals(String arg) {
        return arg.equals(getToken()) || arg.equals(getAlias());
    }
}