package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.events.types.SensorEventAlarmActivate;
import ru.sbt.mipt.oop.events.types.SensorEventAlarmDeactivate;

import java.util.ArrayList;

public class AlarmedHandlerManager implements HandlerManager{

    public AlarmedHandlerManager(Alarm alarm, HandlerManager handlerManager){
        this.alarm = alarm;
        this.handlerManager = handlerManager;
    }

    @Override
    public void handleEvent(SensorEvent event) {
        if (event.getType() instanceof SensorEventAlarmActivate){
            SensorEventAlarmActivate alarmEvent = (SensorEventAlarmActivate)event.getType();
            alarm.activate(alarmEvent.getCode());
        } else if (event.getType() instanceof SensorEventAlarmDeactivate){
            SensorEventAlarmDeactivate alarmEvent = (SensorEventAlarmDeactivate)event.getType();
            alarm.deactivate(alarmEvent.getCode());
        } else {
            switch (alarm.getState()){
                case DEACTIVATED:{
                    handlerManager.handleEvent(event);
                    break;
                }
                case ACTIVATED:{
                    System.out.println("Alarm");
                    alarm.alarm();
                    break;
                }
            }
        }
    }

    public AlarmState getState(){
        return alarm.getState();
    }

    private final HandlerManager handlerManager;
    private final Alarm alarm;
}
