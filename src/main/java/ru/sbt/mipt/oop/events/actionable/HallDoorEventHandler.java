package ru.sbt.mipt.oop.events.actionable;

import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.commands.PrintCommandSender;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.types.SensorEventDoorClose;
import ru.sbt.mipt.oop.helpers.SmartHomeHelpers;

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
                if (event.getType() instanceof SensorEventDoorClose){
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
