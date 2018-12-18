package argparse.arguments;

/**
 * RequiredArgument class.
 *
 * @author Daniel Copley
 * @version 0.2
 */
public abstract class RequiredArgument extends Argument {
    /**
     * RequiredArgument class constructor. Constructor takes two string params and passes them to the super class
     * constructor.
     *
     * @param token Argument object added to parser
     * @param help     text to be shown to user when help flag is passed
     */
    RequiredArgument(String token, String help) {
        super(token, help);
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
