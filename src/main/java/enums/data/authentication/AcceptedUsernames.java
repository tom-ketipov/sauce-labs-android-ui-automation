package enums.data.authentication;

public enum AcceptedUsernames {
    STANDARD_USER("standard_user"),
    LOCKED_OUT_USER("locked_out_user"),
    PROBLEM_USER("problem_user");

    private final String username;

    AcceptedUsernames(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
