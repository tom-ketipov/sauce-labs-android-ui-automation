package core;

import config.ConfigurationManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.net.MalformedURLException;

public class BaseTest {
    protected App app;
    protected ConfigurationManager configurationManager;

    @BeforeEach
    public void setup() throws MalformedURLException {
        configurationManager = new ConfigurationManager();

        app = new App();
        app.config();
    }

    @AfterEach
    public void tearDown() {
        app.quitDriver();
    }
}
