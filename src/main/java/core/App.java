package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import pages.authentication.AuthenticationPage;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class App {
    private AppiumDriver driver;

    // # pages
    private AuthenticationPage authenticationPage;

    public AuthenticationPage authenticationPage() {
        return authenticationPage == null ? new AuthenticationPage(driver) : authenticationPage;
    }

    protected void config() throws MalformedURLException {
        File apk = new File("src/main/resources/app/android/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");

        UiAutomator2Options uiAutomator2Options = new UiAutomator2Options();
        uiAutomator2Options.setDeviceName("38f4edee");
        uiAutomator2Options.setApp(apk.getAbsolutePath());
        uiAutomator2Options.setAppActivity("com.swaglabsmobileapp.MainActivity");
        uiAutomator2Options.setAppPackage("com.swaglabsmobileapp");

        driver = new AppiumDriver(new URL("http://127.0.0.1:4723"), uiAutomator2Options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    protected void quitDriver() {
        driver.quit();
    }
}