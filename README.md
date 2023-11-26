# sgVeteris Trendyol Test Automation

This project is a test automation project created using [Gauge](https://gauge.org/), [Selenium](https://www.selenium.dev/), [JUnit 5](https://junit.org/junit5/), [WebDriverManager](https://github.com/bonigarcia/webdrivermanager), [Maven](https://maven.apache.org/), and [Log4j](https://logging.apache.org/log4j/2.x/). The project is designed to execute automation tests on the Trendyol website.

## Project Structure

```plaintext
sgVeteris-trendyolTest/
│
├── src/
│   ├── test/
│       └── java/
│           ├── driver
│           │    ├── Driver.java
│           │    └── DriverFactory.java
│           │
│           └── org.sg.veteris
│                └── StepImplemantation.java
│
├── specs/
│   ├── concepts
│   │     └── trendyol.cpt
│   └── trendyol.spec
│
├── target/
│   └── (compiled bytecode and generated files)
│
├── pom.xml
│
└── README.md
```

## Requirements

- [Java](https://www.java.com/) 11 or later
- [Maven](https://maven.apache.org/) 3.5 or later
- [Gauge](https://gauge.org/) 1.4.3 or later

## Dependencies

- [Gauge Java](https://github.com/getgauge/gauge-java) - For writing test scenarios
- [Selenium Java](https://www.selenium.dev/documentation/en/) - For automating web applications
- [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) - For automatic WebDriver management
- [Log4j](https://logging.apache.org/log4j/2.x/) - For logging
- [JUnit 5](https://junit.org/junit5/) - For JUnit tests

## Running the Project

You can run the tests by using the following commands in the project root directory:

```bash
mvn gauge:execute -DspecsDir=specs -Dtags="openMainPage"
mvn gauge:execute -DspecsDir=specs -Dtags="checkWithIncorrect"
mvn gauge:execute -DspecsDir=specs -Dtags="controlLaptopSearch"
mvn gauge:execute -DspecsDir=specs -Dtags="controlRandomLaptopSelection"
mvn gauge:execute -DspecsDir=specs -Dtags="controlAddCart"
mvn gauge:execute -DspecsDir=specs -Dtags="priceControl"
mvn gauge:execute -DspecsDir=specs -Dtags="controlIncreaseProductAmount"
mvn gauge:execute -DspecsDir=specs -Dtags="controlDeletingAllProductInBasket"
```

Test reports will be generated in the `target` directory.

## Test Scenarios

Test scenarios are located in the `specs` directory. These scenarios are written in the Gauge format.

## Logging

Log files will be created in the `log` directory. You can review Log4j configuration files for log levels and other logging configurations.

---

This README file provides basic information about the project's structure, the technologies used, and the necessary steps to run the project. Make sure to check dependencies and steps before starting to work with the project.