package argparse.arguments;

import jdk.jshell.spi.ExecutionControl;

import java.util.ArrayList;

/**
 * OptionsArgument class.
 *
 * @author Daniel Copley
 * @version 0.2
 */
public class OptionsArgument extends RequiredArgument {
    /**
     * Sub argument
     */
    private OptionsArgument subCmd = null;

    /**
     * ArrayList of options
     */
    private String[] options;

    /**
     * OptionsArgument class constructor. Constructor takes two string params and passes them to the super class
     * constructor.
     *
     * @param token   Argument object added to parser
     * @param help    text to be shown to user when help flag is passed
     * @param options array of options
     */
    OptionsArgument(String token, String help, String[] options) throws ExecutionControl.NotImplementedException {
        super(token, help);
        this.options = options;
        throw new ExecutionControl.NotImplementedException("OptionsArgument not implemented");
    }

    /**
     * OptionsArgument class constructor. Constructor takes two string params and passes them to the super class
     * constructor.
     *
     * @param token   argument token
     * @param alias   alternative token
     * @param help    text to be shown to user when help flag is passed
     * @param options array of options
     */
    OptionsArgument(String token, String alias, String help, String[] options) throws ExecutionControl.NotImplementedException {
        super(token, alias, help);
        this.options = options;
        throw new ExecutionControl.NotImplementedException("OptionsArgument not implemented");
    }

    /**
     * Method adds a sub-command to OptionsArgument
     *
     * @param cmd sub-command to add
     */
    public void addSubCommand(OptionsArgument cmd) {
        if (subCmd == null) {
            subCmd = cmd;
        } else {
            OptionsArgument s = subCmd;
            while (s.subCmd != null) {
                s = s.subCmd;
            }
            s.subCmd = cmd;
        }
    }

    /**
     * Method parses input and returns true if valid, else false.
     *
     * @param args array of string arguments to parse
     * @return true if the arguments passed is valid, else false
     */
    @Override
    public boolean resolveArgument(String[] args) {
        if (getPosition() < args.length) {
            if (!args[getPosition()].substring(0, 1).equals("-")) {
                setInput(args[getPosition()]);
                setPassed();
                return true;
            }
        }
        return false;
    }

    /**
     * Method compares a string argument to it's token / alias and returns true if the match.
     *
     * @param arg string argument
     * @return true if arg matches token / alias, else false
     */
    @Override
    public boolean stringArgEquals(String arg) {
        for (String s : options) {
            if (s.equals(arg)) {
                return true;
            }
        }
        return false;
    }
}
