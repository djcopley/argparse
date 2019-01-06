package argparse.arguments;

public class OptionsArgument extends RequiredArgument {
    /**
     * OptionsArgument class constructor. Constructor takes two string params and passes them to the super class
     * constructor.
     *
     * @param token argument token
     * @param help  text to be shown to user when help flag is passed
     */
    protected OptionsArgument(String token, String help) {
        super(token, help);
    }

    /**
     * Method parses input and returns true if valid, else false.
     *
     * @param args array of string arguments to parse
     * @return true if the arguments passed is valid, else false
     */
    @Override
    public boolean resolveArgument(String[] args) {
        return false;
    }
}