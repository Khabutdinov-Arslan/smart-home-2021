package ru.sbt.mipt.oop.events.alarm.handlers;

import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.alarm.Alarm;
import ru.sbt.mipt.oop.events.alarm.AlarmNotifier;

public class AlarmHandler {
    private AlarmHandler nextHandler;
    protected EventHandler handler;
    protected Alarm alarm;
    protected AlarmNotifier notifier;

    public AlarmHandler(EventHandler handler, Alarm alarm, AlarmNotifier notifier){
        this.nextHandler = null;
        this.handler = handler;
        this.alarm = alarm;
        this.notifier = notifier;
    }

    public AlarmHandler setNextHandler(AlarmHandler handler){
        nextHandler = handler;
        return handler;
    }

    public void passEvent(SensorEvent event){
        if (nextHandler != null){
            nextHandler.handleEvent(event);
        }
    }

    public void handleEvent(SensorEvent event){
        passEvent(event);
    }
}
