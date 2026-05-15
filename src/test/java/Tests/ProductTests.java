package Tests;

import Base.BaseClassSauceDemo;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Pages.LoginPage;
import Pages.ProductsPage;
public class ProductTests  extends BaseClassSauceDemo {

	
	    // Login before every product test
	    @BeforeMethod
	    public void loginFirst() {
	        LoginPage loginPage = new LoginPage(driver);
	        loginPage.setUsername(p.getProperty("Username"));
	        loginPage.setPassword(p.getProperty("Password"));
	        loginPage.clickOnLogin();
	        System.out.println("Login successful — on products page");
	    }

	    // TC_PRD_01 — Verify page title
	    @Test(priority = 1,
	    		groups  = {"smoke", "regression"},
	          description = "Verify products page title displays Products")
	    public void verifyPageTitle() {
	        ProductsPage productsPage = new ProductsPage(driver);
	        Assert.assertEquals(productsPage.getPageTitle(), "Products",
	            "TC_PRD_01 FAILED — Page title is incorrect");
	        System.out.println("TC_PRD_01 PASSED — Page title verified");
	    }

	    // TC_PRD_02 — Verify 6 products displayed
	    @Test(priority = 2,
	    		groups = {"smoke", "regression"},
	          description = "Verify 6 products are displayed on products page")
	    public void verifyProductCount() {
	        ProductsPage productsPage = new ProductsPage(driver);
	        Assert.assertEquals(productsPage.getProductCount(), 6,
	            "TC_PRD_02 FAILED — Product count is not 6");
	        System.out.println("TC_PRD_02 PASSED — 6 products verified");
	    }

	    // TC_PRD_03 — Verify all products have names
	    @Test(priority = 3,
	    		groups = {"regression"},
	          description = "Verify all products have names")
	    public void verifyAllProductsHaveNames() {
	        ProductsPage productsPage = new ProductsPage(driver);
	        Assert.assertTrue(productsPage.allProductsHaveNames(),
	            "TC_PRD_03 FAILED — One or more products missing name");
	        System.out.println("TC_PRD_03 PASSED — All products have names");
	    }

	    // TC_PRD_04 — Verify all products have prices
	    @Test(priority = 4,
	    		groups = {"regression"},
	          description = "Verify all products have prices")
	    public void verifyAllProductsHavePrices() {
	        ProductsPage productsPage = new ProductsPage(driver);
	        Assert.assertTrue(productsPage.allProductsHavePrices(),
	            "TC_PRD_04 FAILED — One or more products missing price");
	        System.out.println("TC_PRD_04 PASSED — All products have prices");
	    }

	    // TC_PRD_05 — Verify all products have Add to Cart button
	    @Test(priority = 5,
	    		groups = {"regression"},
	          description = "Verify all products have Add to Cart button")
	    public void verifyAddToCartButtons() {
	        ProductsPage productsPage = new ProductsPage(driver);
	        Assert.assertEquals(productsPage.getAddToCartButtonCount(), 6,
	            "TC_PRD_05 FAILED — Not all products have Add to Cart button");
	        System.out.println(
	            "TC_PRD_05 PASSED — All 6 Add to Cart buttons verified");
	    }

	    // TC_SRT_01 — Sort by Name A to Z
	    @Test(priority = 6,
	    		groups = {"regression"},
	          description = "Sort by Name A to Z and verify first product")
	    public void sortByNameAtoZ() {
	        ProductsPage productsPage = new ProductsPage(driver);
	        productsPage.sortBy("Name (A to Z)");
	        String firstName = productsPage.getFirstProductName();
	        Assert.assertEquals(firstName, "Sauce Labs Backpack",
	            "TC_SRT_01 FAILED — First product after A to Z sort is wrong");
	        System.out.println("TC_SRT_01 PASSED — A to Z sort verified");
	    }

	    // TC_SRT_02 — Sort by Name Z to A
	    @Test(priority = 7,
	    		groups = {"regression"},
	          description = "Sort by Name Z to A and verify first product")
	    public void sortByNameZtoA() {
	        ProductsPage productsPage = new ProductsPage(driver);
	        productsPage.sortBy("Name (Z to A)");
	        String firstName = productsPage.getFirstProductName();
	        Assert.assertEquals(firstName,
	            "Test.allTheThings() T-Shirt (Red)",
	            "TC_SRT_02 FAILED — First product after Z to A sort is wrong");
	        System.out.println("TC_SRT_02 PASSED — Z to A sort verified");
	    }

	    // TC_SRT_03 — Sort by Price Low to High
	    @Test(priority = 8,
	    		groups = {"regression"},
	          description = "Sort by Price Low to High and verify first price")
	    public void sortByPriceLowToHigh() {
	        ProductsPage productsPage = new ProductsPage(driver);
	        productsPage.sortBy("Price (low to high)");
	        String firstPrice = productsPage.getFirstProductPrice();
	        Assert.assertEquals(firstPrice, "$7.99",
	            "TC_SRT_03 FAILED — First price after Low to High is wrong");
	        System.out.println("TC_SRT_03 PASSED — Price Low to High verified");
	    }

	    // TC_SRT_04 — Sort by Price High to Low
	    @Test(priority = 9,
	    		groups = {"regression"},
	          description = "Sort by Price High to Low and verify first price")
	    public void sortByPriceHighToLow() {
	        ProductsPage productsPage = new ProductsPage(driver);
	        productsPage.sortBy("Price (high to low)");
	        String firstPrice = productsPage.getFirstProductPrice();
	        Assert.assertEquals(firstPrice, "$49.99",
	            "TC_SRT_04 FAILED — First price after High to Low is wrong");
	        System.out.println("TC_SRT_04 PASSED — Price High to Low verified");
	    }

	    // TC_CRT_01 — Add one product verify badge shows 1
	    @Test(priority = 10,
	    		groups = {"regression"},
	          description = "Add one product and verify cart badge shows 1")
	    public void addOneProductVerifyBadge() {
	        ProductsPage productsPage = new ProductsPage(driver);
	        productsPage.addProductToCart(0);
	        Assert.assertEquals(productsPage.getCartBadgeCount(), 1,
	            "TC_CRT_01 FAILED — Cart badge not showing 1");
	        System.out.println("TC_CRT_01 PASSED — Cart badge shows 1");
	    }

	    // TC_CRT_02 — Add two products verify badge shows 2
	    @Test(priority = 11,
	    		groups = {"regression"},
	          description = "Add two products and verify cart badge shows 2")
	    public void addTwoProductsVerifyBadge() {
	        ProductsPage productsPage = new ProductsPage(driver);
	        productsPage.addProductToCart(0);
	        productsPage.addProductToCart(1);
	        Assert.assertEquals(productsPage.getCartBadgeCount(), 2,
	            "TC_CRT_02 FAILED — Cart badge not showing 2");
	        System.out.println("TC_CRT_02 PASSED — Cart badge shows 2");
	    }

	    // TC_CRT_03 — Verify Add to Cart changes to Remove
	    @Test(priority = 12,
	    		groups = {"regression"},
	          description = "Verify Add to Cart button changes to Remove")
	    public void verifyButtonChangesToRemove() {
	        ProductsPage productsPage = new ProductsPage(driver);
	        productsPage.addProductToCart(0);
	        Assert.assertTrue(productsPage.isRemoveButtonVisible(),
	            "TC_CRT_03 FAILED — Remove button not visible after adding");
	        System.out.println(
	            "TC_CRT_03 PASSED — Button changed to Remove");
	    }

	    // TC_RMV_01 — Remove product verify badge disappears
	    @Test(priority = 13,
	    		groups = {"regression"},
	          description = "Remove product and verify badge disappears")
	    public void removeProductVerifyBadgeDisappears() {
	        ProductsPage productsPage = new ProductsPage(driver);
	        productsPage.addProductToCart(0);
	        Assert.assertEquals(productsPage.getCartBadgeCount(), 1,
	            "Product not added");
	        productsPage.removeFirstProductFromCart();
	        Assert.assertEquals(productsPage.getCartBadgeCount(), 0,
	            "TC_RMV_01 FAILED — Badge still visible after removing");
	        System.out.println(
	            "TC_RMV_01 PASSED — Badge disappeared after remove");
	    }

	    // TC_RMV_02 — Add two remove one verify badge shows 1
	    @Test(priority = 14,
	    		groups = {"regression"},
	          description = "Add two products remove one verify badge shows 1")
	    public void addTwoRemoveOneVerifyBadge() {
	        ProductsPage productsPage = new ProductsPage(driver);
	        productsPage.addProductToCart(0);
	        productsPage.addProductToCart(1);
	        Assert.assertEquals(productsPage.getCartBadgeCount(), 2,
	            "Two products not added");
	        productsPage.removeFirstProductFromCart();
	        Assert.assertEquals(productsPage.getCartBadgeCount(), 1,
	            "TC_RMV_02 FAILED — Badge not showing 1 after removing one");
	        System.out.println(
	            "TC_RMV_02 PASSED — Badge shows 1 after removing one");
	    }
	}
	

