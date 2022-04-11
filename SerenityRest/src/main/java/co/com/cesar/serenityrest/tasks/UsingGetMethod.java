package co.com.cesar.serenityrest.tasks;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class UsingGetMethod implements Task
{

    private String parameter;

    public UsingGetMethod(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
            actor.attemptsTo(
                    Get.resource(parameter)
            );
    }

    public static UsingGetMethod withTheAPI(String parameter){
        return Instrumented.instanceOf(UsingGetMethod.class).withProperties(parameter);
    }
}
