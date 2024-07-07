package config;

public class ConfigurationManager {
    private final String STANDARD_USER;
    private final String PROBLEM_USER;
    private final String LOCKED_OUT_USER;
    private final String PASSWORD;

    public ConfigurationManager() {
        this.STANDARD_USER = System.getenv("STANDARD_USER");
        this.PROBLEM_USER = System.getenv("PROBLEM_USER");
        this.PASSWORD = System.getenv("PASSWORD");
        this.LOCKED_OUT_USER = System.getenv("LOCKED_OUT_USER");

        if (this.STANDARD_USER == null || this.PROBLEM_USER == null || this.PASSWORD == null || this.LOCKED_OUT_USER == null) {
            throw new IllegalStateException("One or more environment variables are not set.");
        }
    }

    public String getStandardUser() {
        return STANDARD_USER;
    }

    public String getProblemUser() {
        return PROBLEM_USER;
    }

    public String getPassword() {
        return PASSWORD;
    }

    public String getLockedOutUser() {
        return LOCKED_OUT_USER;
    }
}
