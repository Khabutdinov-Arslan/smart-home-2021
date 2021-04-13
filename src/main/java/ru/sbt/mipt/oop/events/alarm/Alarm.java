package ru.sbt.mipt.oop.events.alarm;

public class Alarm {
    private AlarmState state;

    public Alarm(){
        this.state = new AlarmDeactivated(this);
    }

    void setState(AlarmState state){
        this.state=state;
    }

    public void activate(int code){
        state.activateAlarm(code);
    }

    public void deactivate(int code){
        state.deactivateAlarm(code);
    }

    public void fire(){
        state.fireAlarm();
    }

    public Boolean isFiring(){
        return this.state instanceof AlarmFiring;
    }

    public Boolean isActivated() {return this.state instanceof AlarmActivated;}

    public Boolean isDeactivated() {return this.state instanceof AlarmDeactivated;}
}
