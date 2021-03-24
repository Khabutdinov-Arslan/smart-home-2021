package ru.sbt.mipt.oop.events.types;

public class SensorEventAlarmActivate extends SensorEventType{
    private final int code;
    public SensorEventAlarmActivate(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }
}
