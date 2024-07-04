package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;

public class BasePage {
    private static final Logger LOGGER = Logger.getLogger(BasePage.class.getName());

    private final AppiumDriver driver;
    private TouchAction touchAction;
    private final int MAX_RETRY_COUNT = 3;

    public BasePage(AppiumDriver driver) {
        this.driver = driver;
//        touchAction = new TouchAction((PerformsTouchActions) driver);
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    // WAIT METHODS ---------------------------------------------------------------------------------

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

    // ACTION METHODS --------------------------------------------------------------------------------

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

    protected String getText(WebElement elementToGetText) {
        String elementText = "";
        int retryCount = 0;
        while (retryCount < MAX_RETRY_COUNT) {
            try {
                WebElement foundElement = waitForElementToBeVisible(elementToGetText);
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


    /**
     * tap on a specific element on your device
     *
     * @param element - the element where you want to tap
     **/
    protected void tap(WebElement element) {
        int retryCount = 0;
        while (retryCount < MAX_RETRY_COUNT) {
            try {
                WebElement foundElement = waitForElementToBeClickable(element);
                touchAction.tap(tapOptions().withElement(element(foundElement))).perform();
                break;
            } catch (StaleElementReferenceException e) {
                retryCount++;
                refreshElements();
                LOGGER.info("Retry " + retryCount + ": StaleElementReferenceException occurred. Retrying...");
            }
        }
    }

    /**
     * drag and Drop element from one specific destination to another
     *
     * @param from        - the starting element position (e.g. the element)
     * @param destination - the destination where you want to release() the element
     **/
    protected void dragAndDrop(WebElement from, WebElement destination) {
        LOGGER.info("Dragging the element to the specific location");
        touchAction.longPress(longPressOptions().withElement(element(from))).moveTo(element(destination)).release().perform();
    }

//    /**
//     * Performs swipe from the center of screen
//     *
//     * @param dir the direction of swipe
//     **/
//    public void swipeScreen(Directions dir) {
//        LOGGER.info("Swiping screen the screen " + dir);
//
//        // Animation default time:
//        // Animation default time:
//        //  - Android: 300 ms
//        //  - iOS: 200 ms
//        // final value depends on your app and could be greater
//        final int ANIMATION_TIME = 200; // ms
//        final int PRESS_TIME = 200; // ms
//
//        int edgeBorder = 10; // better avoid edges
//        PointOption pointOptionStart, pointOptionEnd;
//
//        // init screen variables
//        Dimension dims = driver.manage().window().getSize();
//
//        // init start point = center of screen
//        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);
//
//        switch (dir) {
//            case DOWN: // center of footer
//                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
//                break;
//            case UP: // center of header
//                pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
//                break;
//            case LEFT: // center of left side
//                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
//                break;
//            case RIGHT: // center of right side
//                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
//                break;
//            default:
//                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
//        }
//
//        // execute swipe using TouchAction
//        try {
//            new TouchAction((PerformsTouchActions) driver)
//                    .press(pointOptionStart)
//                    // a bit more reliable when we add small wait
//                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
//                    .moveTo(pointOptionEnd)
//                    .release().perform();
//        } catch (Exception e) {
//            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
//            return;
//        }
//
//        // always allow swipe action to complete
//        try {
//            Thread.sleep(ANIMATION_TIME);
//        } catch (InterruptedException e) {
//            // ignore
//        }
//    }
//
//    /**
//     * Performs swipe inside an element
//     *
//     * @param element the element to swipe
//     * @param dir     the direction of swipe
//     **/
//    public void swipeElementAndroid(WebElement element, Directions dir) {
//        LOGGER.info("Swiping mobile element " + dir);
//        // Animation default time:
//        //  - Android: 300 ms
//        //  - iOS: 200 ms
//        // final value depends on your app and could be greater
//        final int ANIMATION_TIME = 200; // ms
//
//        final int PRESS_TIME = 200; // ms
//
//        int edgeBorder;
//        PointOption pointOptionStart, pointOptionEnd;
//
//        // init screen variables
//        Rectangle rect = element.getRect();
//        // sometimes it is needed to configure edgeBorders
//        // you can also improve borders to have vertical/horizontal
//        // or left/right/up/down border variables
//        edgeBorder = 0;
//
//        switch (dir) {
//            case DOWN: // from up to down
//                pointOptionStart = PointOption.point(rect.x + rect.width / 2, rect.y);
//                pointOptionEnd = PointOption.point(rect.x + rect.width / 2, rect.y + rect.height);
//                break;
//            case UP: // from down to up
//                pointOptionStart = PointOption.point(rect.x + rect.width / 2, rect.y + rect.height - edgeBorder);
//                pointOptionEnd = PointOption.point(rect.x + rect.width / 2, rect.y + edgeBorder);
//                break;
//            case LEFT: // from right to left
//                pointOptionStart = PointOption.point(rect.x + rect.width - edgeBorder, rect.y + rect.height / 2);
//                pointOptionEnd = PointOption.point(rect.x + edgeBorder, rect.y + rect.height / 2);
//                break;
//            case RIGHT: // from left to right
//                pointOptionStart = PointOption.point(rect.x + edgeBorder, rect.y + rect.height / 2);
//                pointOptionEnd = PointOption.point(rect.x + rect.width - edgeBorder, rect.y + rect.height / 2);
//                break;
//            default:
//                throw new IllegalArgumentException("swipeElementAndroid(): dir: '" + dir + "' NOT supported");
//        }
//
//        // execute swipe using TouchAction
//        try {
//            new TouchAction((PerformsTouchActions) driver)
//                    .press(pointOptionStart)
//                    // a bit more reliable when we add small wait
//                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
//                    .moveTo(pointOptionEnd)
//                    .release().perform();
//        } catch (Exception e) {
//            System.err.println("swipeElementAndroid(): TouchAction FAILED\n" + e.getMessage());
//            return;
//        }
//
//        // always allow swipe action to complete
//        try {
//            Thread.sleep(ANIMATION_TIME);
//        } catch (InterruptedException e) {
//            // ignore
//        }
//    }
}