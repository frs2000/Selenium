package ua.com.qatestlab.prestashopautomation.sorting;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Sorting {

    private final static Logger logger = Logger.getLogger(Sorting.class);
    private final static String DESCENDING_PRICE = "Цене: от высокой к низкой";
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    public Sorting(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, 10);
    }

    @FindBy(css = "i.material-icons.pull-xs-right")
    private WebElement sortMenu;

    @FindBy(css = "#js-product-list-top > div:nth-child(2) > div > div > div > a:nth-child(5)")
    private WebElement sorting;

    @FindBy(css = "a[class=\"select-title\"]")
    private WebElement sortFieldValue;

    public void openSortMenu() {
        logger.info("Open sort menu");
        sortMenu.click();
    }

    public void sortByCriteria(String criteria) {
        logger.info("Select item " + criteria + " from the sort menu");
        if (criteria.equals(DESCENDING_PRICE)) sorting.click();
    }

    public String getSortFieldValue() {
        logger.info("Get current field from sort menu");
        return sortFieldValue.getText();
    }

    public void waitCurrentBlockUpdate() {
        logger.info("Wait till sort menu block updated");
        webDriverWait.until(ExpectedConditions.textToBePresentInElement(sortFieldValue, DESCENDING_PRICE));
    }
}