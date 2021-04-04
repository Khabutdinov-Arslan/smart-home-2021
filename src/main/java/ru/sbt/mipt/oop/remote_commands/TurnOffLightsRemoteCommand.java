package ru.sbt.mipt.oop.remote_commands;

import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.SmartHome;

public class TurnOffLightsRemoteCommand extends SmartHomeRemoteCommand{

    public TurnOffLightsRemoteCommand(SmartHome smartHome) {
        super(smartHome);
    }

    @Override
    public void execute(){
        smartHome.execute((component -> {
            if (component instanceof Light){
                Light light = (Light)component;
                light.setOn(false);
            }
        }));
    }
}
