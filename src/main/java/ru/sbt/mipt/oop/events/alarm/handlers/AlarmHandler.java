package ru.sbt.mipt.oop.events.alarm.handlers;

import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.alarm.Alarm;
import ru.sbt.mipt.oop.events.alarm.AlarmNotifier;

public class AlarmHandler {
    protected final Alarm alarm;
    private AlarmHandler nextHandler;
    protected EventHandler handler;

    public AlarmHandler(EventHandler handler, Alarm alarm){
        this.nextHandler = null;
        this.handler = handler;
        this.alarm = alarm;
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
