package com.yet.spring.core;

import com.yet.spring.core.loggers.EventLogger;
import com.yet.spring.core.models.Client;
import com.yet.spring.core.models.EnumType;
import com.yet.spring.core.models.Event;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Map;
import java.util.Random;

public class App {

    private static Random rand;
    private Client client;
    private EventLogger eventLogger;
    private Map<EnumType, EventLogger> loggerMap;

    public App(Client client, EventLogger eventLogger, Map<EnumType, EventLogger> loggerMap) {
        this.client = client;
        this.eventLogger = eventLogger;
        this.loggerMap = loggerMap;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        rand = SecureRandom.getInstanceStrong();
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

//        App appConsole = (App) ctx.getBean("appConsole");
//        App appFile = (App) ctx.getBean("appFile");
        App appCache = (App) ctx.getBean("app");

        Event event = (Event) ctx.getBean("event");

        appCache.logEvent(EnumType.ERROR, "Some event for user 1", event);
        appCache.logEvent(EnumType.INFO,"Some event for user 1", event);
        appCache.logEvent(EnumType.INFO,"Some event for user 1", event);
        appCache.logEvent(null, "Some event for user 1", event);
//        appConsole.logEvent("Some event for user 2", event);
        ctx.close();
    }

    private void logEvent(EnumType enumType, String msg,  Event event) {
        EventLogger logger = loggerMap.get(enumType);
        if (logger == null) {
            logger = eventLogger;
        }
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setId(rand.nextInt());
        event.setMsg(message);
        logger.logEvent(event);
    }
}
