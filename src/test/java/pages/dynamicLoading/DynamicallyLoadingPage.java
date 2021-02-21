package pages.dynamicLoading;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class DynamicallyLoadingPage extends BasePage {
    public DynamicallyLoadingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='content']/descendant::a[text()='Example 2: Element rendered after the fact']")
    public WebElement example2;
}
