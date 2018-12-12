package argparse.arguments;

/**
 * OptionalArgument class.
 *
 * @author Daniel Copley
 * @version 0.1
 */
public abstract class OptionalArgument extends Argument {
    /**
     * OptionalArgument class constructor. Constructor takes two string params and passes them to the super class
     * constructor.
     *
     * @param argument Argument object added to parser
     * @param help     text description of
     */
    OptionalArgument(String argument, String help) {
        super(argument, help);
    }
}
