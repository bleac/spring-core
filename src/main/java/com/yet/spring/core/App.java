package com.yet.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

public class App {

    private static Random rand;
    private Client client;
    private EventLogger eventLogger;

    public App(Client client, EventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        rand = SecureRandom.getInstanceStrong();
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");
        Event event = (Event) ctx.getBean("event");

        app.logEvent("Some event for user 1", event);
        app.logEvent("Some event for user 2", event);
    }

    private void logEvent(String msg,  Event event) {
        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setId(rand.nextInt());
        event.setMsg(message);
        eventLogger.logEvent(event);
    }
}
