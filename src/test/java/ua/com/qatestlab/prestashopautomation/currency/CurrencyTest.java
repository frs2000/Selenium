package ua.com.qatestlab.prestashopautomation.currency;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ua.com.qatestlab.prestashopautomation.page.loading.Page;
import ua.com.qatestlab.prestashopautomation.WebDriverSettings;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

public class CurrencyTest extends WebDriverSettings {

    private final static String USD = "$";

    public void generalBehavior() {
        Page page = PageFactory.initElements(driver, Page.class);
        page.openHomePage();
    }

    @Test
    public void currency_in_header_and_popular_products_match() {
        generalBehavior();

        Currency currency = PageFactory.initElements(driver, Currency.class);
        String selectedCurrency = currency.getHeaderCurrency();

        ProductPrice price = PageFactory.initElements(driver, ProductPrice.class);
        List<String> prices = price.getProductsPrice();

        assertThat(prices).allMatch(productPrice -> productPrice.contains(selectedCurrency));
    }

    @Test
    public void set_currency_in_header_successfully() {
        generalBehavior();

        Currency currency = PageFactory.initElements(driver, Currency.class);
        currency.openCurrenciesMenu();
        currency.setCurrency(USD);

        assertEquals(USD, currency.getHeaderCurrency());
    }
}