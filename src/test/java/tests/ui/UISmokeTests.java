package tests.ui;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.checkboxes.CheckBoxesPage;
import pages.dynamicLoading.DynamicallyLoadingExample2Page;
import pages.dynamicLoading.DynamicallyLoadingPage;
import pages.frames.FramesPage;
import pages.frames.iFramePage;

@Slf4j
public final class UISmokeTests extends BaseUITest {
    private HomePage homePage;

    @Test
    public void checkboxesPageControlsCouldChangeState() {
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
        homePage.dynamicallyLoading.click();

        var dynamicallyLoadingPage = new DynamicallyLoadingPage(driver);
        dynamicallyLoadingPage.example2.click();

        var dynamicallyLoadingExample2Page = new DynamicallyLoadingExample2Page(driver);
        dynamicallyLoadingExample2Page.start.click();
        dynamicallyLoadingExample2Page.waitUntilFinishedAndTextAppear("Hello World!");
    }

    @BeforeEach
    public void preconditions() {
        var base = System.getProperty("base.url");
        log.info("Open URL: " + base);
        driver.get(base);
        homePage = new HomePage(driver);
    }
}
