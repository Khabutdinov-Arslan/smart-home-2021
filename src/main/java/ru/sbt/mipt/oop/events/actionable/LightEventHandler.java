package ru.sbt.mipt.oop.events.actionable;

import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.types.SensorEventLightOff;
import ru.sbt.mipt.oop.events.types.SensorEventLightOn;


public class LightEventHandler implements EventHandler {

    public LightEventHandler(SmartHome smartHome){
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(SensorEvent event) {
            smartHome.execute((component) -> {
                if (component instanceof Light) {
                    Light light = (Light) component;
                    if (light.getId().equals(event.getObjectId())) {
                        if (event.getType() instanceof SensorEventLightOn) {
                            light.setOn(true);
                            System.out.println("Light " + light.getId() + " was turned on.");
                        }
                        if (event.getType() instanceof SensorEventLightOff) {
                            light.setOn(false);
                            System.out.println("Light " + light.getId() + " was turned off.");
                        }
                    }
                }
            });
    }

    SmartHome smartHome;
}
