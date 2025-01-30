# API Test Automation Project

## Overview
This project is an API test automation framework built using **Java, TestNG, and Rest Assured**. It is designed to validate API responses, handle errors, and generate test reports efficiently.

## Features
- API request and response validation
- Error handling for 4xx and 5xx responses
- Test execution with TestNG
- Allure reporting for detailed test reports
- Modular design for easy maintenance

## Technologies Used
- **Java** (JDK 11 or higher)
- **TestNG** (Test framework)
- **Rest Assured** (API testing library)
- **Maven** (Dependency management)
- **Allure** (Reporting tool)

## Installation & Setup
### Prerequisites:
- Install **Java** (JDK 11+)
- Install **Maven**
- Install **Allure** (for reporting)

### Installing Allure on PC:
For Windows:
```sh
scoop install allure
```
For macOS:
```sh
brew install allure
```
For Linux:
```sh
sudo apt-add-repository ppa:qameta/allure
sudo apt update
sudo apt install allure
```

### Clone the Repository:
```sh
git clone https://github.com/michaelwaheb/APITestAutomation.git
cd APITestAutomation
```

### Install Dependencies:
```sh
mvn clean install
```

## Running the Tests
To execute the tests, run the following command:
```sh
mvn test
```

## Generating Reports
Test reports are automatically generated and accessible directly from the project.
