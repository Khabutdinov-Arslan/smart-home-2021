package ru.sbt.mipt.oop.remote_commands;

import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.helpers.SmartHomeHelpers;

public class TurnOnHallLightsRemoteCommand extends SmartHomeRemoteCommand{

    public TurnOnHallLightsRemoteCommand(SmartHome smartHome) {
        super(smartHome);
    }

    @Override
    public void execute(){
        smartHome.execute((component -> {
            if (component instanceof Light){
                Light light = (Light)component;
                if (SmartHomeHelpers.isHallLight(smartHome, light.getId())){
                    light.setOn(true);
                }
            }
        }));
    }
}
