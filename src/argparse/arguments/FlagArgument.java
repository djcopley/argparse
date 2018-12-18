package argparse.arguments;

/**
 * FlagArgument class.
 *
 * @author Daniel Copley
 * @version 0.3
 */
public class FlagArgument extends OptionalArgument {
    private String alias;

    /**
     * Constructor method takes two string parameters (token and help), then passes them to the super class
     * constructor.
     *
     * @param token Argument object added to parser
     * @param help  text to be shown to user when help flag is passed
     */
    public FlagArgument(String token, String help) {
        super(token, help);
        this.alias = "";
    }

    /**
     * Constructor method takes two string parameters (token and help), then passes them to the super class
     * constructor.
     *
     * @param token Argument object added to parser
     * @param help  text to be shown to user when help flag is passed
     */
    public FlagArgument(String token, String alias, String help) {
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
     * Method returns alias if one exists. Otherwise, the argument token is returned.
     *
     * @return alias if one exists, else token
     */
    public String getKeyword() {
        if (alias.isEmpty()) {
            return super.getKeyword();
        }
        return alias;
    }

    /**
     * Method parses input and returns true if valid, else false.
     *
     * @param args array of string arguments to parse
     * @return true if the arguments passed is valid, else false
     */
    public boolean resolveArgument(String[] args) {
        for (String s : args) {
            if (stringArgEquals(s)) {
                setPassed();
            }
        }
        return true;
    }

    /**
     * Method compares a string argument to it's token / alias and returns true if the match.
     *
     * @param arg string argument
     * @return true if arg matches token / alias, else false
     */
    @Override
    public boolean stringArgEquals(String arg) {
        return super.stringArgEquals(arg) || arg.equals(getAlias());
    }

    /**
     * Method compares two argument objects and returns true if their tokens/alias's match.
     *
     * @param other Argument object
     * @return true if instance token/alias matches other token/alias, else false
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof FlagArgument)) {
            return false;
        }
        return super.equals(other) || getAlias().equals(((FlagArgument) other).getAlias());
    }

    /**
     * Method returns string representation of argument.
     *
     * @return string representation of argument
     */
    @Override
    public String toString() {
        if (!alias.isEmpty()) {
            return String.format("%s, %s\t\t%s", getAlias(), getToken(), getHelp());
        }
        return super.toString();
    }
}
