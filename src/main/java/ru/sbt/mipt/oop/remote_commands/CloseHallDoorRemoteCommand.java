package ru.sbt.mipt.oop.remote_commands;

import ru.sbt.mipt.oop.components.Door;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.helpers.SmartHomeHelpers;

public class CloseHallDoorRemoteCommand extends SmartHomeRemoteCommand{

    public CloseHallDoorRemoteCommand(SmartHome smartHome) {
        super(smartHome);
    }

    @Override
    public void execute(){
        smartHome.execute((component -> {
            if (component instanceof Door){
                Door door = (Door)component;
                if (SmartHomeHelpers.isHallDoor(smartHome, door.getId())){
                    door.setOpen(false);
                }
            }
        }));
    }
}
