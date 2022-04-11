package co.com.cesar.serenityrest.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class UsingPostMethod3 implements Task {

    String service;
    Object request;

    public UsingPostMethod3(String service, Object request) {
        this.service = service;
        this.request = request;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to(service).with(requestSpecification -> requestSpecification
                        .contentType("application/json")
                        .body(request)
                )
        );
    }


    public static UsingPostMethod3 with(String service, Object request) {
        return Instrumented.instanceOf(UsingPostMethod3.class).withProperties(service, request);
    }
}
