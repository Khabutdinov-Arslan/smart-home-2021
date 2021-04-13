package ru.sbt.mipt.oop.events.alarm;

import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.alarm.handlers.*;

public class AlarmedEventHandler implements EventHandler{
    private final Alarm alarm;
    private final EventHandler handler;
    private final AlarmNotifier notifier;
    private final AlarmHandler alarmHandler;

    public AlarmedEventHandler(Alarm alarm, EventHandler handler, AlarmNotifier notifier){
        this.alarm = alarm;
        this.handler = handler;
        this.notifier = notifier;
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
