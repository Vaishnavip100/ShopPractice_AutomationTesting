package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class RegisterPage extends BasePage{
	private By registerBtn=By.xpath("//a[normalize-space()='Register here']");
	private By firstNameField=By.id("firstName");
	private By lastNameField=By.id("lastName");
	private By phoneNoField=By.id("userMobile");
	private By emailField=By.id("userEmail");
	private By passwordField=By.id("userPassword");
	private By confirmPasswordField=By.id("confirmPassword");
	private By submitBtn=By.id("login");
	private By checkBox=By.xpath("//input[@type='checkbox']");
	private By duplicateError=By.xpath("//*[contains(text(),'already exists')]");
	private By passwordError=By.xpath("//*[contains(text(),'password')]");

	
	public RegisterPage(WebDriver driver) {
		super(driver);
	}
	
	public void register(String name,String lastName,String number,String email,String password,String confirmPass) {
		click(registerBtn);
		type(firstNameField,name);
		type(lastNameField,lastName);
		type(phoneNoField,number);
	    type(emailField,email);
	    type(passwordField,password);
	    type(confirmPasswordField,confirmPass);
	    click(checkBox);
	    click(submitBtn);
	}

	public boolean isDuplicateErrorDisplayed() {
	    return driver.findElements(duplicateError).size() > 0;
	}
	
	public boolean isPasswordValidationDisplayed() {
	    return driver.findElements(passwordError).size() > 0;
	}
}
