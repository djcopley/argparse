package argparse.arguments;

/**
 * RequiredArgument class.
 *
 * @author Daniel Copley
 * @version 0.3
 */
public abstract class RequiredArgument extends Argument {
    /**
     * Variable stores input from the command line.
     */
    private String input;

    /**
     * Member variable; maintains the index of current positional argument.
     */
    private static int posArgIndex = 0;

    /**
     * Index of positional argument.
     */
    private int position;

    /**
     * RequiredArgument class constructor. Constructor takes two string params and passes them to the super class
     * constructor.
     *
     * @param token argument token
     * @param help  text to be shown to user when help flag is passed
     */
    RequiredArgument(String token, String help) {
        super(token, help);
        position = posArgIndex++;
    }

    /**
     * RequiredArgument class constructor. Constructor takes two string params and passes them to the super class
     * constructor.
     *
     * @param token argument token
     * @param alias alternative token
     * @param help  text to be shown to user when help flag is passed
     */
    RequiredArgument(String token, String alias, String help) {
        super(token, alias, help);
        position = posArgIndex++;
    }

    /**
     * Method returns input field. Input field is set by the parser when it identifies the input of a positional
     * argument.
     *
     * @return command line input
     */
    public String getInput() {
        return input;
    }

    /**
     * Method sets the input field.
     *
     * @param input value to set instance variable "input"
     */
    void setInput(String input) {
        this.input = input;
    }

    /**
     * Method returns the position of required argument.
     *
     * @return argument position
     */
    int getPosition() {
        return position;
    }

    /**
     * Method returns false since positional arguments won't be accessed by their tokens.
     *
     * @param other Argument object
     * @return true if instance token matches other token, else false
     */
    @Override
    public boolean equals(Object other) {
        return false;
    }
}
