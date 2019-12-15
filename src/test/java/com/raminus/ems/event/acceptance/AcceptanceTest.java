package com.raminus.ems.event.acceptance;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue = {"classpath:com.raminus.ems.event.acceptance"},
        features = {"classpath:acceptance"},
        plugin = {"pretty", "html:target/cucumber-report-html"})
public class AcceptanceTest {

}
