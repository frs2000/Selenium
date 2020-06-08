package ua.com.qatestlab.prestashopautomation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class WebDriverSettings {

    private final static String WEBDRIVER_KEY = "webdriver.chrome.driver";
    private final static String PATH_TO_WEBDRIVER = "src/main/resources/driver/chromedriver.exe";

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        System.setProperty(WEBDRIVER_KEY, PATH_TO_WEBDRIVER);
        driver = new ChromeDriver();
    }

    @AfterMethod
    public void close() {
        driver.quit();
    }
}