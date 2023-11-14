package com.example.definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class HomePageDefinitions {
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

    @When("LastPass enters username and password")
    public void loginTest() {

        // login to application
        // Note:  This test environment uses LastPass to autofill the username and password fields.
        driver.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"signInSubmit\"]")).click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@class='oxd-form']/div[3]/button")).submit();
    }

    @Then("User should be able to login successfully and new page open")
    public void verifyLogin() {

        String welcomeUserString = driver.findElement(By.xpath("//*[@id=\"nav-link-accountList-nav-line-1\"]")).getText();

        //Verify new page - HomePage
        Assert.assertEquals(welcomeUserString, "Hello, Daniel");

    }

    @After
    public void teardown() {

        driver.quit();
    }

}
