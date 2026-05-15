package Tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseClassSauceDemo;
import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.LoginPage;
import Pages.ProductsPage;
public class CheckoutTests extends BaseClassSauceDemo{


	    // Login, add product, go to cart and click checkout
	    @BeforeMethod
	    public void goToCheckout() {
	        // Step 1 — Login
	        LoginPage loginPage = new LoginPage(driver);
	        loginPage.setUsername(p.getProperty("Username"));
	        loginPage.setPassword(p.getProperty("Password"));
	        loginPage.clickOnLogin();
	        System.out.println("Login successful");

	        // Step 2 — Add first product
	        ProductsPage productsPage = new ProductsPage(driver);
	        productsPage.addProductToCart(0);
	        System.out.println("Product added to cart");

	        // Step 3 — Go to cart
	        productsPage.clickCartIcon();
	        System.out.println("Navigated to cart page");

	        // Step 4 — Click checkout
	        CartPage cartPage = new CartPage(driver);
	        cartPage.clickCheckout();
	        System.out.println("Navigated to checkout page");
	    }

	    // =====================
	    // Step 1 — Customer Information Tests
	    // =====================

	    // TC_CHK_01 — Verify checkout page title
	    @Test(priority = 1,
	    		groups = {"regression"},
	          description = "Verify checkout page title")
	    public void verifyCheckoutPageTitle() {
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        Assert.assertEquals(checkoutPage.getPageTitle(),
	            "Checkout: Your Information",
	            "TC_CHK_01 FAILED — Checkout page title incorrect");
	        System.out.println(
	            "TC_CHK_01 PASSED — Checkout page title verified");
	    }

	    // TC_CHK_02 — Verify checkout URL
	    @Test(priority = 2,
	    		groups = {"regression"},
	          description = "Verify checkout URL contains checkout-step-one")
	    public void verifyCheckoutUrl() {
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        Assert.assertTrue(
	            checkoutPage.getCurrentUrl()
	                .contains("checkout-step-one"),
	            "TC_CHK_02 FAILED — Checkout URL incorrect");
	        System.out.println(
	            "TC_CHK_02 PASSED — Checkout URL verified");
	    }

	    // TC_CHK_03 — Complete checkout with valid details
	    @Test(priority = 3,
	    		groups = {"smoke","regression"},
	          description = "Complete checkout with valid details")
	    public void completeCheckoutWithValidDetails() {
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        checkoutPage.fillCheckoutForm(
	            p.getProperty("firstName"),
	            p.getProperty("lastName"),
	            p.getProperty("zipCode"));
	        checkoutPage.clickContinue();
	        Assert.assertTrue(
	            checkoutPage.getCurrentUrl()
	                .contains("checkout-step-two"),
	            "TC_CHK_03 FAILED — Did not navigate to overview page");
	        System.out.println(
	            "TC_CHK_03 PASSED — Valid checkout details accepted");
	    }

	    // TC_CHK_04 — Empty first name error
	    @Test(priority = 4,
	    		groups = {"regression"},
	          description = "Submit with empty first name verify error")
	    public void verifyEmptyFirstNameError() {
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        checkoutPage.fillCheckoutForm("",
	            p.getProperty("lastName"),
	            p.getProperty("zipCode"));
	        checkoutPage.clickContinue();
	        Assert.assertTrue(
	            checkoutPage.getErrorMessage()
	                .contains("First Name is required"),
	            "TC_CHK_04 FAILED — First name error not shown");
	        System.out.println(
	            "TC_CHK_04 PASSED — Empty first name error verified");
	    }

	    // TC_CHK_05 — Empty last name error
	    @Test(priority = 5,
	    		groups = {"regression"},
	          description = "Submit with empty last name verify error")
	    public void verifyEmptyLastNameError() {
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        checkoutPage.fillCheckoutForm(
	            p.getProperty("firstName"), "",
	            p.getProperty("zipCode"));
	        checkoutPage.clickContinue();
	        Assert.assertTrue(
	            checkoutPage.getErrorMessage()
	                .contains("Last Name is required"),
	            "TC_CHK_05 FAILED — Last name error not shown");
	        System.out.println(
	            "TC_CHK_05 PASSED — Empty last name error verified");
	    }

	    // TC_CHK_06 — Empty zip code error
	    @Test(priority = 6,
	    		groups = {"regression"},
	          description = "Submit with empty zip code verify error")
	    public void verifyEmptyZipCodeError() {
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        checkoutPage.fillCheckoutForm(
	            p.getProperty("firstName"),
	            p.getProperty("lastName"), "");
	        checkoutPage.clickContinue();
	        Assert.assertTrue(
	            checkoutPage.getErrorMessage()
	                .contains("Postal Code is required"),
	            "TC_CHK_06 FAILED — Zip code error not shown");
	        System.out.println(
	            "TC_CHK_06 PASSED — Empty zip code error verified");
	    }

	    // TC_CHK_07 — All fields empty error
	    @Test(priority = 7,
	    		groups = {"regression"},
	          description = "Submit with all empty fields verify error")
	    public void verifyAllFieldsEmptyError() {
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        checkoutPage.fillCheckoutForm("", "", "");
	        checkoutPage.clickContinue();
	        Assert.assertTrue(
	            checkoutPage.getErrorMessage()
	                .contains("First Name is required"),
	            "TC_CHK_07 FAILED — Error not shown for empty fields");
	        System.out.println(
	            "TC_CHK_07 PASSED — All empty fields error verified");
	    }

	    // TC_CHK_08 — Cancel returns to cart
	    @Test(priority = 8,
	    		groups = {"regression"},
	          description = "Cancel button returns to cart page")
	    public void verifyCancelReturnsToCart() {
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        checkoutPage.clickCancel();
	        Assert.assertTrue(
	            checkoutPage.getCurrentUrl().contains("cart.html"),
	            "TC_CHK_08 FAILED — Cancel did not return to cart");
	        System.out.println(
	            "TC_CHK_08 PASSED — Cancel returns to cart verified");
	    }

	    // =====================
	    // Step 2 — Overview Tests
	    // =====================

	    // Helper method — fills form and navigates to overview
	    public void goToOverview() {
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        checkoutPage.fillCheckoutForm(
	            p.getProperty("firstName"),
	            p.getProperty("lastName"),
	            p.getProperty("zipCode"));
	        checkoutPage.clickContinue();
	    }

	    // TC_CHK_09 — Verify overview page title
	    @Test(priority = 9,
	    		groups = {"regression"},
	          description = "Verify overview page title")
	    public void verifyOverviewPageTitle() {
	        goToOverview();
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        Assert.assertEquals(checkoutPage.getPageTitle(),
	            "Checkout: Overview",
	            "TC_CHK_09 FAILED — Overview page title incorrect");
	        System.out.println(
	            "TC_CHK_09 PASSED — Overview page title verified");
	    }

	    // TC_CHK_10 — Verify overview URL
	    @Test(priority = 10,
	    		groups = {"regression"},
	          description = "Verify overview URL contains checkout-step-two")
	    public void verifyOverviewUrl() {
	        goToOverview();
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        Assert.assertTrue(
	            checkoutPage.getCurrentUrl()
	                .contains("checkout-step-two"),
	            "TC_CHK_10 FAILED — Overview URL incorrect");
	        System.out.println(
	            "TC_CHK_10 PASSED — Overview URL verified");
	    }

	    // TC_CHK_11 — Verify product name on overview
	    @Test(priority = 11,
	    		groups = {"regression"},
	          description = "Verify product name on overview matches product added")
	    public void verifyOverviewProductName() {
	        goToOverview();
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        Assert.assertEquals(checkoutPage.getOverviewProductName(),
	            "Sauce Labs Backpack",
	            "TC_CHK_11 FAILED — Product name on overview incorrect");
	        System.out.println(
	            "TC_CHK_11 PASSED — Overview product name verified");
	    }

	    // TC_CHK_12 — Verify product price on overview
	    @Test(priority = 12,
	    		groups = {"regression"},
	          description = "Verify product price on overview matches product page")
	    public void verifyOverviewProductPrice() {
	        goToOverview();
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        Assert.assertEquals(checkoutPage.getOverviewProductPrice(),
	            "$29.99",
	            "TC_CHK_12 FAILED — Product price on overview incorrect");
	        System.out.println(
	            "TC_CHK_12 PASSED — Overview product price verified");
	    }

	    // TC_CHK_13 — Cancel on overview returns to products
	    @Test(priority = 13,
	    		groups = {"regression"},
	          description = "Cancel on overview returns to products page")
	    public void verifyCancelOnOverview() {
	        goToOverview();
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        checkoutPage.clickCancel();
	        Assert.assertTrue(
	            checkoutPage.getCurrentUrl()
	                .contains("inventory.html"),
	            "TC_CHK_13 FAILED — Cancel did not return to products");
	        System.out.println(
	            "TC_CHK_13 PASSED — Cancel on overview verified");
	    }

	    // TC_CHK_14 — Finish navigates to confirmation
	    @Test(priority = 14,
	    		groups = {"regression"},
	          description = "Finish button navigates to confirmation page")
	    public void verifyFinishNavigation() {
	        goToOverview();
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        checkoutPage.clickFinish();
	        Assert.assertTrue(
	            checkoutPage.getCurrentUrl()
	                .contains("checkout-complete"),
	            "TC_CHK_14 FAILED — Finish did not navigate to confirmation");
	        System.out.println(
	            "TC_CHK_14 PASSED — Finish navigation verified");
	    }

	    // =====================
	    // Step 3 — Confirmation Tests
	    // =====================

	    // Helper method — completes full checkout to confirmation
	    public void goToConfirmation() {
	        goToOverview();
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        checkoutPage.clickFinish();
	    }

	    // TC_CHK_15 — Verify confirmation URL
	    @Test(priority = 15,
	    		groups = {"regression"},
	          description = "Verify confirmation URL contains checkout-complete")
	    public void verifyConfirmationUrl() {
	        goToConfirmation();
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        Assert.assertTrue(
	            checkoutPage.getCurrentUrl()
	                .contains("checkout-complete"),
	            "TC_CHK_15 FAILED — Confirmation URL incorrect");
	        System.out.println(
	            "TC_CHK_15 PASSED — Confirmation URL verified");
	    }

	    // TC_CHK_16 — Verify success message displayed
	    @Test(priority = 16,
	    		groups = {"regression"},
	          description = "Verify success message is displayed")
	    public void verifySuccessMessageDisplayed() {
	        goToConfirmation();
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        Assert.assertTrue(
	            checkoutPage.isSuccessMessageDisplayed(),
	            "TC_CHK_16 FAILED — Success message not displayed");
	        System.out.println(
	            "TC_CHK_16 PASSED — Success message displayed");
	    }

	    // TC_CHK_17 — Verify success message text
	    @Test(priority = 17,
	    		groups = {"regression"},
	          description = "Verify success message text is correct")
	    public void verifySuccessMessageText() {
	        goToConfirmation();
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        Assert.assertEquals(checkoutPage.getSuccessMessage(),
	            "Thank you for your order!",
	            "TC_CHK_17 FAILED — Success message text incorrect");
	        System.out.println(
	            "TC_CHK_17 PASSED — Success message text verified");
	    }

	    // TC_CHK_18 — Back Home returns to products page
	    @Test(priority = 18,
	    		groups = {"regression"},
	          description = "Back Home button returns to products page")
	    public void verifyBackHomeButton() {
	        goToConfirmation();
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        checkoutPage.clickBackHome();
	        Assert.assertTrue(
	            checkoutPage.getCurrentUrl()
	                .contains("inventory.html"),
	            "TC_CHK_18 FAILED — Back Home did not go to products");
	        System.out.println(
	            "TC_CHK_18 PASSED — Back Home button verified");
	    }

	    // =====================
	    // Full Checkout Flow Tests
	    // =====================

	    // TC_CHK_19 — Complete full checkout flow
	    @Test(priority = 19,
	    		groups = {"smoke","regression"},
	          description = "Complete full checkout flow end to end")
	    public void completeFullCheckoutFlow() {
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        checkoutPage.fillCheckoutForm(
	            p.getProperty("firstName"),
	            p.getProperty("lastName"),
	            p.getProperty("zipCode"));
	        checkoutPage.clickContinue();
	        checkoutPage.clickFinish();
	        Assert.assertTrue(
	            checkoutPage.isSuccessMessageDisplayed(),
	            "TC_CHK_19 FAILED — Full checkout flow failed");
	        System.out.println(
	            "TC_CHK_19 PASSED — Full checkout flow verified");
	    }

	    // TC_CHK_20 — Verify cart empty after checkout
	    @Test(priority = 20,
	    		groups = {"regression"},
	          description = "Verify cart is empty after completing checkout")
	    public void verifyCartEmptyAfterCheckout() {
	        goToConfirmation();
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        checkoutPage.clickBackHome();
	        ProductsPage productsPage = new ProductsPage(driver);
	        Assert.assertEquals(productsPage.getCartBadgeCount(), 0,
	            "TC_CHK_20 FAILED — Cart not empty after checkout");
	        System.out.println(
	            "TC_CHK_20 PASSED — Cart empty after checkout verified");
	    }

	    // TC_CHK_21 — Verify cart badge disappears after checkout
	    @Test(priority = 21,
	    		groups = {"regression"},
	          description = "Verify cart badge disappears after checkout")
	    public void verifyBadgeDisappearsAfterCheckout() {
	        goToConfirmation();
	        CheckoutPage checkoutPage = new CheckoutPage(driver);
	        checkoutPage.clickBackHome();
	        ProductsPage productsPage = new ProductsPage(driver);
	        Assert.assertEquals(productsPage.getCartBadgeCount(), 0,
	            "TC_CHK_21 FAILED — Badge still visible after checkout");
	        System.out.println(
	            "TC_CHK_21 PASSED — Badge disappears after checkout");
	    }
	}

