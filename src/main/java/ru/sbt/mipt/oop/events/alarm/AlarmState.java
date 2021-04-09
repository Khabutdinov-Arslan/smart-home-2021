package ru.sbt.mipt.oop.events.alarm;

public interface AlarmState {
    void activateAlarm(int code);
    void deactivateAlarm(int code);
    void fireAlarm();
}
