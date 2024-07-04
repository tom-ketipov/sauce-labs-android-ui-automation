package pages.products;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.WebElement;
import pages.BasePage;

public class ProductsPage extends BasePage {

    public ProductsPage(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='PRODUCTS']")
    WebElement productsPageTitle;

    public WebElement getProductsPageTitle() {
        return productsPageTitle;
    }
}
