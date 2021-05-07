package ru.sbt.mipt.oop.events.alarm.handlers;

import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.events.alarm.AlarmNotifier;
import ru.sbt.mipt.oop.events.types.SensorEventAlarmDeactivate;

public class AlarmDeactivateHandler extends AlarmHandler {
    private final Alarm alarm;

    public AlarmDeactivateHandler(EventHandler handler, Alarm alarm) {
        super(handler, alarm);
        this.alarm = alarm;
    }

    @Override
    public void handleEvent(SensorEvent event){
        if (event.getType() instanceof SensorEventAlarmDeactivate){
            alarm.deactivate(((SensorEventAlarmDeactivate)event.getType()).getCode());
        }else {
            passEvent(event);
        }
    }
}
