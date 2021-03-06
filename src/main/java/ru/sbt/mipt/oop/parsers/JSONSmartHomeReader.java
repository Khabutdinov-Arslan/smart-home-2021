package ru.sbt.mipt.oop.parsers;

import com.google.gson.Gson;
import ru.sbt.mipt.oop.components.SmartHome;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONSmartHomeReader implements SmartHomeReader {
    public JSONSmartHomeReader(String filename){
        this.filename = filename;
    }

    private final String filename;

    @Override
    public SmartHome readHome(){
        SmartHome smartHome;
        try {
            Gson gson = new Gson();
            String json = new String(Files.readAllBytes(Paths.get(filename)));
            smartHome = gson.fromJson(json, SmartHome.class);
        } catch (IOException e){
            smartHome = new SmartHome();
        }
        return smartHome;
    }

}
