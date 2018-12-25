package com.yet.spring.core.loggers.impl;

import com.yet.spring.core.models.Event;
import com.yet.spring.core.loggers.EventLogger;

public class ConsoleEventLogger implements EventLogger {

    @Override
    public void logEvent(Event event) {
        System.out.println("Event was logged with message: " + event.toString());
    }
}
