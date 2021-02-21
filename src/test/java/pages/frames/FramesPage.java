package pages.frames;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class FramesPage extends BasePage {
    public FramesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@id='content']/descendant::a[text()='iFrame']")
    public WebElement iFrame;
}
