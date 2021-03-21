package ru.sbt.mipt.oop.events;

import ru.sbt.mipt.oop.components.Actionable;

public interface Action {
    void act(Actionable component);
}
