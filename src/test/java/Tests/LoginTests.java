package Tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Base.BaseClassSauceDemo;
import Pages.LoginPage;

public class LoginTests extends BaseClassSauceDemo{
	
	
	 // TC_LGN_01 — Valid login

    @Test(priority = 1, 
    		groups = { "smoke", "regression"},
          description = "Valid login should navigate to products page")
    public void validLoginTest() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUsername(p.getProperty("Username"));
        loginPage.setPassword(p.getProperty("Password"));
        loginPage.clickOnLogin();
     /*   Assert.assertTrue(
        	    driver.getCurrentUrl().contains("wrongpage"),
        	    "TC_LGN_01 FAILED");   */
        Assert.assertTrue(loginPage.isLoginSuccessful(),
            "TC_LGN_01 FAILED — Valid login did not reach products page"); 
        System.out.println("TC_LGN_01 PASSED — Valid login successful");
    }

 // TC_LGN_02 to TC_LGN_06 — Invalid login scenarios
    @Test(priority = 2,
    		groups = { "regression"},
          dataProvider = "invalidLoginData",
          description = "Invalid login should display correct error message")
    public void invalidLoginTest(String username,
                                  String password,
                                  String expectedError) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickOnLogin();
        String actualError = loginPage.getErrorMessage();
        Assert.assertTrue(actualError.contains(expectedError),
            "Expected: " + expectedError + " | Got: " + actualError);
        System.out.println("PASSED — Error verified for username: "
            + username);
    }
    
    @Test(priority = 3,
    		groups = { "regression"},
    	      description = "Logout should return user to login page")
    	public void logoutTest() {
    	    LoginPage loginPage = new LoginPage(driver);
    	    
    	    // First login
    	    loginPage.setUsername(p.getProperty("Username"));
    	    loginPage.setPassword(p.getProperty("Password"));
    	    loginPage.clickOnLogin();
    	    
    	    // Then logout
    	    loginPage.clickOnMenu();
    	    loginPage.clickOnLogout();
    	    
    	    // Verify back on login page
    	    Assert.assertTrue(
    	        driver.getCurrentUrl().contains("saucedemo.com"),
    	        "TC_LGN_07 FAILED — Logout did not return to login page");
    	    System.out.println("TC_LGN_07 PASSED — Logout successful");
    	}

    // DataProvider
    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return new Object[][] {
            {"locked_out_user", "secret_sauce",
                "Sorry, this user has been locked out"},
            {"",  "secret_sauce",
                "Username is required"},
            {"standard_user",   "",
                "Password is required"},
            {"wronguser",       "secret_sauce",
                "Username and password do not match"},
            {"standard_user",   "wrongpassword",
                "Username and password do not match"}
        };
    }



}

