package pages.authentication;

import enums.Directions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import pages.BasePage;

import java.sql.SQLOutput;
import java.util.logging.Logger;

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

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sorry, this user has been locked out.']")
    WebElement lockedOutUserErrorMessage;


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

    public WebElement getLockedOutUserErrorMessage() {
        return lockedOutUserErrorMessage;
    }

    public String getLoggedOutUserErrorMessageText() {
        return getText(lockedOutUserErrorMessage);
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
}