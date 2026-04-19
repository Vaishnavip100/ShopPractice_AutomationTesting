package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.RegisterPage;

public class FormValidationsTest extends BaseTest {
    LoginPage lp;

    @BeforeMethod
    public void setup() {
        lp= new LoginPage(getDriver());
    }

    //Duplicate Email Registration
    @Test
    public void verifyDuplicateEmailRegistration() {
    	RegisterPage register=new RegisterPage(getDriver());
        register.register("Vaishnavi","Perumalla","9963312875",config.getUsername(), config.getPassword(), config.getPassword());

        Assert.assertTrue(register.isDuplicateErrorDisplayed(),"Duplicate email error not shown");
    }

    //Password Minimum Length Validation
    @Test
    public void verifyPasswordValidation() {
    	RegisterPage rp=new RegisterPage(getDriver());
        rp.register("Vaishnavi","Perumalla","9963312875","vaishnavi@test.com", "123", "123");
        Assert.assertTrue(rp.isPasswordValidationDisplayed(),"Password validation message not shown");
    }
}