package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class OrderDetailsPage extends BasePage {
    private By productName=By.xpath("//div[@class='title']");
    
    public OrderDetailsPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName() {
        return getText(productName);
    }
}