package Pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
public class CheckoutPage {

	
	    WebDriver driver;

	    // Constructor
	    public CheckoutPage(WebDriver driver) {
	        this.driver = driver;
	        PageFactory.initElements(driver, this);
	    }

	    // =====================
	    // Locators
	    // =====================

	    // Step 1 — Customer Information
	    @FindBy(xpath = "//span[@class='title']")
	    WebElement pageTitle;

	    @FindBy(id = "first-name")
	    WebElement firstNameField;

	    @FindBy(id = "last-name")
	    WebElement lastNameField;

	    @FindBy(id = "postal-code")
	    WebElement zipCodeField;

	    @FindBy(id = "continue")
	    WebElement continueButton;

	    @FindBy(id = "cancel")
	    WebElement cancelButton;

	    @FindBy(css = "[data-test='error']")
	    WebElement errorMessage;

	    // Step 2 — Overview
	    @FindBy(className = "inventory_item_name")
	    List<WebElement> overviewProductNames;

	    @FindBy(className = "inventory_item_price")
	    List<WebElement> overviewProductPrices;

	    @FindBy(id = "finish")
	    WebElement finishButton;

	    // Step 3 — Confirmation
	    @FindBy(className = "complete-header")
	    WebElement successMessage;

	    @FindBy(id = "back-to-products")
	    WebElement backHomeButton;

	    // =====================
	    // Step 1 Methods
	    // =====================

	    // Get page title
	    
	    public String getPageTitle() {
	    	try {
				String title = pageTitle.getText();
				System.out.println("Page title:" + title);
				return title;
			} catch (Exception e) {
				System.out.println("Failed to get title:" +e.getMessage());
				return "";

			}
	    }
	    
	    
/*	    public String getPageTitle() {
	        try {
	            String title = pageTitle.getText();
	            System.out.println("Page title: " + title);
	            return title;
	        } catch (Exception e) {
	            System.out.println("Failed to get title: "
	                + e.getMessage());
	            return "";
	        }
	    }
*/
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

	    // Enter first name
	    public void enterFirstName(String firstName) {
	        try {
	            firstNameField.clear();
	            firstNameField.sendKeys(firstName);
	            System.out.println("First name entered: " + firstName);
	        } catch (Exception e) {
	            System.out.println("Failed to enter first name: "
	                + e.getMessage());
	        }
	    }

	    // Enter last name
	    public void enterLastName(String lastName) {
	        try {
	            lastNameField.clear();
	            lastNameField.sendKeys(lastName);
	            System.out.println("Last name entered: " + lastName);
	        } catch (Exception e) {
	            System.out.println("Failed to enter last name: "
	                + e.getMessage());
	        }
	    }

	    // Enter zip code
	    public void enterZipCode(String zipCode) {
	        try {
	            zipCodeField.clear();
	            zipCodeField.sendKeys(zipCode);
	            System.out.println("Zip code entered: " + zipCode);
	        } catch (Exception e) {
	            System.out.println("Failed to enter zip code: "
	                + e.getMessage());
	        }
	    }

	    // Click continue button
	    public void clickContinue() {
	        try {
	            continueButton.click();
	            System.out.println("Continue button clicked");
	        } catch (Exception e) {
	            System.out.println("Failed to click continue: "
	                + e.getMessage());
	        }
	    }

	    // Click cancel button
	    public void clickCancel() {
	        try {
	            cancelButton.click();
	            System.out.println("Cancel button clicked");
	        } catch (Exception e) {
	            System.out.println("Failed to click cancel: "
	                + e.getMessage());
	        }
	    }

	    // Get error message
	    public String getErrorMessage() {
	        try {
	            String error = errorMessage.getText();
	            System.out.println("Error message: " + error);
	            return error;
	        } catch (Exception e) {
	            System.out.println("Error message not found: "
	                + e.getMessage());
	            return "";
	        }
	    }

	    // Fill complete checkout form
	    public void fillCheckoutForm(String firstName,
	                                  String lastName,
	                                  String zipCode) {
	        enterFirstName(firstName);
	        enterLastName(lastName);
	        enterZipCode(zipCode);
	        System.out.println("Checkout form filled");
	    }

	    // =====================
	    // Step 2 Methods
	    // =====================

	    // Get first product name on overview
	    public String getOverviewProductName() {
	        try {
	            String name = overviewProductNames.get(0).getText();
	            System.out.println("Overview product name: " + name);
	            return name;
	        } catch (Exception e) {
	            System.out.println("Failed to get overview product: "
	                + e.getMessage());
	            return "";
	        }
	    }

	    // Get first product price on overview
	    public String getOverviewProductPrice() {
	        try {
	            String price = overviewProductPrices.get(0).getText();
	            System.out.println("Overview product price: " + price);
	            return price;
	        } catch (Exception e) {
	            System.out.println("Failed to get overview price: "
	                + e.getMessage());
	            return "";
	        }
	    }

	    // Click finish button
	    public void clickFinish() {
	        try {
	            finishButton.click();
	            System.out.println("Finish button clicked");
	        } catch (Exception e) {
	            System.out.println("Failed to click finish: "
	                + e.getMessage());
	        }
	    }

	    // =====================
	    // Step 3 Methods
	    // =====================

	    // Get success message text
	    public String getSuccessMessage() {
	        try {
	            String message = successMessage.getText();
	            System.out.println("Success message: " + message);
	            return message;
	        } catch (Exception e) {
	            System.out.println("Failed to get success message: "
	                + e.getMessage());
	            return "";
	        }
	    }

	    // Click Back Home button
	    public void clickBackHome() {
	        try {
	            backHomeButton.click();
	            System.out.println("Back Home button clicked");
	        } catch (Exception e) {
	            System.out.println("Failed to click Back Home: "
	                + e.getMessage());
	        }
	    }

	    // Check if success message is displayed
	    public boolean isSuccessMessageDisplayed() {
	        try {
	            boolean displayed = successMessage.isDisplayed();
	            System.out.println("Success message displayed: "
	                + displayed);
	            return displayed;
	        } catch (Exception e) {
	            System.out.println("Success message not found: "
	                + e.getMessage());
	            return false;
	        }
	    }
	
}
