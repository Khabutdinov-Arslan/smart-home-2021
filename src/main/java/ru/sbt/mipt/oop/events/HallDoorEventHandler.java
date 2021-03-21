package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.commands.PrintCommandSender;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.helpers.SmartHomeHelpers;

import static ru.sbt.mipt.oop.events.SensorEventType.DOOR_CLOSED;

public class HallDoorEventHandler implements EventHandler {

    public HallDoorEventHandler(SmartHome smartHome){
        this.smartHome = smartHome;
        this.commandPrinter = new PrintCommandSender();
    }

    @Override
    public void handleEvent(SensorEvent event) {
        smartHome.execute((component -> {
            if (component instanceof Door){
                Door door = (Door)component;
                if (!SmartHomeHelpers.isHallDoor(smartHome, door.getId())){
                    return;
                }
                if (!door.getId().equals(event.getObjectId())) {
                    return;
                }
                if (event.getType() == DOOR_CLOSED){
                    door.setOpen(false);
                    System.out.println("Door (hall) " + door.getId() + " was closed.");
                    smartHome.execute((innerComponent)->{
                        if (innerComponent instanceof Light){
                            Light light = (Light)innerComponent;
                            System.out.println("Light " + light.getId() + " was turned off.");
                            light.setOn(false);
                        }
                    });
                }
            }
        }));
    }

    SmartHome smartHome;
    PrintCommandSender commandPrinter;
}
