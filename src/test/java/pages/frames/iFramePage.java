package pages.frames;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class iFramePage extends BasePage {
    public iFramePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "tinymce")
    public WebElement textEditor;

    @FindBy(id = "mce_0_ifr")
    public WebElement editAreaIFrame;
}
