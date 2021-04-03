package ru.sbt.mipt.oop.events.alarm;

import ru.sbt.mipt.oop.events.AlarmState;
import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.events.types.SensorEventAlarmActivate;
import ru.sbt.mipt.oop.events.types.SensorEventAlarmDeactivate;

public class AlarmActivated implements AlarmState {
    private final Alarm alarm;
    private final int code;

    public AlarmActivated(Alarm alarm, int code) {
        this.alarm = alarm;
        this.code = code;
    }


    @Override
    public void handleEvent(SensorEvent event) {
        if (event.getType() instanceof SensorEventAlarmDeactivate){
            SensorEventAlarmDeactivate alarmEvent = (SensorEventAlarmDeactivate)event.getType();
            if (alarmEvent.getCode() == code){
                alarm.setState(new AlarmDeactivated(alarm));
            }
        }
        if (event.getType() instanceof  SensorEventAlarmActivate){
            return;
        }
        alarm.setState(new AlarmFiring(alarm, code));
    }
}
