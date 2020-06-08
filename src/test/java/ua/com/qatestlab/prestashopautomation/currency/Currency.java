package ua.com.qatestlab.prestashopautomation.currency;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Currency {

    private final static Logger logger = Logger.getLogger(Currency.class);
    private final static String USD = "$";
    private WebDriver driver;
    private WebDriverWait webDriverWait;

    public Currency(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, 10);
    }

    @FindBy(css = "span.expand-more._gray-darker.hidden-sm-down")
    private WebElement headerCurrency;

    @FindBy(css = "#_desktop_currency_selector > div > a > i")
    private WebElement currenciesMenu;

    @FindBy(css = "#_desktop_currency_selector > div > ul > li:nth-child(3)")
    private WebElement currencyUSD;

    public String getHeaderCurrency() {
        logger.info("Getting currency from the header");
        String currency = headerCurrency.getText();
        return currency.substring(currency.length() - 1);
    }

    public void openCurrenciesMenu() {
        currenciesMenu.click();
        logger.info("Opened currency menu in the header");
    }

    public void setCurrency(String currency) {
        logger.info("Choosing " + currency + " currency");
        if (currency.equals(USD)) currencyUSD.click();
    }
}