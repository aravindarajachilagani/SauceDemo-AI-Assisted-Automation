package Pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
public class ProductsPage {



	    WebDriver driver;

	    // Constructor
	    public ProductsPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    // Locators
	    @FindBy(xpath = "//span[text()='Products']")
	    WebElement pageTitle;

	    @FindBy(xpath = "//select[@class='product_sort_container']")
	    WebElement sortDropdown;

	    @FindBy(className = "inventory_item_name")
	    List<WebElement> productNames;

	    @FindBy(className = "inventory_item_price")
	    List<WebElement> productPrices;

	    @FindBy(xpath = "//button[contains(@id,'add-to-cart')]")
	    List<WebElement> addToCartButtons;

	    @FindBy(className = "shopping_cart_badge")
	    WebElement cartBadge;

	    @FindBy(className = "inventory_list")
	    WebElement inventoryList;

	    @FindBy(id = "shopping_cart_container")
	    WebElement cartIcon;

	    // =====================
	    // Action Methods
	    // =====================

	    // Get page title text
	    public String getPageTitle() {
	        try {
	            String title = pageTitle.getText();
	            System.out.println("Page title: " + title);
	            return title;
	        } catch (Exception e) {
	            System.out.println("Failed to get title: " + e.getMessage());
	            return "";
	        }
	    }

	    // Get total number of products displayed
	    public int getProductCount() {
	        try {
	            int count = productNames.size();
	            System.out.println("Products displayed: " + count);
	            return count;
	        } catch (Exception e) {
	            System.out.println("Failed to count products: "+ e.getMessage());
	            return 0;
	        }
	    }

	    // Get first product name after sorting
	    public String getFirstProductName() {
	        try {
	            String name = productNames.get(0).getText();
	            System.out.println("First product name: " + name);
	            return name;
	        } catch (Exception e) {
	            System.out.println("Failed to get first product: "
	                + e.getMessage());
	            return "";
	        }
	    }

	    // Get first product price after sorting
	    public String getFirstProductPrice() {
	        try {
	            String price = productPrices.get(0).getText();
	            System.out.println("First product price: " + price);
	            return price;
	        } catch (Exception e) {
	            System.out.println("Failed to get first price: "
	                + e.getMessage());
	            return "";
	        }
	    }

	    // Sort products using dropdown
	    public void sortBy(String option) {
	        try {
	            Select select = new Select(sortDropdown);
	            select.selectByVisibleText(option);
	            System.out.println("Sorted by: " + option);
	        } catch (Exception e) {
	            System.out.println("Failed to sort: " + e.getMessage());
	        }
	    }

	    // Add first product to cart
	    public void addFirstProductToCart() {
	        try {
	            addToCartButtons.get(0).click();
	            System.out.println("First product added to cart");
	        } catch (Exception e) {
	            System.out.println("Failed to add to cart: "
	                + e.getMessage());
	        }
	    }

	    // Add product by index — 0 is first, 1 is second
	    public void addProductToCart(int index) {
	        try {
	            addToCartButtons.get(index).click();
	            System.out.println("Product " + (index + 1)
	                + " added to cart");
	        } catch (Exception e) {
	            System.out.println("Failed to add product " + index
	                + ": " + e.getMessage());
	        }
	    }

	    // Get cart badge count
	    public int getCartBadgeCount() {
	        try {
	            int count = Integer.parseInt(cartBadge.getText());
	            System.out.println("Cart badge count: " + count);
	            return count;
	        } catch (Exception e) {
	            System.out.println("Cart badge not visible: "
	                + e.getMessage());
	            return 0;
	        }
	    }

	    // Click cart icon to go to cart page
	    public void clickCartIcon() {
	        try {
	            cartIcon.click();
	            System.out.println("Cart icon clicked");
	        } catch (Exception e) {
	            System.out.println("Failed to click cart: "
	                + e.getMessage());
	        }
	    }

	    // Check if all products have names
	    public boolean allProductsHaveNames() {
	        try {
	            for (WebElement name : productNames) {
	                if (name.getText().isEmpty()) {
	                    return false;
	                }
	            }
	            System.out.println("All products have names");
	            return true;
	        } catch (Exception e) {
	            System.out.println("Failed to verify names: "
	                + e.getMessage());
	            return false;
	        }
	    }

	    // Check if all products have prices
	    public boolean allProductsHavePrices() {
	        try {
	            for (WebElement price : productPrices) {
	                if (price.getText().isEmpty()) {
	                    return false;
	                }
	            }
	            System.out.println("All products have prices");
	            return true;
	        } catch (Exception e) {
	            System.out.println("Failed to verify prices: "
	                + e.getMessage());
	            return false;
	        }
	    }
	
	    public int getAddToCartButtonCount() {
	        try {
	            int count = addToCartButtons.size();
	            System.out.println("Add to Cart buttons found: " + count);
	            return count;
	        } catch (Exception e) {
	            System.out.println("Failed to count buttons: "
	                + e.getMessage());
	            return 0;
	        }
	    }



	    public boolean isRemoveButtonVisible() {
	        try {
	            List<WebElement> removeButtons = driver.findElements(
	                By.xpath("//button[contains(@id,'remove')]"));
	            boolean visible = removeButtons.size() > 0;
	            System.out.println("Remove button visible: " + visible);
	            return visible;
	        } catch (Exception e) {
	            System.out.println("Failed to check remove button: "
	                + e.getMessage());
	            return false;
	        }
	    }




	    public void removeFirstProductFromCart() {
	        try {
	            List<WebElement> removeButtons = driver.findElements(
	                By.xpath("//button[contains(@id,'remove')]"));
	            removeButtons.get(0).click();
	            System.out.println("First product removed from cart");
	        } catch (Exception e) {
	            System.out.println("Failed to remove product: "
	                + e.getMessage());
	        }
	    }

	    public void clickContinueShopping() {
	        try {
	            driver.findElement(
	                By.id("continue-shopping")).click();
	            System.out.println("Continue Shopping clicked");
	        } catch (Exception e) {
	            System.out.println("Failed to click Continue Shopping: "
	                + e.getMessage());
	        }
	    }




}

