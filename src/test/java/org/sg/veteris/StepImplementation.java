package org.sg.veteris;

import com.fasterxml.jackson.core.JsonParser;
import com.thoughtworks.gauge.Step;
import org.junit.jupiter.api.Assertions;
import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class StepImplementation extends Driver {

    public static Double initialPrice;

    WebDriver driver = webDriver;

    WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

    @Step("Open the Trendyol homepage")
    public void goAppUrl() {
        String app_url = System.getenv("APP_URL");
        driver.get(app_url + "/");
    }

    @Step("Close gender popup if exsit")
    public void ganderChooseIfExist() {
        try {
            WebElement closeGenderPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#gender-popup-modal > div > div > div.modal-close")));
            wait.until(ExpectedConditions.visibilityOf(closeGenderPopup)).click();
            System.out.println("Gender PopUp Closed");
        } catch (Exception e) {
            System.out.println("closeGenderPopup element is not displayed");
        }

    }

    @Step("Choose Turkey Address if exist")
    public void regionChooseIfExist() {
        try {
            WebElement chooseTurkeyAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.az-action.tr-button")));
            wait.until(ExpectedConditions.visibilityOf(chooseTurkeyAddress)).click();
            System.out.println("Turkey Address chosen.");
        } catch (Exception e) {
            System.out.println("choseTurkeyAddress element is not displayed");
        }
    }

    @Step("Verify the page title is correct")
    public void verifyPageTitle() {
        String expectedTitle = "En Trend Ürünler Türkiye'nin Online Alışveriş Sitesi Trendyol'da";
        String actualTitle = driver.getTitle();
        Assertions.assertEquals(expectedTitle, actualTitle, "Page title is not as expected");
    }

    @Step("Click on the Login button on the homepage")
    public void clickLogin() {
        WebElement login = driver.findElement(By.cssSelector("div.link.account-user > p"));
        wait.until(ExpectedConditions.visibilityOf(login)).click();
        System.out.println("Login button clicked");

    }

    @Step("Typing incorrect <emailAddress> in the email field")
    public void emailLogin(String emailAddress) {
        WebElement email = driver.findElement(By.cssSelector("#login-email"));
        wait.until(ExpectedConditions.visibilityOf(email)).sendKeys(emailAddress);
    }

    @Step("Typing incorrect <password> in the password field")
    public void passwordLogin(String password) {
        WebElement passwordLogin = driver.findElement(By.cssSelector("#login-password-input"));
        wait.until(ExpectedConditions.visibilityOf(passwordLogin)).sendKeys(password);
    }

    @Step("Click on the login button")
    public void verifyLogin() {
        WebElement loginButton = driver.findElement(By.cssSelector("div.q-layout.login > form > button"));
        wait.until(ExpectedConditions.visibilityOf(loginButton)).click();

        WebElement loginResult = driver.findElement(By.cssSelector("#error-box-wrapper > span.message"));
        String loginResultText = wait.until(ExpectedConditions.visibilityOf(loginResult)).getText();

        boolean isContain = loginResultText.contains("E-posta adresiniz ve/veya şifreniz hatalı.");
        Assertions.assertTrue(isContain, "The search result is not contain " + "E-posta adresiniz ve/veya şifreniz hatalı." + ". Search result is " + loginResultText);

    }

    @Step("Click on the search bar and type <productName>")
    public void searchProduct(String productName) {
        WebElement searchBox = driver.findElement(By.cssSelector("div.N4M8bfaJ > input"));
        wait.until(ExpectedConditions.visibilityOf(searchBox)).sendKeys(productName);

        WebElement searchButton = driver.findElement(By.cssSelector("div.N4M8bfaJ > i"));
        wait.until(ExpectedConditions.visibilityOf(searchButton)).click();

    }

    @Step("Select a random product")
    public void selectRandomProduct() {
        List < WebElement > productResults = driver.findElements(By.cssSelector(".p-card-wrppr"));
        Random rand = new Random();
        int randomIndex = rand.nextInt(productResults.size());
        WebElement randomProduct = productResults.get(randomIndex);
        wait.until(ExpectedConditions.elementToBeClickable(randomProduct)).click();

    }

    @Step("Click on the add to cart button")
    public void addCart() {
        String mainPageHandle = driver.getWindowHandle();
        Set < String > allWindowHandles = driver.getWindowHandles();
        for (String handle: allWindowHandles) {
            if (!handle.equals(mainPageHandle)) {

                driver.switchTo().window(handle);
                try {
                    WebElement campaign = driver.findElement(By.cssSelector("div.campaign-button.bold"));
                    wait.until(ExpectedConditions.visibilityOf(campaign)).click();
                } catch (Exception E) {
                    System.out.println("campaign button is not visible.");
                }
                //Is it Campaign Product
                WebElement featuredPriceContainer = null;
                try {
                    featuredPriceContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.product-price-container")));
                } catch (Exception e) {
                    System.out.println("featuredPriceContainer is not visible");
                }
                if (featuredPriceContainer == null) {
                    WebElement initialPriceLocator = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.product-price-container > div > div > div > div.featured-prices > span")));
                    initialPrice = Double.parseDouble(initialPriceLocator.getText().replace(".","").replace("TL", "").trim().replaceAll(",", "."));
                } else {
                    WebElement initialPriceLocator = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.product-price-container > div > div > span")));
                    initialPrice = Double.parseDouble(initialPriceLocator.getText().replace(".","").replace("TL", "").trim().replaceAll(",", "."));
                }
                WebElement addChart = driver.findElement(By.cssSelector("div.add-to-basket-button-text"));
                wait.until(ExpectedConditions.visibilityOf(addChart)).click();

            }
        }
    }

    @Step("Click on the my basket button on the page")
    public void basketControl() {
        WebElement basket = driver.findElement(By.cssSelector("div.account-nav-item.basket-preview > a > p"));
        wait.until(ExpectedConditions.visibilityOf(basket)).click();
        try {
            WebElement additionalService = driver.findElement(By.cssSelector("div.pb-basket-item-details > div > div > div:nth-child(3) > div > div > div > button"));
            wait.until(ExpectedConditions.visibilityOf(additionalService)).click();

        } catch (Exception e) {
            System.out.println("additionalService button is not visible.");
        }
        WebElement checkBasket = driver.findElement(By.cssSelector("div.pb-header"));
        Assertions.assertTrue(checkBasket.isDisplayed(), "Basket is empty");
    }

    @Step("Verifying that prices remain the same")
    public void verifyPriceSame() throws InterruptedException {
        WebElement basketPriceLocator = driver.findElement(By.cssSelector("div.pb-basket-item-price-and-badge > div > div"));
        Thread.sleep(2000);
        Double basketPrice = Double.parseDouble(basketPriceLocator.getText().replace(".","").replace("TL", "").trim().replaceAll(",", "."));
        System.out.println("First Price: " + initialPrice + ". Second Price:" + basketPrice);
        Assertions.assertEquals(initialPrice, basketPrice, "Priceses are not same");
    }
    @Step("Verify <> is displayed search page")
    public void verifySearchText(String text) {

        WebElement searchResult = driver.findElement(By.cssSelector("div.srch-rslt-title > div.srch-ttl-cntnr-wrppr > div"));
        String searchResultText = wait.until(ExpectedConditions.visibilityOf(searchResult)).getText();
        boolean isContain = searchResultText.contains(text);
        Assertions.assertTrue(isContain, "The search result is not contain " + text + ". Search result is " + searchResultText);

    }
    @Step("Click on the product plus button on the basket page")
    public void increaseProduct() {
        WebElement initialPriceLocator = driver.findElement(By.cssSelector("div.pb-basket-item-price-and-badge > div > div"));
        initialPrice = Double.parseDouble(initialPriceLocator.getText().replace(".","").replace("TL", "").trim().replaceAll(",", "."));
        WebElement plusButton = driver.findElement(By.cssSelector("div.pb-basket-item-counter-wrapper > div > div > button:nth-child(3)"));
        wait.until(ExpectedConditions.visibilityOf(plusButton)).click();
    }
    @Step("Click on the bin button next to the product on the basket")
    public void deleteBasket() {
        WebElement binButton = driver.findElement(By.cssSelector("div.pb-basket-item-actions > button > i"));
        wait.until(ExpectedConditions.visibilityOf(binButton)).click();
    }

    @Step("Check product detail page is opened")
    public void checkProductDetail() {
        String mainPageHandle = driver.getWindowHandle();
        Set < String > allWindowHandles = driver.getWindowHandles();
        for (String handle: allWindowHandles) {
            if (!handle.equals(mainPageHandle)) {
                driver.switchTo().window(handle);
                WebElement checkProduct = driver.findElement(By.cssSelector("div.add-to-basket-button-text"));
                Assertions.assertTrue(checkProduct.isDisplayed(), "Product detail page is not opened");
            }
        }
    }

    @Step("Verify product price and quantity increment")
    public void verifyProductValue() throws InterruptedException {
        WebElement finalProduct = driver.findElement(By.cssSelector("div.pb-basket-item-price-and-badge > div > div"));
        Thread.sleep(2000);
        Double finalProductPrice = Double.parseDouble(finalProduct.getText().replace("TL", "").trim().replaceAll(",", "."));
        System.out.println("First Price: " + initialPrice + ". Second Price:" + finalProductPrice);
        Assertions.assertEquals(initialPrice * 2, finalProductPrice, "Priceses are not same");
        Thread.sleep(2000);
        WebElement productQuantity = driver.findElement(By.cssSelector("div.pb-basket-item-counter-wrapper > div > div > input"));
        int actualQuantity = Integer.parseInt(wait.until(ExpectedConditions.visibilityOf(productQuantity)).getAttribute("value"));
        Assertions.assertEquals(2, actualQuantity, "Quantities are not same");

    }
}