package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import java.lang.reflect.InvocationTargetException;

public class BaseTest {
    protected WebDriver driver;
    private static DriverManagerType driverManagerType;

    @BeforeAll
    public static void setupClass() {
        driverManagerType = DriverManagerType.valueOf(System.getProperty("webdriver.browser").toUpperCase());
        WebDriverManager.getInstance(driverManagerType).setup();
    }

    @BeforeEach
    public void setUp() throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        var browserClass = Class.forName(driverManagerType.browserClass());
        driver = new Augmenter().augment((WebDriver) browserClass.getDeclaredConstructor().newInstance());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
