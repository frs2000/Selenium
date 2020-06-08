package ua.com.qatestlab.prestashopautomation.sorting;

import com.google.common.collect.Ordering;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import ua.com.qatestlab.prestashopautomation.WebDriverSettings;
import ua.com.qatestlab.prestashopautomation.currency.ProductPrice;
import ua.com.qatestlab.prestashopautomation.page.loading.Page;
import ua.com.qatestlab.prestashopautomation.search.Search;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class SortingTest extends WebDriverSettings {

    private final static String KEYWORD = "dress";
    private final static String DESCENDING_PRICE = "Цене: от высокой к низкой";

    private Sorting sorting;

    public void generalBehavior() {
        Page page = PageFactory.initElements(driver, Page.class);
        page.openHomePage();

        Search search = PageFactory.initElements(driver, Search.class);
        search.enterWord(KEYWORD);
        search.pressEnter();

        sorting = PageFactory.initElements(driver, Sorting.class);
        sorting.openSortMenu();
        sorting.sortByCriteria(DESCENDING_PRICE);
        sorting.waitCurrentBlockUpdate();
    }

    @Test
    public void sortByCriteria_successfully() {
        generalBehavior();

        assertTrue(sorting.getSortFieldValue().contains(DESCENDING_PRICE));
    }

    @Test
    public void products_sorted_in_price_reverse_order_true() {
        generalBehavior();

        ProductPrice productPrice = PageFactory.initElements(driver, ProductPrice.class);
        List<Float> productsPrice = productPrice.getProductsPriceAfterSorting();

        assertTrue(Ordering.natural().reverse().isOrdered(productsPrice));
    }

    @Test
    public void isDiscountCalculatesRight_true() {
        generalBehavior();
        ProductPrice productPrice = PageFactory.initElements(driver, ProductPrice.class);

        assertTrue(productPrice.isDiscountCalculatesRight());
    }
}