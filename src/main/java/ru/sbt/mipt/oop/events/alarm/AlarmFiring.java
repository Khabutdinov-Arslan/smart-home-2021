package ru.sbt.mipt.oop.events.alarm;

import ru.sbt.mipt.oop.events.AlarmState;
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
    public void handleEvent(SensorEvent event) {
        if (event.getType() instanceof SensorEventAlarmDeactivate){
            SensorEventAlarmDeactivate alarmEvent = (SensorEventAlarmDeactivate)event.getType();
            if (alarmEvent.getCode() == code){
                alarm.setState(new AlarmDeactivated(alarm));
            }
        }
        System.out.println("Alarm");
    }
}
