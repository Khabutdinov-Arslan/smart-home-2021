package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;

public class AlarmedEventHandler implements EventHandler{
    Alarm alarm;
    EventHandler handler;

    public AlarmedEventHandler(Alarm alarm, EventHandler handler){
        this.alarm = alarm;
        this.handler = handler;
    }


    @Override
    public void handleEvent(SensorEvent event) {
        alarm.handleEvent(event);
        if (!alarm.isFiring()){
            handler.handleEvent(event);
        }
    }
}
