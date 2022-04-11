package co.com.cesar.serenityrest.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class UsingGetMethod2 implements Task {

    private String service;
    private String parameterName;
    private int parameterValue;


    public UsingGetMethod2(String service, String parameterName, int parameterValue) {
        this.service = service;
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
       actor.attemptsTo(
               Get.resource(service).with(
                       requestSpecification -> requestSpecification.queryParam(parameterName, parameterValue)
               )
       );
    }

    public static UsingGetMethod2 withTheAPI(String service, String parameterName, int parameterValue){
        return Instrumented.instanceOf(UsingGetMethod2.class).withProperties(service, parameterName, parameterValue);
    }
}
