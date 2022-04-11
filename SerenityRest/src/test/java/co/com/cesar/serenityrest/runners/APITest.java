package co.com.cesar.serenityrest.runners;

import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 *      Serenity 2.1.5
 */
//import cucumber.api.CucumberOptions;
//import static cucumber.api.SnippetType.CAMELCASE;


/**
 *      Serenity 3.0.5
 */
import io.cucumber.junit.CucumberOptions;
import static io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/api_test.feature",
        glue = "co.com.cesar.serenityrest.stepdefinitions",
        snippets = CAMELCASE)
public class APITest {
}
