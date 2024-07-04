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


    public void enterUsername(String username) {
        type(usernameField, username);
    }

    public void enterPassword(String password) {
        type(passwordField, password);
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
}