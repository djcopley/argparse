package argparse.arguments;

/**
 * PositionalArgument class.
 *
 * @author Daniel Copley
 * @version 0.3
 */
public class PositionalArgument extends RequiredArgument {
    /**
     * Variable stores input from the command line.
     */
    private String input;

    /**
     * Constructor method takes two string parameters (token and help), then passes them to the super class
     * constructor.
     *
     * @param token Argument object added to parser
     * @param help  text to be shown to user when help flag is passed
     */
    public PositionalArgument(String token, String help) {
        super(token, help);
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
     * Method sets field input.
     *
     * @param input value to assign "input" field to
     */
    public void setInput(String input) {
        this.input = input;
    }

    /**
     * Positional arguments will not be matched token; therefore, always return false.
     *
     * @param arg string argument
     * @return false
     */
    @Override
    public boolean argEquals(String arg) {
        // Return false since positional arguments won't be accessed by specific token
        return false;
    }
}
