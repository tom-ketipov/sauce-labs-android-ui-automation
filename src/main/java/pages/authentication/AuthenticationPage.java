package pages.authentication;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import pages.BasePage;

public class AuthenticationPage extends BasePage {
    public AuthenticationPage(AppiumDriver driver) {
        super(driver);
    }

    // # authentication form
    @CacheLookup
    @AndroidFindBy(accessibility = "test-Username")
    WebElement usernameField;

    @CacheLookup
    @AndroidFindBy(accessibility = "test-Password")
    WebElement passwordField;

    @CacheLookup
    @AndroidFindBy(accessibility = "test-LOGIN")
    WebElement loginBtn;

    @CacheLookup
    @AndroidFindBy(accessibility = "test-biometry")
    WebElement biometricLoginBtn;

    @AndroidFindBy(accessibility = "test-Error message")
    WebElement errorMessage;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-Error message'] //android.widget.TextView")
    WebElement errorMessageTextView;


    // # auto-fill user credential buttons
    @CacheLookup
    @AndroidFindBy(accessibility = "test-standard_user")
    WebElement standardUserAutofillBtn;

    @CacheLookup
    @AndroidFindBy(accessibility = "test-locked_out_user")
    WebElement lockedOutUserAutofillBtn;

    @CacheLookup
    @AndroidFindBy(accessibility = "test-problem_user")
    WebElement problemUserAutofillBtn;


    // # authentication form
    public void enterUsername(String username) {
        type(usernameField, username);
    }

    public String getUsernameFieldText() {
        return getText(usernameField);
    }

    public WebElement getUsernameField() {
        return usernameField;
    }

    public void enterPassword(String password) {
        type(passwordField, password);
    }

    public String getPasswordFieldText() {
        return getText(passwordField);
    }

    public void clickLoginBtn() {
        click(loginBtn);
    }

    public void clickBiometricLoginBtn() {
        click(biometricLoginBtn);
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }

    public String getErrorMessageText() {
        return getText(errorMessageTextView);
    }


    // # auto-fill user credential buttons
    public void clickStandardUserAutofillBtn() {
        click(standardUserAutofillBtn);
    }

    public WebElement getStandardUserAutofillBtn() {
        return standardUserAutofillBtn;
    }

    public void clickLockedOutUserAutofillBtn() {
        click(lockedOutUserAutofillBtn);
    }

    public WebElement getLockedOutUserAutofillBtn() {
        return lockedOutUserAutofillBtn;
    }

    public void clickProblemUserAutofillBtn() {
        click(problemUserAutofillBtn);
    }

    public WebElement getProblemUserAutofillBtn() {
        return problemUserAutofillBtn;
    }


    /**
     * Authenticates a user by entering the username and password, then clicking the login button.
     *
     * @param username the username for login
     * @param password the password for login
     */
    public void authenticate(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginBtn();
    }
}