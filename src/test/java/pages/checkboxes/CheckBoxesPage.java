package pages.checkboxes;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class CheckBoxesPage extends BasePage {
    public CheckBoxesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//form[@id='checkboxes']/input[1]")
    public WebElement checkBox1;
    @FindBy(xpath = "//form[@id='checkboxes']/input[2]")
    public WebElement checkBox2;
}
