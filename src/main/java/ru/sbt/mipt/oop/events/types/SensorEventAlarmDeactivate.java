package ru.sbt.mipt.oop.events.types;

public class SensorEventAlarmDeactivate extends SensorEventType{
    private final int code;
    public SensorEventAlarmDeactivate(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }
}