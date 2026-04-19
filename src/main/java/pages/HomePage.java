package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class HomePage extends BasePage {
    private By loginLink=By.xpath("//button[text()='Login']");
    
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url) {
        driver.get(url);
    }

    public void goToLoginPage() {
        if (driver.findElements(loginLink).size() > 0) {
            click(loginLink);
        }
    }

    public boolean isHomePageLoaded() {
        return driver.getTitle().toLowerCase().contains("ecommerce");
    }
}