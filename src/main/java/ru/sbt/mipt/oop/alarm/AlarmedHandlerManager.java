package ru.sbt.mipt.oop.alarm;

import ru.sbt.mipt.oop.events.HandlerManager;
import ru.sbt.mipt.oop.events.SensorEvent;

public class AlarmedHandlerManager implements HandlerManager {
    private final Alarm alarm;
    private final HandlerManager handlerManager;

    public AlarmedHandlerManager(Alarm alarm, HandlerManager handlerManager){
        this.alarm = alarm;
        this.handlerManager = handlerManager;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        alarm.handleEvent(event);
        if (!alarm.isFiring()){
            handlerManager.handleEvent(event);
        }
    }
}
