package Tests;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Base.BaseClassSauceDemo;
import Pages.CartPage;
import Pages.LoginPage;
import Pages.ProductsPage;

public class CartTests extends BaseClassSauceDemo{


	
	    // Login, add product and go to cart before every test
	    @BeforeMethod
	    public void goToCart() {
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

	        // Step 3 — Click cart icon
	        productsPage.clickCartIcon();
	        System.out.println("Navigated to cart page");
	    }

	    // =====================
	    // Cart Display Tests
	    // =====================

	    // TC_CAR_01 — Verify cart title
	    @Test(priority = 1,
	    		groups = {"smoke","regression"},
	          description = "Verify cart page title shows Your Cart")
	    public void verifyCartTitle() {
	        CartPage cartPage = new CartPage(driver);
	        Assert.assertEquals(cartPage.getCartTitle(), "Your Cart",
	            "TC_CAR_01 FAILED — Cart title is incorrect");
	        System.out.println("TC_CAR_01 PASSED — Cart title verified");
	    }

	    // TC_CAR_02 — Verify cart URL
	    @Test(priority = 2,
	          description = "Verify cart URL contains cart.html")
	    public void verifyCartUrl() {
	        CartPage cartPage = new CartPage(driver);
	        Assert.assertTrue(
	            cartPage.getCurrentUrl().contains("cart.html"),
	            "TC_CAR_02 FAILED — Cart URL is incorrect");
	        System.out.println("TC_CAR_02 PASSED — Cart URL verified");
	    }

	    // TC_CAR_03 — Verify product appears in cart
	    @Test(priority = 3,
	    		groups = {"smoke","regression"},
	          description = "Verify added product appears in cart")
	    public void verifyProductInCart() {
	        CartPage cartPage = new CartPage(driver);
	        Assert.assertEquals(cartPage.getCartProductCount(), 1,
	            "TC_CAR_03 FAILED — Product not found in cart");
	        System.out.println("TC_CAR_03 PASSED — Product appears in cart");
	    }

	    // =====================
	    // Product Details Tests
	    // =====================

	    // TC_CAR_04 — Verify product name in cart
	    @Test(priority = 4,
	    		groups = {"regression"},
	          description = "Verify product name in cart matches product added")
	    public void verifyProductName() {
	        CartPage cartPage = new CartPage(driver);
	        Assert.assertEquals(cartPage.getFirstProductName(),
	            "Sauce Labs Backpack",
	            "TC_CAR_04 FAILED — Product name in cart is incorrect");
	        System.out.println("TC_CAR_04 PASSED — Product name verified");
	    }

	    // TC_CAR_05 — Verify product price in cart
	    @Test(priority = 5,
	    		groups = {"regression"},
	          description = "Verify product price in cart matches product page")
	    public void verifyProductPrice() {
	        CartPage cartPage = new CartPage(driver);
	        Assert.assertEquals(cartPage.getFirstProductPrice(), "$29.99",
	            "TC_CAR_05 FAILED — Product price in cart is incorrect");
	        System.out.println("TC_CAR_05 PASSED — Product price verified");
	    }

	    // TC_CAR_06 — Verify product quantity shows 1
	    @Test(priority = 6,
	    		groups = {"regression"},
	          description = "Verify product quantity shows 1 by default")
	    public void verifyProductQuantity() {
	        CartPage cartPage = new CartPage(driver);
	        Assert.assertEquals(cartPage.getFirstProductQuantity(), "1",
	            "TC_CAR_06 FAILED — Product quantity is not 1");
	        System.out.println("TC_CAR_06 PASSED — Product quantity verified");
	    }

	    // =====================
	    // Remove Tests
	    // =====================

	    // TC_CAR_07 — Remove product verify cart is empty
	    @Test(priority = 7,
	    		groups = {"regression"},
	          description = "Remove product and verify cart is empty")
	    public void removeProductVerifyCartEmpty() {
	        CartPage cartPage = new CartPage(driver);
	        cartPage.removeFirstProduct();
	        Assert.assertTrue(cartPage.isCartEmpty(),
	            "TC_CAR_07 FAILED — Cart is not empty after removing");
	        System.out.println("TC_CAR_07 PASSED — Cart is empty after remove");
	    }

	    // TC_CAR_08 — Remove product verify product count is 0
	    @Test(priority = 8,
	    		groups = {"regression"},
	          description = "Remove product and verify product count is 0")
	    public void removeProductVerifyCount() {
	        CartPage cartPage = new CartPage(driver);
	        cartPage.removeFirstProduct();
	        Assert.assertEquals(cartPage.getCartProductCount(), 0,
	            "TC_CAR_08 FAILED — Product still showing after remove");
	        System.out.println(
	            "TC_CAR_08 PASSED — Product count 0 after remove");
	    }

	    // TC_CAR_09 — Add two remove one verify one remains
	    @Test(priority = 9,
	    		groups = {"regression"},
	          description = "Add two products remove one verify one remains")
	    public void addTwoRemoveOneVerifyOneRemains() {
	        // Add second product — first already added in @BeforeMethod
	        ProductsPage productsPage = new ProductsPage(driver);
	        productsPage.clickContinueShopping();
	        productsPage.addProductToCart(1);
	        productsPage.clickCartIcon();

	        CartPage cartPage = new CartPage(driver);
	        Assert.assertEquals(cartPage.getCartProductCount(), 2,
	            "Two products not in cart");
	        cartPage.removeFirstProduct();
	        Assert.assertEquals(cartPage.getCartProductCount(), 1,
	            "TC_CAR_09 FAILED — Cart should have 1 product remaining");
	        System.out.println(
	            "TC_CAR_09 PASSED — One product remains after removing one");
	    }

	    // TC_CAR_10 — Verify removed product no longer in cart
	    @Test(priority = 10,
	    		groups = {"regression"},
	          description = "Verify removed product no longer shows in cart")
	    public void verifyRemovedProductGone() {
	        CartPage cartPage = new CartPage(driver);
	        cartPage.removeFirstProduct();
	        Assert.assertEquals(cartPage.getCartProductCount(), 0,
	            "TC_CAR_10 FAILED — Removed product still in cart");
	        System.out.println(
	            "TC_CAR_10 PASSED — Removed product no longer in cart");
	    }

	    // =====================
	    // Continue Shopping Tests
	    // =====================

	    // TC_CAR_11 — Continue Shopping returns to products page
	    @Test(priority = 11,
	    		groups = {"regression"},
	          description = "Continue Shopping returns to products page")
	    public void verifyContinueShopping() {
	        CartPage cartPage = new CartPage(driver);
	        cartPage.clickContinueShopping();
	        Assert.assertTrue(
	            driver.getCurrentUrl().contains("inventory.html"),
	            "TC_CAR_11 FAILED — Did not return to products page");
	        System.out.println(
	            "TC_CAR_11 PASSED — Continue Shopping works correctly");
	    }

	    // TC_CAR_12 — Verify products page title after continuing
	    @Test(priority = 12,
	    		groups = {"regression"},
	          description = "Verify products page title after Continue Shopping")
	    public void verifyProductsPageAfterContinue() {
	        CartPage cartPage = new CartPage(driver);
	        cartPage.clickContinueShopping();
	        ProductsPage productsPage = new ProductsPage(driver);
	        Assert.assertEquals(productsPage.getPageTitle(), "Products",
	            "TC_CAR_12 FAILED — Products page title incorrect");
	        System.out.println(
	            "TC_CAR_12 PASSED — Products page title verified");
	    }

	    // TC_CAR_13 — Verify cart badge persists after continuing
	    @Test(priority = 13,
	    		groups = {"regression"},
	          description = "Verify cart badge count persists after continuing")
	    public void verifyBadgePersistsAfterContinue() {
	        CartPage cartPage = new CartPage(driver);
	        cartPage.clickContinueShopping();
	        ProductsPage productsPage = new ProductsPage(driver);
	        Assert.assertEquals(productsPage.getCartBadgeCount(), 1,
	            "TC_CAR_13 FAILED — Cart badge not persisting");
	        System.out.println(
	            "TC_CAR_13 PASSED — Cart badge persists after continuing");
	    }

	    // =====================
	    // Checkout Navigation Tests
	    // =====================

	    // TC_CAR_14 — Checkout button navigates to checkout page
	    @Test(priority = 14,
	    		groups = {"regression"},
	          description = "Verify Checkout button navigates to checkout page")
	    public void verifyCheckoutNavigation() {
	        CartPage cartPage = new CartPage(driver);
	        cartPage.clickCheckout();
	        Assert.assertTrue(
	            driver.getCurrentUrl().contains("checkout-step-one"),
	            "TC_CAR_14 FAILED — Checkout did not navigate correctly");
	        System.out.println(
	            "TC_CAR_14 PASSED — Checkout navigation verified");
	    }

	    // TC_CAR_15 — Verify checkout URL
	    @Test(priority = 15,
	    		groups = {"regression"},
	          description = "Verify checkout URL contains checkout-step-one")
	    public void verifyCheckoutUrl() {
	        CartPage cartPage = new CartPage(driver);
	        cartPage.clickCheckout();
	        Assert.assertTrue(
	            driver.getCurrentUrl().contains("checkout-step-one"),
	            "TC_CAR_15 FAILED — Checkout URL is incorrect");
	        System.out.println("TC_CAR_15 PASSED — Checkout URL verified");
	    }

	    // =====================
	    // Edge Case Tests
	    // =====================

	    // TC_CAR_16 — Verify empty cart shows no products
	    @Test(priority = 16,
	    		groups = {"regression"},
	          description = "Verify empty cart shows no products")
	    public void verifyEmptyCart() {
	        CartPage cartPage = new CartPage(driver);
	        cartPage.removeFirstProduct();
	        Assert.assertEquals(cartPage.getCartProductCount(), 0,
	            "TC_CAR_16 FAILED — Empty cart showing products");
	        System.out.println(
	            "TC_CAR_16 PASSED — Empty cart verified");
	    }

	    // TC_CAR_17 — Verify cart badge not visible when cart empty
	    @Test(priority = 17,
	    		groups = {"regression"},
	          description = "Verify cart badge not visible when cart is empty")
	    public void verifyBadgeNotVisibleWhenEmpty() {
	        CartPage cartPage = new CartPage(driver);
	        cartPage.removeFirstProduct();
	        cartPage.clickContinueShopping();
	        ProductsPage productsPage = new ProductsPage(driver);
	        Assert.assertEquals(productsPage.getCartBadgeCount(), 0,
	            "TC_CAR_17 FAILED — Badge visible when cart is empty");
	        System.out.println(
	            "TC_CAR_17 PASSED — Badge not visible when cart empty");
	    }
	}

