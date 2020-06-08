package ua.com.qatestlab.prestashopautomation.page.loading;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class Page {

    private final static Logger logger = Logger.getLogger(Page.class);
    private final static String HOME_PAGE = "http://prestashop-automation.qatestlab.com.ua/ru/";
    private WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public void openHomePage() {
        logger.info("Main page opening");
        driver.get(HOME_PAGE);
    }

    public String getTitle() {
        logger.info("Get title from the page");
        return driver.getTitle();
    }
}