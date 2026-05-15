package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

	
	WebDriver driver;
	public BasePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		}

/*
 * SauceDemo-AI-Assisted-Automation
│
├── src
│   ├── main/java/Pages
│   │   ├── LoginPage.java
│   │   ├── ProductsPage.java
│   │   ├── CartPage.java
│   │   └── CheckoutPage.java
│   │
│   └── test/java
│       ├── Base
│       │   └── BaseClassSauceDemo.java
│       ├── Listeners
│       │   └── ScreenshotListener.java
│       └── Tests
│           ├── LoginTests.java
│           ├── ProductTests.java
│           ├── CartTests.java
│           └── CheckoutTests.java
│
├── screenshots
├── reports
├── src/test/resources
│   └── config.properties
├── testng.xml
└── pom.xml
*/












}
