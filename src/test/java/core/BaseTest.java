package core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.net.MalformedURLException;

public class BaseTest {
    protected App app;

    @BeforeEach
    public void setup() throws MalformedURLException {
        app = new App();
        app.config();
    }

    @AfterEach
    public void tearDown() {
        app.quitDriver();
    }
}
