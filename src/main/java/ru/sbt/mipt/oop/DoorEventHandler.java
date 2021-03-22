package ru.sbt.mipt.oop;

import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_OPEN;

public class DoorEventHandler implements EventHandler{

    DoorEventHandler(SmartHome smartHome){
        this.smartHome = smartHome;
        this.commandPrinter = new PrintCommandSender();
    }

    @Override
    public void handleEvent(SensorEvent event) {

        if (!((event.getType() == DOOR_OPEN) || (event.getType() == DOOR_CLOSED))){
            return;
        }

        for (Room room : smartHome.getRooms()) {
            for (Door door : room.getDoors()) {
                if (door.getId().equals(event.getObjectId())) {
                    if (event.getType() == DOOR_OPEN) {
                        door.setOpen(true);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was opened.");
                    } else {
                        door.setOpen(false);
                        System.out.println("Door " + door.getId() + " in room " + room.getName() + " was closed.");
                    }
                }
            }
        }
    }

    SmartHome smartHome;
    PrintCommandSender commandPrinter;
}
