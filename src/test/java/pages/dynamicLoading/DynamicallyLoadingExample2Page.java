package pages.dynamicLoading;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class DynamicallyLoadingExample2Page extends BasePage {
    private static final String FINISH_LOCATOR = "//div[@id='finish']/h4";

    public DynamicallyLoadingExample2Page(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='start']/button")
    public WebElement start;

    @FindBy(xpath = FINISH_LOCATOR)
    public WebElement finish;

    public void waitUntilFinishedAndTextAppear(String text) {
        wait.until(
                ExpectedConditions.textToBe(By.xpath(FINISH_LOCATOR), text)
        );
    }
}
