package com.example.definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Slf4j
public class LogoutDefinitions {
    private static WebDriver driver;
    public final static int TIMEOUT = 5;

    @Before
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIMEOUT));

    }

    @Given("User is on Amazon homepage {string}")
    public void goToURLTest(String url) {

        driver.get(url);

    }

    @Given("The user is logged in, evidenced by the account greeting {string}")
    public void checkAccountGreeting(String greeting) {

        String welcomeUserString = driver.findElement(By.xpath("//*[@id=\"nav-link-accountList-nav-line-1\"]")).getText();

        //Verify logged-in status
        Assert.assertEquals(welcomeUserString, greeting);

    }


    @When("User hovers over, the Accounts & Lists tab and clicks Sign Out")
    public void logoutTest() {

        // login to application
        WebElement accountNavButton = driver.findElement(By.id("//*[@id=\"nav-link-accountList\"]"));
        WebElement signOutButton = driver.findElement(By.id("//*[@id=\"nav-item-signout\"]/span"));
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));

        actions.moveToElement(accountNavButton).perform();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(signOutButton));
            log.info("Button is clickable");
        } catch (TimeoutException e) {
            log.error("Button still not clickable after 3 seconds", e);
        }

        actions.moveToElement(accountNavButton).click(signOutButton).perform();
    }

    @Then("User should be able to logout successfully")
    public void verifyLogout() {

        String welcomeUserString = driver.findElement(By.xpath("//*[@id=\"nav-link-accountList-nav-line-1\"]")).getText();

        //Verify new page - HomePage
        Assert.assertEquals(welcomeUserString, "Hello, sign in");

    }

    @After
    public void quit() {

        driver.quit();
    }

}
