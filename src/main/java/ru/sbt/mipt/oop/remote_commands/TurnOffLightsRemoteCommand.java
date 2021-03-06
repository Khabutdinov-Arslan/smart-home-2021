package ru.sbt.mipt.oop.remote_commands;

import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.SmartHome;

public class TurnOffLightsRemoteCommand implements RemoteCommand{
    SmartHome smartHome;

    public TurnOffLightsRemoteCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
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
