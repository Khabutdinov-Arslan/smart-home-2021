package ru.sbt.mipt.oop.events.alarm;

import ru.sbt.mipt.oop.events.AlarmState;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.types.SensorEventAlarmActivate;

public class AlarmDeactivated implements AlarmState {
    Alarm alarm;

    public AlarmDeactivated(Alarm alarm){
        this.alarm = alarm;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event.getType() instanceof SensorEventAlarmActivate){
            SensorEventAlarmActivate alarmEvent = (SensorEventAlarmActivate)event.getType();
            alarm.setState(new AlarmActivated(alarm, alarmEvent.getCode()));
        }
    }
}
