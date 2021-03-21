package ru.sbt.mipt.oop.helpers;

import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.events.Action;
import ru.sbt.mipt.oop.components.Actionable;

public class FindLightByIdAction implements Action {
    private Light result;
    private final String id;

    FindLightByIdAction(String id){
        this.id = id;
        this.result = null;
    }

    @Override
    public void act(Actionable component) {
        if (component instanceof Light){
            Light light = (Light)component;
            if (light.getId().equals(id)){
                this.result = light;
            }
        }
    }

    public Light getResult(){
        return result;
    }
}
