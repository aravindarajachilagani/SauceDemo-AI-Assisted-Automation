package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage  {

	WebDriver driver;
	public LoginPage(WebDriver driver) {
	    this.driver = driver;
	    PageFactory.initElements(driver, this);
	}
	
	

	@FindBy(xpath="//input[@id='user-name']") 
	WebElement Username;

	@FindBy(xpath="//input[@id='password']")
	 WebElement password;
	 
	@FindBy(xpath="//input[@id='login-button']")
	 WebElement login_btn;
	
	@FindBy(xpath="//div[@class='app_logo']")
	WebElement AppLogo;
	
	@FindBy(xpath="//h3[@data-test='error']")
	WebElement Error_msg ;
		
	@FindBy(xpath="//button[@id='react-burger-menu-btn']")
	WebElement Menubutton;
		
	@FindBy(xpath="//a[@id='logout_sidebar_link']")
	WebElement LogOut;
		

	
	// Click Action
		
		
		public void setUsername(String user) {
		    try {
		        Username.sendKeys(user);
		        System.out.println("Username entered: " + user);
		    } catch (Exception e) {
		        System.out.println("Failed to enter username: " + e.getMessage());
		    }
		    return;
		}

		public void setPassword(String pwd) {
		    try {
		        password.sendKeys(pwd);
		        System.out.println("Password entered successfully");
		    } catch (Exception e) {
		        System.out.println("Failed to enter password: " + e.getMessage());
		    }
		    return;
		}

		public void clickOnLogin() {
		    try {
		        login_btn.click();
		        System.out.println("Login button clicked");
		    } catch (Exception e) {
		        System.out.println("Failed to click login button: " + e.getMessage());
		    }
		    return;
		}
		
		
		  // Get error message
	    public String getErrorMessage() {
	        try {
	            String error = Error_msg.getText();
	            System.out.println("Error message displayed: " + error);
	            return error;
	        } catch (Exception e) {
	            System.out.println("Error message not found: " + e.getMessage());
	            return "";
	        }
	
	    }

	    public boolean isLoginSuccessful() {
	        try {
	            String currentUrl = driver.getCurrentUrl();
	            boolean status = currentUrl.contains("inventory");
	            System.out.println("Login successful: " + status);
	            return status;
	        } catch (Exception e) {
	            System.out.println("Could not verify login: " + e.getMessage());
	            return false;
	        }
	    }
	    
	    public void clickOnMenu() {
		    try {
		    	Menubutton.click();
		        System.out.println("Menu button clicked");
		    } catch (Exception e) {
		        System.out.println("Failed to click Menu button: " + e.getMessage());
		    }
		    return;
		}
	    
	    public void clickOnLogout() {
		    try {
		    	LogOut.click();
		        System.out.println("Logot button clicked");
		    } catch (Exception e) {
		        System.out.println("Failed to click Logout button: " + e.getMessage());
		    }
		    return;
		}
		
}
