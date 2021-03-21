package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.commands.PrintCommandSender;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.components.Door;

import static ru.sbt.mipt.oop.events.SensorEventType.*;

public class DoorEventHandler implements EventHandler {

    public DoorEventHandler(SmartHome smartHome){
        this.smartHome = smartHome;
        this.commandPrinter = new PrintCommandSender();
    }

    @Override
    public void handleEvent(SensorEvent event) {
        smartHome.execute((component -> {
            if (component instanceof Door){
                Door door = (Door)component;
                if (!door.getId().equals(event.getObjectId())) {
                    return;
                }
                if (event.getType() == DOOR_OPEN) {
                    door.setOpen(true);
                    System.out.println("Door " + door.getId() + " was opened.");
                }
                if (event.getType() == DOOR_CLOSED){
                    door.setOpen(false);
                    System.out.println("Door " + door.getId() + " was closed.");
                }
            }
        }));

    }

    SmartHome smartHome;
    PrintCommandSender commandPrinter;
}
