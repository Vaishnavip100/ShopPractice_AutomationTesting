package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class OrdersPage extends BasePage {
    private By orderRows=By.xpath("//tbody/tr");
    private By orderIds=By.xpath("//tbody/tr/th");
    private By viewButtons=By.xpath("//button[contains(text(),'View')]");
    
    public OrdersPage(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getOrders() {
        waitForElement(orderRows);
        return driver.findElements(orderRows);
    }

    public boolean hasOrders() {
        return getOrders().size() > 0;
    }

    public String getLatestOrderId() {
        return driver.findElements(orderIds).get(0).getText();
    }

    public void openLatestOrder() {
        By rowsLocator=By.xpath("//tbody/tr");
        By viewBtnLocator=By.xpath("//button[contains(text(),'View')]");

        wait.until(driver -> driver.findElements(rowsLocator).size() > 0);
        wait.until(driver -> driver.findElements(viewBtnLocator).size() > 0);
        driver.findElements(viewBtnLocator).get(0).click();
    }
}