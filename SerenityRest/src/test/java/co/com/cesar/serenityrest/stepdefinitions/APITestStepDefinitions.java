package co.com.cesar.serenityrest.stepdefinitions;

import co.com.cesar.serenityrest.models.Login;
import co.com.cesar.serenityrest.models.User;
import co.com.cesar.serenityrest.models.UsersResponse;
import co.com.cesar.serenityrest.questions.Check;
import co.com.cesar.serenityrest.questions.StatusCode;
import co.com.cesar.serenityrest.tasks.*;

/**
 *      Serenity 2.1.5
 */
//import cucumber.api.java.en.Given;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;

/**
 *      Serenity 3.0.5
 */
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static co.com.cesar.serenityrest.utils.Constants.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;
import static net.serenitybdd.screenplay.rest.questions.ResponseConsequence.seeThatResponse;


public class APITestStepDefinitions {


    @Given("^that the user wants to make a request$")
    public void thatTheUserWantsToMakeARequest() {
        ACTOR.wasAbleTo(
                Connect.withTheEndpoint(ENDPOINT)
        );
    }

    @When("^he makes the request, using the GET method$")
    public void heMakesTheRequestUsingTheGETMethod() {
        String parameter = "users?page=2";
        ACTOR.attemptsTo(
                UsingGetMethod.withTheAPI(parameter)
        );
       /*
        ACTOR.attemptsTo(
                Consume2.anAPI("users", "page", 2)
        );
        */
    }

    @Then("^the service return the data$")
    public void theServiceReturnTheData() {
        UsersResponse usersResponse = Check.theUsersResponse().answeredBy(ACTOR);
        ACTOR.should(

                seeThat("Status code", StatusCode.is(), equalTo(STATUS_CODE_200)),
                seeThat("Total Registers", ACTOR -> usersResponse.getTotal(), equalTo(12)),
                seeThat("Support URL", ACTOR -> usersResponse.getSupport().getUrl(), containsString("reqres.in")),
                seeThat("First name", ACTOR -> usersResponse.getData().get(3).getFirst_name(), equalTo("Byron")),
                seeThat("total per pag", ACTOR -> usersResponse.getPer_page(), equalTo(usersResponse.getData().size())),
                seeThatResponse("All users on page 2 should be returned", response -> response.body("data.first_name", hasItems("Tobias", "Lindsay", "Rachel"))),
                seeThatResponse("status code", response -> response.statusCode(200)),


                seeThatResponse(response -> response
                        .statusCode(200)
                        .body("data.first_name", hasItems("Tobias", "Lindsay", "Rachel"))
                        .body("total", equalTo(12))
                        .body("support.url", containsString("reqres.in"))
                )
        );
    }


    /////////////POST METHOD


    @When("^he wants to register a new user, using the POST method1$")
    public void heWantsToRegisterANewUserUsingThePOSTMethod1(){

        //// opcion 1
        /*
        String request = "{\n" +
                "    \"email\": \"eve.holt@reqres.in\",\n" +
                "    \"password\": \"pistol\"\n" +
                "}";

            ACTOR.attemptsTo(
               UsingPostMethod1.withRequest(request)
        );

         */

        Login login = new Login("eve.holt@reqres.in", "pistol");
        ACTOR.attemptsTo(
                // UsingPostMethod1.withRequest(request)
                UsingPostMethod2.with("register", login)
        );
    }

    @Then("^the user is registered correctly$")
    public void theUserIsRegisteredCorrectly(){
        ACTOR.should(
                seeThatResponse(request -> request
                        .statusCode(200)
                        .body("id", equalTo(4))
                        .body("token", equalTo("QpwL5tke4Pnpja7X4"))
                )
        );
    }

    @When("^he makes the request, using the POST method2$")
    public void
    heMakesTheRequestUsingThePOSTMethod2() {
        Login login = new Login("eve.holt@reqres.in", "pistol");
        User user = new User("morpheus", "leader");
        String email = "{\n" +
                "    \"email\": \"peter@klaven\"\n" +
                "}";
        ACTOR.attemptsTo(
                UsingPostMethod3.with("register", login),
                UsingPostMethod3.with("users", user),
                UsingPostMethod3.with("login", email)
        );
    }


    @Then("^a valid response is returned$")
    public void aValidResponseIsReturned() {
       ACTOR.should(
               seeThat("Status code", StatusCode.is(), equalTo(400))
       );
    }


}
