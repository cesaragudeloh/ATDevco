package co.com.cesar.serenityrest.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;


public class Connect implements Task {

    private String endpoint;

    public Connect(String url) {
        this.endpoint = url;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        SerenityRest.reset();
        actor.whoCan(
                CallAnApi.at(endpoint)
        );
    }

    public static Connect withTheEndpoint(String endpoint){
        return Instrumented.instanceOf(Connect.class).withProperties(endpoint);
    }
}
