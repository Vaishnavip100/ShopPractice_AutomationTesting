package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import pages.DashboardPage;

public class ProductTest extends BaseTest {
    DashboardPage db;
    @BeforeMethod
    public void setUpTest() {
        LoginPage lp=new LoginPage(getDriver());
        lp.login(config.getUsername(),config.getPassword());
        db=new DashboardPage(getDriver());
    }

    //Product listing
    @Test
    public void verifyProductListing() {
        Assert.assertTrue(db.areProductsDisplayed(),"Products are not displayed");
        Assert.assertTrue(db.verifyProductDetails(),"Product name or price missing");
    }

    //Add single product
    @Test
    public void verifyAddToCart() {
        db.addProductToCart("ZARA COAT 3");
        Assert.assertTrue(db.getCartCount() > 0,"Cart count not updated");
    }

    //Add multiple products
    @Test
    public void verifyMultipleProductsAdd() {
        String[] products = {"ZARA COAT 3", "ADIDAS ORIGINAL"};
        db.addMultipleProducts(products);
        Assert.assertEquals(db.getCartCount(),products.length,"Multiple products not added correctly");
    }
}