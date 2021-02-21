package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='content']/descendant::a[text()='Checkboxes']")
    public WebElement checkBoxes;

    @FindBy(xpath = "//div[@id='content']/descendant::a[text()='Frames']")
    public WebElement frames;

    @FindBy(xpath = "//div[@id='content']/descendant::a[text()='Dynamic Loading']")
    public WebElement dynamicallyLoading;
}
