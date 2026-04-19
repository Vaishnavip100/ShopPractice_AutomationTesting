package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.*;

public class OrderTest extends BaseTest {
    DashboardPage db;
    OrdersPage op;
    OrderDetailsPage details;

    String productName="ZARA COAT 3";

    @BeforeMethod
    public void setupTest() {
        LoginPage lp=new LoginPage(getDriver());
        lp.login(config.getUsername(), config.getPassword());

        db= new DashboardPage(getDriver());
        db.addProductToCart(productName);
        db.goToCart();

        CartPage cart=new CartPage(getDriver());
        cart.goToCheckout();

        CheckoutPage checkout=new CheckoutPage(getDriver());
        checkout.selectCountry("India");
        checkout.submitOrder();
    }

    //Order appears
    @Test
    public void verifyOrderAppears() {
        db.goToOrders();
        op=new OrdersPage(getDriver());
        Assert.assertTrue(op.hasOrders(),"No orders found");
    }

    //Verify product name in order details
    @Test
    public void verifyOrderDetails() {
        db.goToOrders();

        op=new OrdersPage(getDriver());
        op.openLatestOrder();
        
        details=new OrderDetailsPage(getDriver());
        Assert.assertTrue(details.getProductName().equalsIgnoreCase(productName),"Product name mismatch in order details");
    }

    //Verify order ID and date present
    @Test
    public void verifyOrderHistoryFields() {
        db.goToOrders();
        op=new OrdersPage(getDriver());
        Assert.assertTrue(op.getOrders().size() > 0,"Order history empty");
    }
}