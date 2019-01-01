package argparse.arguments;

/**
 * RequiredArgument class.
 *
 * @author Daniel Copley
 * @version 0.3
 */
public abstract class RequiredArgument extends Argument {
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
    protected RequiredArgument(String token, String help) {
        super(token, help);
        position = posArgIndex++;
    }

    /**
     * Method returns the position of required argument.
     *
     * @return argument position
     */
    protected int getPosition() {
        return position;
    }
}
