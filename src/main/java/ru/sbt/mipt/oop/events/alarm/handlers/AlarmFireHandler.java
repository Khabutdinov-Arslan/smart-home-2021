package ru.sbt.mipt.oop.events.alarm.handlers;

import ru.sbt.mipt.oop.events.EventHandler;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.alarm.Alarm;
import ru.sbt.mipt.oop.events.alarm.AlarmNotifier;
import ru.sbt.mipt.oop.events.types.SensorEventAlarmActivate;
import ru.sbt.mipt.oop.events.types.SensorEventAlarmDeactivate;

public class AlarmFireHandler extends AlarmHandler {

    public AlarmFireHandler(EventHandler handler, Alarm alarm, AlarmNotifier notifier) {
        super(handler, alarm, notifier);
    }

    @Override
    public void handleEvent(SensorEvent event){
        if ((!alarm.isDeactivated()) && (!(event.getType() instanceof SensorEventAlarmDeactivate))
                && (!(event.getType() instanceof SensorEventAlarmActivate))){
            alarm.fire();
            notifier.sendSMS();
        }else {
            passEvent(event);
        }
    }
}
