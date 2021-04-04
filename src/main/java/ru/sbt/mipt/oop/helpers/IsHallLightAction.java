package ru.sbt.mipt.oop.helpers;

import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.events.Action;
import ru.sbt.mipt.oop.components.Actionable;
import ru.sbt.mipt.oop.components.Door;

public class IsHallLightAction implements Action {
    private Boolean result;
    private String roomName;
    private final String id;

    IsHallLightAction(String id){
        this.id = id;
        this.result = false;
        this.roomName = "";
    }

    @Override
    public void act(Actionable component) {
        if (result){
            return;
        }
        if (component instanceof Room){
            roomName = ((Room) component).getName();
        }
        if (component instanceof Light){
            Light light = (Light)component;
            if (roomName.equals("hall") && light.getId().equals(id)) {
                result = true;
            }
        }
    }

    public Boolean getResult(){
        return result;
    }
}
