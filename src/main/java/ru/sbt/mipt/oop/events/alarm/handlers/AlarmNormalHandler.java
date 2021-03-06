package ru.sbt.mipt.oop.events.alarm.handlers;

import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.events.alarm.AlarmNotifier;

public class AlarmNormalHandler extends AlarmHandler {


    public AlarmNormalHandler(EventHandler handler, Alarm alarm) {
        super(handler, alarm);
    }

    @Override
    public void handleEvent(SensorEvent event){
        if (alarm.isDeactivated()) {
            handler.handleEvent(event);
        }else{
            passEvent(event);
        }
    }
}
