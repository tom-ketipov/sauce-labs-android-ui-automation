import core.BaseTest;
import enums.Directions;
import enums.data.authentication.AcceptedUsernames;
import enums.data.authentication.Password;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

public class AuthenticationPageTest extends BaseTest {

    // # authentication form
    @Test
    @Tag("Positive")
    public void can_authenticate_with_standard_user_and_valid_password() {
        app.authenticationPage().enterUsername(AcceptedUsernames.STANDARD_USER.getUsername());
        app.authenticationPage().enterPassword(Password.SECRET_SAUCE.getPassword());
        app.authenticationPage().clickLoginBtn();

        Assertions.assertTrue(app.productsPage().isDisplayed(app.productsPage().getProductsPageTitle()));
    }

    @Test
    @Tag("Negative")
    public void cant_authenticate_with_standard_user_if_the_username_entry_is_in_uppercase() {
        app.authenticationPage().enterUsername(AcceptedUsernames.STANDARD_USER.getUsername().toUpperCase());
        app.authenticationPage().enterPassword(Password.SECRET_SAUCE.getPassword());
        app.authenticationPage().clickLoginBtn();

        Assertions.assertEquals("Username and password do not match any user in this service.", app.authenticationPage().getErrorMessageText());
    }

    @Test
    @Tag("Negative")
    public void cant_authenticate_with_standard_user_if_the_password_entry_is_in_uppercase() {
        app.authenticationPage().enterUsername(AcceptedUsernames.STANDARD_USER.getUsername());
        app.authenticationPage().enterPassword(Password.SECRET_SAUCE.getPassword().toUpperCase());
        app.authenticationPage().clickLoginBtn();

        Assertions.assertEquals("Username and password do not match any user in this service.", app.authenticationPage().getErrorMessageText());
    }

    @Test
    @Tag("Negative")
    public void cant_authenticate_with_standard_user_with_no_password() {
        app.authenticationPage().enterUsername(AcceptedUsernames.STANDARD_USER.getUsername());
        app.authenticationPage().enterPassword("");
        app.authenticationPage().clickLoginBtn();

        Assertions.assertEquals("Password is required", app.authenticationPage().getErrorMessageText());
    }

    @Test
    @Tag("Positive")
    public void can_authenticate_with_problem_user_and_valid_password() {
        app.authenticationPage().enterUsername(AcceptedUsernames.PROBLEM_USER.getUsername());
        app.authenticationPage().enterPassword(Password.SECRET_SAUCE.getPassword());
        app.authenticationPage().clickLoginBtn();

        Assertions.assertTrue(app.productsPage().isDisplayed(app.productsPage().getProductsPageTitle()));
    }

    @Test
    @Tag("Negative")
    public void cant_authenticate_with_locked_out_user_and_valid_password() {
        app.authenticationPage().enterUsername(AcceptedUsernames.LOCKED_OUT_USER.getUsername());
        app.authenticationPage().enterPassword(Password.SECRET_SAUCE.getPassword());
        app.authenticationPage().clickLoginBtn();

        Assertions.assertEquals("Sorry, this user has been locked out.", app.authenticationPage().getErrorMessageText());
    }


    // # auto-fill user credential buttons
    @Test
    @Tag("Positive")
    public void user_credentials_are_filled_when_the_user_clicks_on_the_standard_user_username() {
        app.authenticationPage().swipeToElement(app.authenticationPage().getStandardUserAutofillBtn(), Directions.DOWN);
        app.authenticationPage().clickStandardUserAutofillBtn();
        app.authenticationPage().swipeToElement(app.authenticationPage().getUsernameField(), Directions.UP);

        Assertions.assertEquals(AcceptedUsernames.STANDARD_USER.getUsername(), app.authenticationPage().getUsernameFieldText());
        Assertions.assertEquals(Password.SECRET_SAUCE.getPassword().length(), app.authenticationPage().getPasswordFieldText().length());
    }

    @Test
    @Tag("Positive")
    public void user_credentials_are_filled_when_the_user_clicks_on_the_locked_out_user_username() {
        app.authenticationPage().swipeToElement(app.authenticationPage().getLockedOutUserAutofillBtn(), Directions.DOWN);
        app.authenticationPage().clickLockedOutUserAutofillBtn();
        app.authenticationPage().swipeToElement(app.authenticationPage().getUsernameField(), Directions.UP);

        Assertions.assertEquals(AcceptedUsernames.LOCKED_OUT_USER.getUsername(), app.authenticationPage().getUsernameFieldText());
        Assertions.assertEquals(Password.SECRET_SAUCE.getPassword().length(), app.authenticationPage().getPasswordFieldText().length());
    }

    @Test
    @Tag("Positive")
    public void user_credentials_are_filled_when_the_user_clicks_on_the_problem_user_username() {
        app.authenticationPage().swipeToElement(app.authenticationPage().getProblemUserAutofillBtn(), Directions.DOWN);
        app.authenticationPage().clickProblemUserAutofillBtn();
        app.authenticationPage().swipeToElement(app.authenticationPage().getUsernameField(), Directions.UP);

        Assertions.assertEquals(AcceptedUsernames.PROBLEM_USER.getUsername(), app.authenticationPage().getUsernameFieldText());
        Assertions.assertEquals(Password.SECRET_SAUCE.getPassword().length(), app.authenticationPage().getPasswordFieldText().length());
    }
}