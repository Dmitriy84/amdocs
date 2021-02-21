package tests;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.checkboxes.CheckBoxesPage;
import pages.frames.FramesPage;
import pages.frames.iFramePage;

@Slf4j
public final class UISmokeTests extends BaseTest {
    @Test
    public void checkboxesPageControlsCouldChangeState() {
        var homePage = new HomePage(driver);
        homePage.checkBoxes.click();

        var checkboxesPage = new CheckBoxesPage(driver);

        var checkbox1State = checkboxesPage.checkBox1.isSelected();
        log.info("checkbox1 state: " + checkbox1State);
        var checkbox2State = checkboxesPage.checkBox2.isSelected();
        log.info("checkbox2 state: " + checkbox2State);
        checkboxesPage.checkBox1.click();
        checkboxesPage.checkBox2.click();

        Assertions.assertAll(() -> {
                    Assertions.assertEquals(!checkbox1State, checkboxesPage.checkBox1.isSelected(), "Unexpected state of checkBox1");
                    Assertions.assertEquals(!checkbox2State, checkboxesPage.checkBox2.isSelected(), "Unexpected state of checkBox2");
                }
        );
    }

    @Test
    public void framesPageAllowsToEditTextParagraph() {
        var testText = "Dmytro";

        var homePage = new HomePage(driver);
        homePage.frames.click();

        var framesPage = new FramesPage(driver);
        framesPage.iFrame.click();

        var iFramePage = new iFramePage(driver);
        iFramePage.chooseIFrame(iFramePage.editAreaIFrame);

        iFramePage.textEditor.clear();
        iFramePage.textEditor.sendKeys(testText);
        Assertions.assertEquals(testText, iFramePage.textEditor.getText(), "Text was not saved");
    }

    @Test
    public void dynamicLoadingAbleToShowMessage() {

    }

    @BeforeEach
    public void preconditions() {
        log.info("Open URL: " + System.getProperty("base.url"));
        driver.get(System.getProperty("base.url"));
    }
}
