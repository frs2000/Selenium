package ua.com.qatestlab.prestashopautomation.currency;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ProductPrice {

    private final static Logger logger = Logger.getLogger(ProductPrice.class);
    private WebDriver driver;

    public ProductPrice(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(css = "span[class=price]")
    private List<WebElement> productsPrice;

    @FindBy(css = "div[class=\"product-price-and-shipping\"]")
    private List<WebElement> productsPriceBlock;

    private final By regularPrice = By.cssSelector("span[class=\"regular-price\"]");
    private final By price = By.cssSelector("span[itemprop=\"price\"]");
    private final By discount = By.cssSelector("span[class=\"discount-percentage\"]");

    public List<String> getProductsPrice() {
        logger.info("Get products price (text format)");

        return productsPrice.stream()
                .map(WebElement::getText)
                .collect(toList());
    }

    public List<Float> getProductsPriceAfterSorting() {
        logger.info("Get products price (float format)");

        return productsPriceBlock.stream()
                .map(this::getPrice)
                .map(this::extractPrice)
                .collect(toList());
    }

    private List<WebElement> getPrice(WebElement priceBlock) {
        List<WebElement> startingPrice = priceBlock.findElements(regularPrice);
        List<WebElement> currentPrice = priceBlock.findElements(price);

        if (!startingPrice.isEmpty()) {
            return startingPrice;
        } else {
            return currentPrice;
        }
    }

    private float extractPrice(List<WebElement> price) {
        return price.stream()
                .map(it -> it.getText().split(" "))
                .map(it -> it[0])
                .map(it -> it.replace(',', '.'))
                .map(Float::valueOf)
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public boolean isDiscountCalculatesRight() {
        logger.info("Check that discount is calculates right"); // информативность подумать + геттинг

        return productsPriceBlock.stream()
                .filter(it -> !it.findElements(discount).isEmpty())
                .map(this::checkDiscount)
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }

    private boolean checkDiscount(WebElement priceBlock) {
        List<WebElement> randomDiscountProduct = priceBlock.findElements(discount);
        List<WebElement> currentPrice = priceBlock.findElements(price);
        List<WebElement> basePrice = priceBlock.findElements(regularPrice);

        int extractedDiscount = extractDiscount(randomDiscountProduct);
        float priceWithDiscount = extractPrice(currentPrice);
        float priceWithoutDiscount = extractPrice(basePrice);

        return Math.round(100 - (priceWithDiscount / (priceWithoutDiscount / 100))) == extractedDiscount;
    }

    private int extractDiscount(List<WebElement> discount) {
        return discount.stream()
                .map(WebElement::getText)
                .map(it -> it.replace("-", ""))
                .map(it -> it.replace("%", ""))
                .map(Integer::parseInt)
                .findFirst().orElseThrow(IllegalArgumentException::new);
    }
}