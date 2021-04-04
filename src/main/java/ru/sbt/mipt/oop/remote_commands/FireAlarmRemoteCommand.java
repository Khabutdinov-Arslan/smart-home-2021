package ru.sbt.mipt.oop.remote_commands;

import ru.sbt.mipt.oop.events.SensorEvent;
import ru.sbt.mipt.oop.alarm.Alarm;
import ru.sbt.mipt.oop.events.types.SensorEventAlarmFiring;

public class FireAlarmRemoteCommand extends AlarmRemoteCommand {

    public FireAlarmRemoteCommand(Alarm alarm) {
        super(alarm);
    }

    @Override
    public void execute(){
        alarm.handleEvent(new SensorEvent(new SensorEventAlarmFiring(1234), "0"));
    }
}
