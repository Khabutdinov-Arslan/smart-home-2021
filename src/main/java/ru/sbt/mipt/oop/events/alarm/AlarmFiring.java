package ru.sbt.mipt.oop.events.alarm;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.types.SensorEventAlarmDeactivate;

public class AlarmFiring implements AlarmState {

    private final Alarm alarm;
    private final int code;

    public AlarmFiring(Alarm alarm, int code) {
        this.alarm = alarm;
        this.code = code;
    }

    @Override
    public void activateAlarm(int code) {

    }

    @Override
    public void deactivateAlarm(int code) {
        if (code == this.code){
            alarm.setState(new AlarmDeactivated(alarm));
        }
    }

    @Override
    public void fireAlarm() {

    }
}
