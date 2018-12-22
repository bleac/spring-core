package com.yet.spring.core;

public class ConsoleEventLogger implements EventLogger {

    @Override
    public void logEvent(Event event) {
        System.out.println("Event was logged with message: " + event.toString());
    }
}
