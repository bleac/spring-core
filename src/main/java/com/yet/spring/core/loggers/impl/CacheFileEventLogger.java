package com.yet.spring.core.loggers.impl;

import com.yet.spring.core.models.Event;

import java.util.ArrayList;
import java.util.List;

public class CacheFileEventLogger extends FileEventLogger {

    private int cacheSize;
    private List<Event> cache;

    private void destroy() {
        if (!cache.isEmpty()) {
            System.out.println("Inside destroy CacheFileEventLogger cache not empty");
            cache.forEach(super::logEvent);
            cache.clear();
        } else {
            System.out.println("Inside destroy CacheFileEventLogger cache empty");
        }
    }

    public CacheFileEventLogger(String fileName, int cacheSize) {
        super(fileName);
        this.cache = new ArrayList<>();
        this.cacheSize = cacheSize;
    }

    public void logEvent(Event event) {
        cache.add(event);
        if (cache.size() == cacheSize) {
            cache.forEach(super::logEvent);
            cache.clear();
        }
    }
}
