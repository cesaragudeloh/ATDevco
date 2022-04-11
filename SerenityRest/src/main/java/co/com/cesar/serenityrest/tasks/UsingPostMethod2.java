package co.com.cesar.serenityrest.tasks;

import co.com.cesar.serenityrest.models.Login;
import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class UsingPostMethod2 implements Task {

    String service;
    Login login;

    public UsingPostMethod2(String service, Login login) {
        this.service = service;
        this.login = login;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(service).with(requestSpecification -> requestSpecification
                        .contentType("application/json")
                        .body(login)
                )
        );
    }


    public static UsingPostMethod2 with(String service, Login login) {
        return Instrumented.instanceOf(UsingPostMethod2.class).withProperties(service, login);
    }
}
