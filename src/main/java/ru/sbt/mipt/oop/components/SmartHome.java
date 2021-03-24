package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.events.Action;
import ru.sbt.mipt.oop.components.Actionable;
import ru.sbt.mipt.oop.events.Alarm;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    private Collection<Room> rooms;
    private Alarm alarm;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    @Override
    public void execute(Action action){
        action.act(this);
        for (Room room:rooms){
            room.execute(action);
        }
    }

    public Alarm getAlarm(){
        return alarm;
    }

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

}
