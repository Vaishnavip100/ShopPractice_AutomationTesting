package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class LoginPage extends BasePage {
    private By email=By.id("userEmail");
    private By password=By.id("userPassword");
    private By loginBtn=By.id("login");
    private By errorMsg=By.cssSelector(".toast-message");
    private By fieldError=By.cssSelector(".ng-star-inserted");
    private By productCards=By.cssSelector(".card-body");
    
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String user,String pass) {
        if (user != null && !user.trim().isEmpty()) {
            type(email, user);
        }

        if (pass != null && !pass.trim().isEmpty()) {
            type(password, pass);
        }
        click(loginBtn);
    }

    public String getErrorMessage() {
        return getText(errorMsg);
    }

    public boolean isErrorDisplayed() {
        return isDisplayed(errorMsg);
    }
    
    public boolean isFieldValidationDisplayed() {
        return driver.findElements(fieldError).size() > 0;
    }
    
    public boolean isAnyErrorDisplayed() {
        return driver.findElements(By.cssSelector(".toast-message")).size() > 0 || driver.findElements(By.cssSelector(".invalid-feedback")).size() > 0;
    }

    public boolean isLoginSuccessful() {
        return waitForUrlContains("dashboard") || driver.findElements(productCards).size() > 0;
    }
    
    public boolean isOnLoginPage() {
        return driver.getCurrentUrl().contains("auth/login") || driver.findElements(By.id("userEmail")).size() > 0;
    }
    
    public void clickLogin() {
        click(loginBtn);
    }
}