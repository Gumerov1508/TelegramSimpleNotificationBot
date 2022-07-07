package org.example.NotificationBot.service;

import java.util.TimerTask;

public class SimpleTask extends TimerTask {
    private final SendEvent sendEvent;

    public SimpleTask(SendEvent sendEvent) {
        this.sendEvent = sendEvent;
    }

    @Override
    public void run() {
        sendEvent.run();
    }
}