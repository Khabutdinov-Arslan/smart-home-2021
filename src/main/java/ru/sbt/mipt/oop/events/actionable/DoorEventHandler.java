package ru.sbt.mipt.oop.events.actionable;

import ru.sbt.mipt.oop.sensor_commands.PrintCommandSender;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.types.SensorEventDoorClose;
import ru.sbt.mipt.oop.events.types.SensorEventDoorOpen;

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
                if (event.getType() instanceof SensorEventDoorOpen) {
                    door.setOpen(true);
                    System.out.println("Door " + door.getId() + " was opened.");
                }
                if (event.getType() instanceof SensorEventDoorClose){
                    door.setOpen(false);
                    System.out.println("Door " + door.getId() + " was closed.");
                }
            }
        }));

    }

    SmartHome smartHome;
    PrintCommandSender commandPrinter;
}
