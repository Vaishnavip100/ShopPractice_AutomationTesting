package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class CheckoutPage extends BasePage {
    private By countryInput=By.cssSelector("[placeholder='Select Country']");
    private By countryResults=By.cssSelector(".ta-results button");
    private By placeOrderBtn=By.cssSelector(".action__submit");
    private By confirmationMsg=By.cssSelector(".hero-primary");
    private By spinner=By.cssSelector(".ngx-spinner-overlay");
    
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void selectCountry(String countryName) {
        waitForElement(countryInput);
        driver.findElement(countryInput).sendKeys(countryName);
        waitForElement(countryResults);

        for (WebElement option : driver.findElements(countryResults)) {
            if (option.getText().equalsIgnoreCase(countryName)) {
                option.click();
                break;
            }
        }
    }

    public void submitOrder() {
        waitForElementToDisappear(spinner);
        waitForElement(placeOrderBtn);
        driver.findElement(placeOrderBtn).click();
    }

    public String getConfirmationMessage() {
        waitForElement(confirmationMsg);
        return driver.findElement(confirmationMsg).getText();
    }
}