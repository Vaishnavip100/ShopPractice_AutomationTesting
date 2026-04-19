package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class CartPage extends BasePage {
    private By checkoutBtn=By.cssSelector(".totalRow button");
    private By cartRows=By.cssSelector(".cartSection");
    private By spinner=By.cssSelector(".ngx-spinner-overlay");
    
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void waitForCartToLoad() {
        waitForElement(cartRows);
        waitForElementToDisappear(spinner);
    }
    
    public List<WebElement> getCartItems() {
        waitForCartToLoad();
        return driver.findElements(cartRows);
    }

    public boolean isProductInCart(String name) {
        By rowsLocator=By.xpath("//li[contains(@class,'items')]");
        List<WebElement> rows=driver.findElements(rowsLocator);

        for (int i=0;i<rows.size();i++) {
            WebElement row=driver.findElements(rowsLocator).get(i);
            String product=row.findElement(By.xpath(".//h3")).getText();
            if (product.equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void deleteProduct(String name) {
        By rowsLocator=By.xpath("//li[contains(@class,'items')]");
        List<WebElement> rows=driver.findElements(rowsLocator);
        for (int i=0;i<rows.size();i++) {
            WebElement row=driver.findElements(rowsLocator).get(i);
            String product=row.findElement(By.xpath(".//h3")).getText();
            if (product.equalsIgnoreCase(name)) {
                row.findElement(By.xpath(".//button[contains(@class,'btn-danger')]")).click();
                wait.until(driver ->driver.findElements(By.xpath("//li[contains(@class,'items')]//h3")).stream().noneMatch(e -> e.getText().equalsIgnoreCase(name)));
                return;
            }
        }
    }
    
    public int calculateExpectedTotal() {
        waitForCartToLoad();
        List<WebElement> rows=driver.findElements(By.xpath("//li[contains(@class,'items')]"));
        int sum=0;
        for (WebElement row : rows) {
            String priceText=row.findElement(By.xpath(".//div[contains(@class,'prodTotal')]//p")).getText().replaceAll("[^0-9]", "");
            if (!priceText.isEmpty()) {
                sum+=Integer.parseInt(priceText);
            }
        }
        return sum;
    }

    public int getDisplayedTotal() {
        waitForCartToLoad();
        WebElement totalElement=driver.findElement(By.xpath("//span[normalize-space()='Total']/following-sibling::span"));
        String totalText=totalElement.getText().replaceAll("[^0-9]", "");
        return Integer.parseInt(totalText);
    }
    
    public void goToCheckout() {
        click(checkoutBtn);
    }
}