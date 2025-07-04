# Book Store API Test Automation Project

## Overview
This project is an API test automation framework for a book Store App  built using **Java, TestNG, and Rest Assured**. It is designed to validate API responses, handle errors, and generate test reports efficiently.

## Features
- **Rest Assured**: For automating API requests
- **TestNG**: A testing framework to run tests and manage test configurations.
- **Error handling**: a mechanism for better exception and assertion management
- **Api Actions**: This class provides generic methods for sending requests, such as fetching data by ID (book or author), without duplicating code in test classes.
- **Utils**: Central utility for fetching test data and extracting response details.
- **Allure Reports** for generating detailed and accessible test reports
---
## Prerequisites
- **Java** (JDK 11 or higher)
- **TestNG** (Test framework)
- **Rest Assured** (API testing library)
- **Maven** (Dependency management)
- **Allure** (Reporting tool)
-  **IDE** (e.g., IntelliJ IDEA, Eclipse)

### Dependencies
The project uses the following libraries:

- **RestAssured**: For API automation.
- **TestNG**: For test execution.
- **Allure**: For test reporting.
All dependencies are managed via Maven.
  
### Installing Allure on PC:
#### For Windows(Powershell):
##### Install Scoope first:
```sh
Set-ExecutionPolicy -ExecutionPolicy RemoteSigned -Scope CurrentUser
```
```sh
irm get.scoop.sh | iex
# You can use proxies if you have network trouble in accessing GitHub, e.g.
irm get.scoop.sh -Proxy 'http://<ip:port>' | iex
```
##### Install Allure second:
```sh
scoop install allure
```
#### For macOS:
```sh
brew install allure
```
#### For Linux:
```sh
sudo apt-add-repository ppa:qameta/allure
sudo apt update
sudo apt install allure
```
---

## Setup Instructions


1- Clone the Repository:
```sh
git clone https://github.com/michaelwaheb/BookStoreAPITestAutomation.git
cd BookStoreAPITestAutomation
```

2- Open in IDE:
   - Import the project as a Maven project in your IDE.

3-Install Dependencies:
```sh
mvn clean install
```
---

## Running the Tests

To run the tests using Maven:
```bash
mvn test
```

## Generating and Accessing Allure Reports

The project is configured to automatically generate Allure reports after running the tests. You don't need to manually run any additional commands for report generation. Once the tests are executed using Maven, the Allure report is available within the project directory.
