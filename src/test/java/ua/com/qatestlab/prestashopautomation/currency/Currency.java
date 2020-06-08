package ua.com.qatestlab.prestashopautomation.currency;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Currency {

    private final static Logger logger = Logger.getLogger(Currency.class);
    private final static String USD = "$";
    private WebDriver driver;

    public Currency(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = "span.expand-more._gray-darker.hidden-sm-down")
    private WebElement headerCurrency;

    @FindBy(id = "_desktop_currency_selector")
    private WebElement currenciesMenu;

    @FindBy(css = "a[title=\"Доллар США\"]")
    private WebElement currencyUSD;

    private By button = By.cssSelector("i.material-icons.expand-more");

    public String getHeaderCurrency() {
        logger.info("Getting currency from the header");
        String currency = headerCurrency.getText();
        return currency.substring(currency.length() - 1);
    }

    public void openCurrenciesMenu() {
        currenciesMenu.findElement(button).click();
        logger.info("Opened currency menu in the header");
    }

    public void setCurrency(String currency) {
        logger.info("Choosing " + currency + " currency");
        if (currency.equals(USD)) currencyUSD.click();
    }
}