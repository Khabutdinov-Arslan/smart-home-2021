package ru.sbt.mipt.oop.events.alarm;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.types.SensorEventAlarmActivate;

public class AlarmDeactivated implements AlarmState {
    Alarm alarm;

    public AlarmDeactivated(Alarm alarm){
        this.alarm = alarm;
    }


    @Override
    public void activateAlarm(int code) {
        alarm.setState(new AlarmActivated(alarm, code));
    }

    @Override
    public void deactivateAlarm(int code) {

    }

    @Override
    public void fireAlarm() {

    }
}
