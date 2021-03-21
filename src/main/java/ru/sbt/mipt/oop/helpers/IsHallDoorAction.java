package ru.sbt.mipt.oop.helpers;

import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.events.Action;
import ru.sbt.mipt.oop.components.Actionable;
import ru.sbt.mipt.oop.components.Door;

public class IsHallDoorAction implements Action {
    private Boolean result;
    private String roomName;
    private final String id;

    IsHallDoorAction(String id){
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
        if (component instanceof Door){
            Door door = (Door)component;
            if (roomName.equals("hall") && door.getId().equals(id)) {
                result = true;
            }
        }
    }

    public Boolean getResult(){
        return result;
    }
}
