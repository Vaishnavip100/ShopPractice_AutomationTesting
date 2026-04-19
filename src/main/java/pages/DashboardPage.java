package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;

public class DashboardPage extends BasePage {
    private By logoutBtn=By.xpath("//button[contains(text(),'Sign Out')]");
    private By productNames=By.cssSelector(".card-body b");
    private By productPrices=By.cssSelector(".card-body .text-muted");
    private By cartBadge=By.cssSelector("[routerlink='/dashboard/cart'] label");
    
    public DashboardPage(WebDriver driver) {
        super(driver);
    }
   
    public void clickLogout() {
        waitForElement(logoutBtn);
        click(logoutBtn);
    }

    public List<WebElement> getAllProducts() {
        waitForElement(productCards);
        return driver.findElements(productCards);
    }

    public boolean areProductsDisplayed() {
        waitForElement(productCards);
        return driver.findElements(productCards).size() > 0;
    }

    public boolean verifyProductDetails() {
        waitForElement(productCards);

        List<WebElement> names=driver.findElements(productNames);
        List<WebElement> prices=driver.findElements(productPrices);

        return names.size() > 0 && prices.size() > 0 && names.size()==prices.size();
    }

    public int getCartCount() {
        wait.until(driver -> {String text=driver.findElement(cartBadge).getText();
            return text!=null && !text.trim().isEmpty();
        });
        String text=driver.findElement(cartBadge).getText();
        return Integer.parseInt(text);
    }

    
    private By productCards=By.cssSelector(".card-body");
    private By cartBtn=By.cssSelector("[routerlink='/dashboard/cart']");
    private By spinner=By.cssSelector(".ngx-spinner-overlay");

    public void waitForLoader() {
        waitForElementToDisappear(spinner);
    }
    
    public void addProductToCart(String productName) {

        waitForElement(productCards);

        List<WebElement> products=driver.findElements(productCards);

        for (WebElement product : products) {

            String name = product.findElement(By.cssSelector("b")).getText();

            if (name.equalsIgnoreCase(productName)) {

                waitForElementToDisappear(spinner);

                WebElement addBtn = product.findElement(
                        By.xpath(".//button[contains(text(),'Add To Cart')]"));

                wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();

                waitForElementToDisappear(spinner);

                break;
            }
        }
    }

    public void addMultipleProducts(String[] products) {
        for (String p : products) {
            addProductToCart(p);
        }
    }

    public void goToCart() {
        waitForElementToDisappear(spinner);
        click(cartBtn);
    }
    
    public void goToOrders() {
        By ordersLocator=By.xpath("//button[contains(text(),'ORDERS')]");
        wait.until(driver -> {
            try {
                driver.findElement(ordersLocator).click();
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }
}