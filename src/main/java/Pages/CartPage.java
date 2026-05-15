package Pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {

	
	WebDriver driver;

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Locators
    @FindBy(xpath = "//span[@class='title']")
    WebElement cartTitle;

    @FindBy(className = "inventory_item_name")
    List<WebElement> cartProductNames;

    @FindBy(className = "inventory_item_price")
    List<WebElement> cartProductPrices;

    @FindBy(className = "cart_quantity")
    List<WebElement> cartQuantities;

    @FindBy(xpath = "//button[contains(@id,'remove')]")
    List<WebElement> removeButtons;

    @FindBy(id = "continue-shopping")
    WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    // =====================
    // Action Methods
    // =====================

    // Get cart page title
    public String getCartTitle() {
        try {
            String title = cartTitle.getText();
            System.out.println("Cart title: " + title);
            return title;
        } catch (Exception e) {
            System.out.println("Failed to get cart title: "
                + e.getMessage());
            return "";
        }
    }

    // Get current URL
    public String getCurrentUrl() {
        try {
            String url = driver.getCurrentUrl();
            System.out.println("Current URL: " + url);
            return url;
        } catch (Exception e) {
            System.out.println("Failed to get URL: "
                + e.getMessage());
            return "";
        }
    }

    // Get number of products in cart
    public int getCartProductCount() {
        try {
            int count = cartProductNames.size();
            System.out.println("Products in cart: " + count);
            return count;
        } catch (Exception e) {
            System.out.println("Failed to count cart products: "
                + e.getMessage());
            return 0;
        }
    }

    // Get first product name in cart
    public String getFirstProductName() {
        try {
            String name = cartProductNames.get(0).getText();
            System.out.println("First product in cart: " + name);
            return name;
        } catch (Exception e) {
            System.out.println("Failed to get product name: "
                + e.getMessage());
            return "";
        }
    }

    // Get first product price in cart
    public String getFirstProductPrice() {
        try {
            String price = cartProductPrices.get(0).getText();
            System.out.println("First product price in cart: " + price);
            return price;
        } catch (Exception e) {
            System.out.println("Failed to get product price: "
                + e.getMessage());
            return "";
        }
    }

    // Get first product quantity
    public String getFirstProductQuantity() {
        try {
            String qty = cartQuantities.get(0).getText();
            System.out.println("First product quantity: " + qty);
            return qty;
        } catch (Exception e) {
            System.out.println("Failed to get quantity: "
                + e.getMessage());
            return "";
        }
    }

    // Remove first product from cart
    public void removeFirstProduct() {
        try {
            removeButtons.get(0).click();
            System.out.println("First product removed from cart");
        } catch (Exception e) {
            System.out.println("Failed to remove product: "
                + e.getMessage());
        }
    }

    // Click Continue Shopping
    public void clickContinueShopping() {
        try {
            continueShoppingButton.click();
            System.out.println("Continue Shopping clicked");
        } catch (Exception e) {
            System.out.println("Failed to click Continue Shopping: "
                + e.getMessage());
        }
    }

    // Click Checkout
    public void clickCheckout() {
        try {
            checkoutButton.click();
            System.out.println("Checkout button clicked");
        } catch (Exception e) {
            System.out.println("Failed to click Checkout: "
                + e.getMessage());
        }
    }

    // Check if cart is empty
    public boolean isCartEmpty() {
        try {
            boolean empty = cartProductNames.size() == 0;
            System.out.println("Cart is empty: " + empty);
            return empty;
        } catch (Exception e) {
            System.out.println("Failed to check cart: "
                + e.getMessage());
            return true;
        }
    }
}
