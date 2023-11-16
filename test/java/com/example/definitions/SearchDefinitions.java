package com.example.definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class SearchDefinitions {
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

    @When("User types {string} into the search bar")
    public void loginTest(String searchText) {

        // search
        WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"twotabsearchtextbox\"]"));
        searchBar.click();
        searchBar.sendKeys(searchText);
        WebElement searchButton = driver.findElement(By.xpath("//*[@id=\"nav-search-submit-button\"]"));
        searchButton.click();

    }

    @Then("The Overall Pick badge is visible on the first result")
    public void verifyLogin() {

        String welcomeUserString = driver.findElement(By.xpath("//*[@id=\"B0012SNLJG-amazons-choice-label\"]/span/div/span[1]")).getText();

        Assert.assertEquals(welcomeUserString, "Overall Pick");
    }

    @After
    public void teardown() {

        driver.quit();
    }

}
