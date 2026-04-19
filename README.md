# 🛒 ShopPractice – Selenium TestNG Automation Framework

## 📌 Overview

This project is a Selenium + TestNG automation framework built using the Page Object Model (POM) design pattern.
It supports parallel execution, retry mechanism, Extent Reports, screenshots on failure, and data-driven testing.

---

## 🚀 Features

* Page Object Model (POM) design
* Retry mechanism for flaky tests
* Extent Reports integration
* Screenshot capture on failure
* Data-driven testing (Excel)

---

## 🧰 Tech Stack

* Java
* Selenium WebDriver
* TestNG
* Maven
* Extent Reports
* Apache POI (Excel handling)

---

## 📁 Project Structure

```text
ShopPractice/
│
├── src/main/java/
│   ├── base/
│   │   └── BasePage.java          → Common WebDriver utilities (click, wait, sendKeys)
│   │
│   ├── pages/
│   │   ├── CartPage.java         → Cart actions (verify, delete, total)
│   │   ├── CheckoutPage.java     → Checkout flow and order placement
│   │   ├── DashboardPage.java    → Product listing and navigation
│   │   ├── HomePage.java         → Landing page navigation
│   │   ├── LoginPage.java        → Login functionality
│   │   ├── OrderDetailsPage.java → Order detail verification
│   │   ├── OrdersPage.java       → Order history handling
│   │   └── RegisterPage.java     → Registration and validations
│   │
│   ├── utils/
│   │   ├── ConfigReader.java     → Reads config.properties
│   │   ├── ExcelUtil.java        → Reads Excel test data
│   │   ├── ExtentManager.java    → Extent Reports setup
│   │   └── ScreenshotUtil.java   → Captures screenshots
│
├── src/test/java/
│   ├── base/
│   │   └── BaseTest.java         → Driver setup & teardown (ThreadLocal)
│   │
│   ├── listeners/
│   │   ├── RetryListener.java    → Attaches retry logic to tests
│   │   └── TestListener.java     → Reporting + screenshot on failure
│   │
│   ├── tests/
│   │   ├── CartTest.java         → Cart test scenarios
│   │   ├── FormValidationsTest.java → Form validation tests
│   │   ├── LoginTest.java        → Login test cases
│   │   ├── OrderTest.java        → Order history tests
│   │   └── ProductTest.java      → Product related tests
│   │
│   └── utils/
│       └── RetryAnalyzer.java    → Retry failed tests logic
│
├── src/test/resources/
│   ├── config.properties         → Environment config (browser, URL, creds)
│   └── testdata/
│       └── LoginData.xlsx        → Test data for login
│
├── reports/                      → Extent reports
├── screenshots/                  → Failure screenshots
│
├── pom.xml                       → Maven dependencies & config
└── testng.xml                    → TestNG suite configuration
```

---

## ⚙️ Configuration

Edit `src/test/resources/config.properties`:

```
browser=chrome
url=https://rahulshettyacademy.com/client
timeout=10
username=your_email
password=your_password
```

---

## ▶️ How to Run Tests

### 🔹 Using Maven

```bash
mvn clean test
```
### 🔹 Using TestNG XML

Run `testng.xml` from IDE.

---

## 📊 Reports

After execution:

* 📄 Extent Report → `reports/ExtentReport.html`
* 📸 Screenshots → `screenshots/`

---

## 👤 Author

Vaishnavi

---

## ✅ Conclusion

This framework demonstrates a robust, scalable, and maintainable automation solution. It incorporates best practices like POM, parallel execution, screenshot on failure, retry logic, and reporting.

---
