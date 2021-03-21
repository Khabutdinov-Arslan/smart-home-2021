package ru.sbt.mipt.oop.helpers;

import ru.sbt.mipt.oop.events.Action;
import ru.sbt.mipt.oop.components.Actionable;
import ru.sbt.mipt.oop.components.Door;

public class FindDoorByIdAction implements Action {
    private Door result;
    private final String id;

    FindDoorByIdAction(String id){
        this.id = id;
        this.result = null;
    }

    @Override
    public void act(Actionable component) {
        if (component instanceof Door){
            Door door = (Door)component;
            if (door.getId().equals(id)){
                this.result = door;
            }
        }
    }

    public Door getResult(){
        return result;
    }
}
