package argparse.arguments;

public class FlagArgument extends OptionalArgument {
    private String alias;

    public FlagArgument(String argument, String help) {
        super(argument, help);
        this.alias = "";
    }

    public FlagArgument(String argument, String alias, String help) {
        super(argument, help);
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getKeyword() {
        if (alias.isEmpty()) {
            return super.getKeyword();
        }
        return alias;
    }

    @Override
    public boolean argEquals(String arg) {
        return super.argEquals(arg) || arg.equals(getAlias());
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof FlagArgument)) {
            return false;
        }
        return super.equals(other) || getAlias().equals(((FlagArgument) other).getAlias());
    }

    @Override
    public String toString() {
        String r;
        if (alias.isEmpty()) {
            r = String.format("%s\t\t%s", getArgument(), getHelp());
        } else {
            r = String.format("%s, %s\t\t%s", getAlias(), getArgument(), getHelp());

        }
        return r;
    }
}
