package ru.sbt.mipt.oop.components;

import ru.sbt.mipt.oop.components.Room;
import ru.sbt.mipt.oop.events.Action;
import ru.sbt.mipt.oop.components.Actionable;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    Collection<Room> rooms;

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

    public SmartHome(Collection<Room> rooms) {
        this.rooms = rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }
}
