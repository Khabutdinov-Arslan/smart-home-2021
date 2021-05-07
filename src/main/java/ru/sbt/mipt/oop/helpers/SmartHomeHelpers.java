package ru.sbt.mipt.oop.helpers;

import ru.sbt.mipt.oop.components.Light;
import ru.sbt.mipt.oop.components.SmartHome;
import ru.sbt.mipt.oop.components.Door;

public class SmartHomeHelpers {
    public static boolean isHallDoor(SmartHome smartHome, String id){
        IsHallDoorAction action = new IsHallDoorAction(id);
        smartHome.execute(action);
        return action.getResult();
    }

    public static boolean isHallLight(SmartHome smartHome, String id){
        IsHallLightAction action = new IsHallLightAction(id);
        smartHome.execute(action);
        return action.getResult();
    }

    public static Door getDoorById(SmartHome smartHome, String id){
        FindDoorByIdAction action = new FindDoorByIdAction(id);
        smartHome.execute(action);
        return action.getResult();
    }

    public static Light getLightById(SmartHome smartHome, String id){
        FindLightByIdAction action = new FindLightByIdAction(id);
        smartHome.execute(action);
        return action.getResult();
    }
}
