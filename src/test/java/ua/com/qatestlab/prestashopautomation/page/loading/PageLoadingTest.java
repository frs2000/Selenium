package ua.com.qatestlab.prestashopautomation.page.loading;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ua.com.qatestlab.prestashopautomation.WebDriverSettings;

import static org.testng.Assert.assertEquals;

public class PageLoadingTest extends WebDriverSettings {

    private final static String HOME_PAGE_TITLE = "prestashop-automation";

    @Test
    public void homePage_opened_successfully() {
        Page page = PageFactory.initElements(driver, Page.class);
        page.openHomePage();

        assertEquals(page.getTitle(), HOME_PAGE_TITLE);
    }
}