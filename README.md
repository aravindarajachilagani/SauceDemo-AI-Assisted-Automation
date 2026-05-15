# SauceDemo AI-Assisted Test Automation Framework

## About This Project
A professional Selenium WebDriver automation framework 
built for the SauceDemo e-commerce application. This 
project demonstrates an AI-assisted QA approach where 
ChatGPT was used for initial test case generation, 
combined with manual QA analysis to identify edge cases 
not covered by AI output.

## AI Integration Approach
| Source | Test Cases |
|--------|------------|
| AI Generated (ChatGPT) | 47 |
| Manually Identified | 12 |
| Total | 59 |

### What AI Missed — Manually Identified Cases
- Locked out user specific error message validation
- Cart badge disappearing when cart is empty
- Session handling edge cases
- Problem user visual glitch scenarios
- Checkout cancel returning to correct page
- Back button behaviour during checkout flow

## Framework Features
- Page Object Model design pattern
- Data driven testing using TestNG DataProvider
- Automatic screenshot capture on test failure
- Smoke and regression test groups via testng.xml
- ExtentReports HTML test reporting
- Externalized test data using config.properties
- BaseClass for browser setup and teardown

## Tech Stack
- Java
- Selenium WebDriver 4.18.1
- TestNG 7.9.0
- Maven
- WebDriverManager
- ExtentReports 5.1.1
- ChatGPT for AI-assisted test case generation

## Test Coverage
| Module | Test Cases | Type |
|--------|------------|------|
| Login | 7 | Smoke + Regression |
| Products | 14 | Regression |
| Cart | 17 | Regression |
| Checkout | 21 | Smoke + Regression |
| **Total** | **59** | |

## Project Structure