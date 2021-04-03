package ru.sbt.mipt.oop.events.alarm;

import ru.sbt.mipt.oop.events.AlarmState;
import ru.sbt.mipt.oop.events.SensorEvent;

public class Alarm {
    private AlarmState state;

    public Alarm(){
        this.state = new AlarmDeactivated(this);
    }

    public void handleEvent(SensorEvent event){
        state.handleEvent(event);
    }

    public void setState(AlarmState state){
        this.state=state;
    }

    public Boolean isFiring(){
        return this.state instanceof AlarmFiring;
    }
}
