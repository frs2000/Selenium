package ua.com.qatestlab.prestashopautomation.search;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ua.com.qatestlab.prestashopautomation.WebDriverSettings;
import ua.com.qatestlab.prestashopautomation.page.loading.Page;
import ua.com.qatestlab.prestashopautomation.currency.ProductPrice;
import ua.com.qatestlab.prestashopautomation.currency.Currency;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.testng.Assert.assertTrue;

public class SearchTest extends WebDriverSettings {

    private final static String KEYWORD = "dress";
    private final static String USD = "$";
    private Search search;

    public void generalBehavior() {
        Page page = PageFactory.initElements(driver, Page.class);
        page.openHomePage();

        search = PageFactory.initElements(driver, Search.class);
        search.enterWord(KEYWORD);
        search.pressEnter();
    }

    @Test
    public void search_by_word_successfully() {
        generalBehavior();
        assertTrue(search.getCurrentUrl().contains(KEYWORD));
    }

    @Test
    public void products_count_match() {
        generalBehavior();
        String productsFoundInfo = search.getProductsFoundInfo();
        int count = search.getFoundProductsCount();

        assertTrue(productsFoundInfo.contains("Товаров: " + count));
    }

    @Test
    public void all_filtered_products_have_correct_currency() {
        generalBehavior();
        Currency currency = PageFactory.initElements(driver, Currency.class);
        currency.openCurrenciesMenu();
        currency.setCurrency(USD);

        ProductPrice price = PageFactory.initElements(driver, ProductPrice.class);
        List<String> prices = price.getProductsPrice();

        assertThat(prices).allMatch(productPrice -> productPrice.contains(USD));
    }
}