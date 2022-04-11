package co.com.cesar.serenityrest.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;

public class UsingPostMethod1 implements Task {

    private String request;

    public UsingPostMethod1(String request) {
        this.request = request;
    }


    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Post.to("register").with(requestSpecification -> requestSpecification
                        //.contentType("application/json")
                        .header("Content-Type", "application/json")
                        //.header("Authorization","shjsadhjxehkahkwejrwk2379824hsdabnm")
                        .body(request))
        );
    }


    public static UsingPostMethod1 withRequest(String request) {
        return Instrumented.instanceOf(UsingPostMethod1.class).withProperties(request);
    }
}
