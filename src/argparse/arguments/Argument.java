package argparse.arguments;

/**
 * Argument class.
 *
 * @author Daniel Copley
 * @version 0.3
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
     * Field is true if token is passed in the command line / token array.
     */
    private boolean argumentPassed;

    /**
     * Constructor method takes two string parameters (token and help), then initializes fields.
     *
     * @param token Argument object added to parser
     * @param help  text to be shown to user when help flag is passed
     */
    public Argument(String token, String help) {
        this.help = help;
        this.token = token;
        argumentPassed = false;
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
     * Method returns argument token.
     *
     * @return argument token
     */
    public String getToken() {
        return token;
    }

    /**
     * Method returns the command line keyword. The key difference between getKeyword and getToken is, getKeyword
     * returns the most concise command line argument. For example, in the case of descendents with an "alias" field,
     * the alias will be returned (if provided).
     *
     * @return command line token
     */
    public String getKeyword() {
        return token;
    }

    /**
     * Method sets the argumentPassed field to true.
     */
    void setPassed() {
        this.argumentPassed = true;
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
    public boolean stringArgEquals(String arg) {
        return arg.equals(getToken());
    }

    /**
     * Method compares two argument objects and returns true if their tokens match.
     *
     * @param other Argument object
     * @return true if instance token matches other token, else false
     */
    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Argument)) {
            return false;
        }
        return getToken().equals(((Argument) other).getToken());
    }

    /**
     * Method returns string representation of argument.
     *
     * @return string representation of argument
     */
    @Override
    public String toString() {
        return String.format("%s\t\t%s", getToken(), getHelp());
    }
}
