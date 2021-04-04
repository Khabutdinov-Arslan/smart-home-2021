package ru.sbt.mipt.oop.events.types;

public class SensorEventAlarmFiring extends SensorEventType{
    private final int code;
    public SensorEventAlarmFiring(int code){
        this.code = code;
    }
    public int getCode(){
        return code;
    }
}
