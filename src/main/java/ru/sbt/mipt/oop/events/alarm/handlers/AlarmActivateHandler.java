package ru.sbt.mipt.oop.events.alarm.handlers;

import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.events.alarm.AlarmNotifier;
import ru.sbt.mipt.oop.events.types.SensorEventAlarmActivate;

public class AlarmActivateHandler extends AlarmHandler {
    public AlarmActivateHandler(EventHandler handler, Alarm alarm) {
        super(handler, alarm);
    }

    @Override
    public void handleEvent(SensorEvent event){
        if ((event.getType() instanceof SensorEventAlarmActivate) && (!alarm.isFiring())){
            alarm.activate(((SensorEventAlarmActivate)event.getType()).getCode());
        }else {
            passEvent(event);
        }
    }
}
