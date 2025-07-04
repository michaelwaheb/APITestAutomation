# Book Store API Test Automation Project

## Overview

This project is an API test automation framework for a Book Store App built using **Java, TestNG, and Rest Assured**. It is designed to validate API responses, handle errors, and generate test reports efficiently.

## Features

- **Rest Assured**: For automating API requests
- **TestNG**: A testing framework to run tests and manage test configurations
- **Error Handling**: A mechanism for better exception and assertion management
- **API Actions**: A utility class with generic methods for sending requests (GET, POST, PUT, DELETE) by ID (book or author), reducing code duplication
- **Utils**: Central utility for fetching test data and extracting response details
- **Allure Reports**: For generating detailed and accessible test reports

---

## Prerequisites

- **Java** (JDK 11 or higher)
- **TestNG** (Test framework)
- **Rest Assured** (API testing library)
- **Maven** (Dependency management)
- **Allure** (Reporting tool)
- **IDE** (e.g., IntelliJ IDEA, Eclipse)

### Dependencies

All dependencies are managed via Maven:

- **RestAssured**
- **TestNG**
- **Allure**

---

## Installing Allure on PC

### For Windows (PowerShell):

#### 1. Install Scoop:

```sh
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
irm get.scoop.sh | iex
```

#### 2. Install Allure:

```sh
scoop install allure
```

### For macOS:

```sh
brew install allure
```

### For Linux:

```sh
sudo apt-add-repository ppa:qameta/allure
sudo apt update
sudo apt install allure
```

---

## Setup Instructions

### 🔧 Local Setup

1. **Clone the Repository:**

```sh
git clone https://github.com/michaelwaheb/BookStoreAPITestAutomation.git
cd BookStoreAPITestAutomation
```

2. **Open in IDE:**

   - Import the project as a Maven project in IntelliJ IDEA, Eclipse, etc.

3. **Install Dependencies:**

```sh
mvn clean install
```

### ▶️ Run Tests Locally

```sh
mvn test
```

### 📈 View Allure Report Locally

After the tests finish running, the Allure report will automatically open in your browser.

> This is handled by the code in `BaseTest.java`:

```java
@AfterSuite
public void TearDown() {
    try {
        Utils.startAndStopAllureServe();
    } catch (IOException | InterruptedException e) {
        throw new RuntimeException(e);
    }
}
```

If it's commented out, simply uncomment it to enable automatic report launch locally.

---

## 🌐 CI/CD Pipeline & GitHub Actions

This project includes a GitHub Actions workflow to:

- Run tests on every push/pull request
- Generate Allure reports
- Publish the report automatically to **GitHub Pages**

### 🔀 Trigger CI Pipeline

- On every push to `main` or `BooksAPITestAutomation` branch, the pipeline is triggered automatically.

### 📄 View CI Report on GitHub Pages

> After the first successful run:

**Visit:**

```
https://michaelwaheb.github.io/BookStoreAPITestAutomation/
```

> *(Make sure "GitHub Pages" source is set to the **`gh-pages`** branch)*

---

## 🔍 Example Test Reports

- Tests cover both **happy paths** and **edge cases**
- Detailed report includes steps, attachments, and assertions

---

## 🧹 Structure Overview

- `src/test/java/ApiActions`: Contains reusable API methods
- `src/test/java/Utils`: Helper utilities for reading JSON, printing responses
- `src/test/java/tests`: Organized test cases
- `src/test/java/ErrorHandler`: Centralized exception and assertion management for clean and consistent error reporting

---

