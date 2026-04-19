package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.DashboardPage;
import pages.LoginPage;
import utils.ExcelUtil;

public class LoginTest extends BaseTest {

    @DataProvider(name="loginExcelData")
    public Object[][] getLoginData() {

        String path="src/test/resources/testdata/LoginData.xlsx";
        return ExcelUtil.getTestData(path, "Sheet1");
    }

    @Test(dataProvider="loginExcelData")
    public void verifyLogin(String username, String password, String expected) {
        LoginPage lp=new LoginPage(getDriver());
        lp.login(username, password);

        switch (expected.toLowerCase()) {
            case "success":
                Assert.assertTrue(
                        lp.isLoginSuccessful(),
                        "Expected successful login but failed"
                );
                break;

            case "failure":
                Assert.assertTrue(
                        lp.isErrorDisplayed(),
                        "Expected error message for invalid login"
                );
                break;

            case "empty":
                Assert.assertTrue(
                    lp.isAnyErrorDisplayed(),
                    "Expected validation or error for empty inputs"
                );
                break;

            default:
                Assert.fail("Invalid expected value in Excel: " + expected);
        }
    }
    
    // Verify logout redirects to login page
    @Test
    public void verifyLogout() {
        LoginPage lp= new LoginPage(getDriver());
        lp.login(config.getUsername(), config.getPassword());

        Assert.assertTrue(lp.isLoginSuccessful(), "Login failed before logout");

        DashboardPage dashboard = new DashboardPage(getDriver());
        dashboard.clickLogout();

        Assert.assertTrue(lp.isOnLoginPage(),"User is not redirected to login page after logout");
    }
}