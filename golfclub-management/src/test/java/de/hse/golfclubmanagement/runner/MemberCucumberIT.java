package de.hse.golfclubmanagement.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "de.hse.golfclubmanagement.steps",
        plugin = {"pretty", "summary", "html:target/cucumber-report.html"},
        monochrome = true // Klarere Konsolenausgabe
)
public class MemberCucumberIT {
}
