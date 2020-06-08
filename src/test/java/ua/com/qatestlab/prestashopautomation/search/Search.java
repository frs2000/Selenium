package ua.com.qatestlab.prestashopautomation.search;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Search {

    private final static Logger logger = Logger.getLogger(Search.class);
    private WebDriver driver;

    public Search(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = "input.ui-autocomplete-input")
    private WebElement enterWord;

    @FindBy(css = "button")
    private WebElement pressEnter;

    @FindBy(css = "#js-product-list-top > div.col-md-6.hidden-sm-down.total-products > p")
    private WebElement productsFoundInfo;

    @FindBy(className = "thumbnail-container")
    private List<WebElement> productsFound;

    public void enterWord(String keyword) {
        logger.info("Enter word into the search bar");
        enterWord.sendKeys(keyword);
    }

    public void pressEnter() {
        logger.info("Press enter to start search");
        pressEnter.click();
    }

    public String getCurrentUrl() {
        logger.info("Get current search page");
        return driver.getCurrentUrl();
    }

    public String getProductsFoundInfo() {
        logger.info("Get line with information about number of found products");
        return productsFoundInfo.getText();
    }

    public int getFoundProductsCount() {
        logger.info("Get count of found products");
        return productsFound.size();
    }
}