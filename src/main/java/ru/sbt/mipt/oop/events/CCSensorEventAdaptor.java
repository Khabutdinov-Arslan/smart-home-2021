package ru.sbt.mipt.oop.events;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.events.types.*;

import java.util.HashMap;

public class CCSensorEventAdaptor implements EventHandler {

    private final ru.sbt.mipt.oop.events.EventHandler handler;
    private final HashMap <String, SensorEventType> convertor;

    public CCSensorEventAdaptor(ru.sbt.mipt.oop.events.EventHandler handler, HashMap<String, SensorEventType> convertor){
        this.handler = handler;
        this.convertor = convertor;
    }

    public void addConversion(String eventName, SensorEventType eventType){
        convertor.put(eventName, eventType);
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
