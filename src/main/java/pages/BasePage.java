package pages;

import enums.Directions;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;
import java.util.logging.Logger;

public class BasePage {
    private static final Logger LOGGER = Logger.getLogger(BasePage.class.getName());

    private final AppiumDriver driver;
    private final int MAX_RETRY_COUNT = 3;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    private void refreshElements() {
        PageFactory.initElements(driver, this);
    }

    /**
     * waits for a specific element in the page to be clickable
     *
     * @param element - element to wait for
     **/
    protected WebElement waitForElementToBeClickable(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * waits for a specific element in the page to be visible
     *
     * @param element - element to wait for
     **/
    protected WebElement waitForElementToBeVisible(WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * Checks if an element is enabled
     *
     * @param element element to be checked
     * @return boolean value (if the element is enabled)
     */
    public boolean isEnabled(WebElement element) {
        int retryCount = 0;
        while (retryCount < MAX_RETRY_COUNT) {
            try {
                WebElement foundElement = waitForElementToBeVisible(element);
                return foundElement.isEnabled();
            } catch (StaleElementReferenceException e) {
                retryCount++;
                refreshElements();
                LOGGER.info("Retry " + retryCount + ": StaleElementReferenceException occurred. Retrying...");
            }
        }
        return false;
    }

    /**
     * Checks if an element is displayed
     *
     * @param element element to be checked
     * @return boolean value (if the element is displayed)
     */
    public boolean isDisplayed(WebElement element) {
        int retryCount = 0;
        while (retryCount < MAX_RETRY_COUNT) {
            try {
                WebElement foundElement = waitForElementToBeVisible(element);
                return foundElement.isDisplayed();
            } catch (StaleElementReferenceException e) {
                retryCount++;
                refreshElements();
                LOGGER.info("Retry " + retryCount + ": StaleElementReferenceException occurred. Retrying...");
            }
        }
        return false;
    }

    /**
     * Clicks on the specified WebElement, retrying up to a maximum count if a
     * StaleElementReferenceException is encountered.
     *
     * @param element the WebElement to be clicked
     */
    protected void click(WebElement element) {
        int retryCount = 0;
        while (retryCount < MAX_RETRY_COUNT) {
            try {
                WebElement foundElement = waitForElementToBeClickable(element);
                foundElement.click();
                break;
            } catch (StaleElementReferenceException e) {
                retryCount++;
                refreshElements();
                LOGGER.info("Retry " + retryCount + ": StaleElementReferenceException occurred. Retrying...");
            }
        }
    }

    /**
     * Retrieves the text content of the specified WebElement, retrying up to a maximum count if a
     * StaleElementReferenceException is encountered.
     *
     * @param element the WebElement from which to retrieve the text
     * @return the text content of the WebElement
     */
    protected String getText(WebElement element) {
        String elementText = "";
        int retryCount = 0;
        while (retryCount < MAX_RETRY_COUNT) {
            try {
                WebElement foundElement = waitForElementToBeVisible(element);
                elementText = foundElement.getText();
                break;
            } catch (StaleElementReferenceException e) {
                retryCount++;
                refreshElements();
                LOGGER.info("Retry " + retryCount + ": StaleElementReferenceException occurred. Retrying...");
            }
        }
        return elementText;
    }

    /**
     * Types the specified text into the given WebElement, retrying up to a maximum count if a
     * StaleElementReferenceException is encountered.
     *
     * @param element the WebElement to type into
     * @param text    the text to type into the WebElement
     */
    protected void type(WebElement element, String text) {
        int retryCount = 0;
        while (retryCount < MAX_RETRY_COUNT) {
            try {
                WebElement foundElement = waitForElementToBeVisible(element);
                foundElement.clear();
                foundElement.sendKeys(text);
                break;
            } catch (StaleElementReferenceException e) {
                retryCount++;
                refreshElements();
                LOGGER.info("Retry " + retryCount + ": StaleElementReferenceException occurred. Retrying...");
            }
        }
    }

    /**
     * Types the specified value into the given WebElement using the Actions class, retrying up to a
     * maximum count if a StaleElementReferenceException is encountered.
     *
     * @param element the WebElement to type into
     * @param value   the value to type into the WebElement
     */
    protected void typeViaActions(WebElement element, String value) {
        int retryCount = 0;
        while (retryCount < MAX_RETRY_COUNT) {
            try {
                waitForElementToBeVisible(element);

                Actions actions = new Actions(driver);
                actions.sendKeys(value).build().perform();
                break;
            } catch (StaleElementReferenceException e) {
                retryCount++;
                refreshElements();
                LOGGER.info("Retry " + retryCount + ": StaleElementReferenceException occurred. Retrying...");
            }
        }
    }

    // todo : improve the swipe(), swipeToElement() methods to know when the page is ready to be scrolled so that the action is optimized.

    /**
     * Performs a swipe gesture in the specified direction.
     *
     * @param direction the direction to swipe (UP, DOWN, LEFT, RIGHT)
     */
    private void swipe(Directions direction) {
        // Get the dimensions of the screen
        Dimension dimension = driver.manage().window().getSize();

        // Calculate the starting point for the swipe
        int startX = dimension.getWidth() / 2;
        int startY = dimension.getHeight() / 2;
        int endX = startX;
        int endY = startY;

        // Determine the end point based on the swipe direction
        switch (direction) {
            case DOWN -> endY = (int) (dimension.getHeight() * 0.25);
            case UP -> endY = (int) (dimension.getHeight() * 1.25);
            case LEFT -> endX = (int) (dimension.getWidth() * 0.25);
            case RIGHT -> endX = (int) (dimension.getWidth() * 1.25);
        }

        // Create a pointer input sequence to perform the swipe
        PointerInput pointerInput = new PointerInput(PointerInput.Kind.TOUCH, "pointerInput");
        Sequence sequence = new Sequence(pointerInput, 1)
                .addAction(pointerInput.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(pointerInput.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(pointerInput.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), endX, endY))
                .addAction(pointerInput.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        // Perform the swipe gesture
        driver.perform(Collections.singleton(sequence));
    }

    /**
     * Swipes in the specified direction until the given WebElement is visible on the screen.
     *
     * @param element   the WebElement to find
     * @param direction the direction to swipe (UP, DOWN, LEFT, RIGHT)
     */
    public void swipeToElement(WebElement element, Directions direction) {
        int swipesCount = 0;
        final int MAX_SWIPES_COUNT = 5;

        while (swipesCount < MAX_SWIPES_COUNT) {
            try {
                if (element.isDisplayed()) return;
            } catch (NoSuchElementException | StaleElementReferenceException e) {
                // Element not found or stale, continue to swipe
            }

            swipe(direction);
            swipesCount++;
        }
    }
}