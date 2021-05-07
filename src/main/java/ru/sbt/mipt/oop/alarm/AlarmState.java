package ru.sbt.mipt.oop.alarm;

public interface AlarmState {
    void activateAlarm(int code);
    void deactivateAlarm(int code);
    void fireAlarm();
}
