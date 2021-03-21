package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.SmartHome;

import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_ON;
import static ru.sbt.mipt.oop.events.SensorEventType.LIGHT_OFF;

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
                        if (event.getType() == LIGHT_ON) {
                            light.setOn(true);
                            System.out.println("Light " + light.getId() + " was turned on.");
                        }
                        if (event.getType() == LIGHT_OFF) {
                            light.setOn(false);
                            System.out.println("Light " + light.getId() + " was turned off.");
                        }
                    }
                }
            });
    }

    SmartHome smartHome;
}
