package argparse.arguments;

public class FlagArgument extends OptionalArgument {
    private String alias = null;

    public FlagArgument(String argument, String help) {
        super(argument, help);
    }

    public FlagArgument(String argument, String alias, String help) {
        super(argument, help);
        this.alias = alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    @Override
    public boolean contains(String[] args) {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof FlagArgument)) {
            return false;
        }
        return super.equals(other) && this.alias.equals(((FlagArgument) other).alias);
    }

    @Override
    public String toString() {
        String r;
        if (alias == null) {
            r = String.format("%s\t\t%s", argument, help);
        } else {
            r = String.format("%s, %s\t\t%s", alias, argument, help);

        }
        return r;
    }
}
