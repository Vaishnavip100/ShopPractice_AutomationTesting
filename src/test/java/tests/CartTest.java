package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.CartPage;
import pages.CheckoutPage;
import pages.DashboardPage;
import pages.LoginPage;

public class CartTest extends BaseTest {
    DashboardPage db;
    CartPage cart;

    @BeforeMethod
    public void setupTest() {
        LoginPage lp=new LoginPage(getDriver());
        lp.login(config.getUsername(),config.getPassword());

        db=new DashboardPage(getDriver());
        cart=new CartPage(getDriver());
    }

    //Verify products in cart
    @Test
    public void verifyProductsInCart() {
        db.addProductToCart("ZARA COAT 3");
        db.goToCart();

        Assert.assertTrue(cart.isProductInCart("ZARA COAT 3"),"Product not found in cart");
    }

    //Delete product and verify
    @Test
    public void verifyDeleteProduct(){
    	String[] products={"ZARA COAT 3", "ADIDAS ORIGINAL"};

        db.addMultipleProducts(products);
        db.goToCart();

        cart.deleteProduct("ZARA COAT 3");

        Assert.assertFalse(cart.isProductInCart("ZARA COAT 3"),"Product still present after deletion");
    }

    //Verify total price
    @Test
    public void verifyTotalPrice() {
        String[] products={"ZARA COAT 3", "ADIDAS ORIGINAL"};

        db.addMultipleProducts(products);
        db.goToCart();

        int expected=cart.calculateExpectedTotal();
        int actual=cart.getDisplayedTotal();

        Assert.assertEquals(actual, expected, "Total price mismatch");
    }

    //Checkout flow
    @Test
    public void verifyCheckout() {
        db.addProductToCart("ZARA COAT 3");
        db.goToCart();

        cart.goToCheckout();

        CheckoutPage cp=new CheckoutPage(getDriver());
        cp.selectCountry("India");
        cp.submitOrder();

        String message = cp.getConfirmationMessage();

        Assert.assertTrue(message.equalsIgnoreCase("THANKYOU FOR THE ORDER."),"Order not placed successfully");
    }
}