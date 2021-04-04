package ru.sbt.mipt.oop.events;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.events.types.*;

import java.util.HashMap;

public class CCSensorEventAdaptor implements EventHandler {

    private final ru.sbt.mipt.oop.events.EventHandler handler;
    private final HashMap <String, SensorEventType> convertor;

    public CCSensorEventAdaptor(ru.sbt.mipt.oop.events.EventHandler handler){
        this.handler = handler;
        convertor = new HashMap<>();
        convertor.put("LightIsOn", new SensorEventLightOn());
        convertor.put("LightIsOff", new SensorEventLightOff());
        convertor.put("DoorIsOpen", new SensorEventDoorOpen());
        convertor.put("DoorIsClosed", new SensorEventDoorClose());
        convertor.put("DoorIsLocked", new SensorEventAlarmActivate(1234));
        convertor.put("DoorIsUnlocked", new SensorEventAlarmDeactivate(1234));
    }

    private SensorEvent convert(CCSensorEvent event){
        if (convertor.containsKey(event.getEventType())){
            return new SensorEvent(convertor.get(event.getEventType()), event.getObjectId());
        }else{
            return null;
        }
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        handler.handleEvent(convert(event));
    }
}
