package com.yet.spring.core.loggers;

import com.yet.spring.core.models.Event;

public interface EventLogger {

    void logEvent(Event event);
}
