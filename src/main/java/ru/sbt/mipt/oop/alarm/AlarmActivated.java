package ru.sbt.mipt.oop.alarm;

public class AlarmActivated implements AlarmState {
    private final Alarm alarm;
    private final int code;

    public AlarmActivated(Alarm alarm, int code) {
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
        alarm.setState(new AlarmFiring(alarm, code));
    }
}
