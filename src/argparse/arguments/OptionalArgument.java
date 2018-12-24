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
    OptionalArgument(String token, String help) {
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
    OptionalArgument(String token, String alias, String help) {
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

    /**
     * Method compares two argument objects and returns true if their tokens/alias's match.
     *
     * @param other Argument object
     * @return true if instance token/alias matches other token/alias, else false
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof OptionalArgument)) {
            return false;
        } else if (getAlias() == null || ((OptionalArgument) other).getAlias() == null) {
            return getToken().equals(((OptionalArgument) other).getToken());
        }
        return getToken().equals(((OptionalArgument) other).getToken())
                || getAlias().equals(((OptionalArgument) other).getAlias());
    }
}
