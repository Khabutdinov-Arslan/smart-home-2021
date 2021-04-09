package ru.sbt.mipt.oop.events.alarm;

import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.alarm.handlers.*;

public class AlarmedEventHandler implements EventHandler{
    Alarm alarm;
    EventHandler handler;
    AlarmNotifier notifier;
    AlarmHandler alarmHandler;

    public AlarmedEventHandler(Alarm alarm, EventHandler handler){
        this.alarm = alarm;
        this.handler = handler;
        this.notifier = new AlarmNotifier();
        this.alarmHandler = new AlarmActivateHandler(handler, alarm, notifier);
        alarmHandler.setNextHandler(new AlarmDeactivateHandler(handler, alarm, notifier))
                .setNextHandler(new AlarmFireHandler(handler, alarm, notifier))
                .setNextHandler(new AlarmNormalHandler(handler, alarm, notifier));
    }


    @Override
    public void handleEvent(SensorEvent event) {
        alarmHandler.handleEvent(event);
    }
}
