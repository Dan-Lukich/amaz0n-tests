package com.example.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(tags = "", features = {"src/test/resources/features/Login.feature",
        "src/test/resources/features/Logout.feature",
        "src/test/resources/features/Search.feature"}, glue = {"com.example.definitions"},
        plugin = {})

public class CucumberRunnerTests extends AbstractTestNGCucumberTests {

}