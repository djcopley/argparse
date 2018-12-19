package argparse.arguments;

/**
 * OptionalArgument class.
 *
 * @author Daniel Copley
 * @version 0.2
 */
public abstract class OptionalArgument extends Argument {
    /**
     * Alternative token
     */
    private String alias;

    /**
     * OptionalArgument class constructor. Constructor takes two string params and passes them to the super class
     * constructor.
     *
     * @param token argument token
     * @param help  text to be shown to user when help flag is passed
     */
    OptionalArgument(String token, String help) {
        super(token, help);
        this.alias = "";
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
        super(token, help);
        this.alias = alias;
    }

    /**
     * Method returns the arguments alias.
     *
     * @return alias
     */
    public String getAlias() {
        return alias;
    }

    /**
     * Method sets the alias field.
     *
     * @param alias value to assign "alias" field to
     */
    public void setAlias(String alias) {
        this.alias = alias;
    }

    /**
     * Method returns argument usage.
     *
     * @return command line token
     */
    @Override
    public String getUsage() {
        if (alias.isEmpty()) {
            return super.getUsage();
        }
        return alias;
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
        } else if (getAlias().isEmpty()) {
            return getToken().equals(((OptionalArgument) other).getToken());
        }
        return getToken().equals(((OptionalArgument) other).getToken())
                || getAlias().equals(((OptionalArgument) other).getAlias());
    }

    /**
     * Method returns string representation of argument.
     *
     * @return string representation of argument
     */
    @Override
    public String toString() {
        if (!getAlias().isEmpty()) {
            return String.format("%s, %s\t\t%s", getAlias(), getToken(), getHelp());
        }
        return super.toString();
    }
}
