package argparse.arguments;

/**
 * Argument class.
 *
 * @author Daniel Copley
 * @version 0.5
 */
public abstract class Argument {
    /**
     * Help text / description of the token.
     */
    private String help;

    /**
     * String representation of the argument, ie. the keyword the parser is looking for (in the case of a flag
     * argument).
     */
    private String token;

    /**
     * Alternative token
     */
    private String alias = null;

    /**
     * Field is true if token is passed in the command line / token array.
     */
    private boolean argumentPassed = false;

    /**
     * Constructor method takes two string parameters (token and help), then initializes fields.
     *
     * @param token argument token
     * @param help  text to be shown to user when help flag is passed
     */
    public Argument(String token, String help) {
        this.help = help;
        this.token = token;
    }

    /**
     * Constructor method takes two string parameters (token and help), then initializes fields.
     *
     * @param token argument token
     * @param alias alternative token
     * @param help  text to be shown to user when help flag is passed
     */
    public Argument(String token, String alias, String help) {
        this.help = help;
        this.token = token;
        this.alias = alias;
    }

    /**
     * Method returns associated help text.
     *
     * @return help text
     */
    public String getHelp() {
        return help;
    }

    /**
     * Method returns argument usage.
     *
     * @return command line token
     */
    public String getUsage() {
        if (alias == null) {
            return token;
        }
        return alias;
    }

    /**
     * Method returns argument token.
     *
     * @return argument token
     */
    public String getToken() {
        return token;
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
     * Method returns argumentPassed field.
     *
     * @return true if the argument has been passed, else false
     */
    public boolean isPassed() {
        return argumentPassed;
    }

    /**
     * Method sets the argumentPassed field to true.
     */
    void setPassed() {
        this.argumentPassed = true;
    }

    /**
     * Method parses input and returns true if valid, else false.
     *
     * @param args array of string arguments to parse
     * @return true if the arguments passed is valid, else false
     */
    public abstract boolean resolveArgument(String[] args);

    /**
     * Method compares a string argument to it's token and returns true if the match.
     *
     * @param arg string argument
     * @return true if arg matches token, else false
     */
    public abstract boolean stringArgEquals(String arg);

    /**
     * Method compares two argument objects and returns true if their tokens match.
     *
     * @param other Argument object
     * @return true if instance token matches other token, else false
     */
    @Override
    public abstract boolean equals(Object other);

    /**
     * Method returns string representation of argument.
     *
     * @return string representation of argument
     */
    @Override
    public String toString() {
        if (alias != null) {
            return String.format("%s, %s\t\t%s", alias, token, help);
        }
        return String.format("%s\t\t%s", token, help);
    }
}
