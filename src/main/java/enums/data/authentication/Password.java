package enums.data.authentication;

public enum Password {
    SECRET_SAUCE("secret_sauce");

    private final String password;

    Password(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
