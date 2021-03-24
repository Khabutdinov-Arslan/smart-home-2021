package ru.sbt.mipt.oop.events;

public class Alarm {
    private int code;
    private AlarmState state;

    public Alarm(){
        this.code = 0;
        this.state = AlarmState.DEACTIVATED;
    }

    public void activate(int code){
        if (state.equals(AlarmState.DEACTIVATED)){
            this.code = code;
            this.state = AlarmState.ACTIVATED;
        }
    }

    public void deactivate(int password){
        if ((state.equals(AlarmState.ACTIVATED) || state.equals(AlarmState.ALARM)) && (password == this.code)){
            this.code = 0;
            this.state = AlarmState.DEACTIVATED;
        }
    }

    public void alarm(){
        this.state = AlarmState.ALARM;
    }

    public AlarmState getState(){
        return state;
    }
}
