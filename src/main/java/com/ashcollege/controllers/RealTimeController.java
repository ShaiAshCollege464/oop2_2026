package com.ashcollege.controllers;

import com.ashcollege.entities.Subscriber;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class RealTimeController {
    private static List<Subscriber> subscribers = new ArrayList<>();

    @PostConstruct
    public void init () {
        broadcast();
    }

    public static void removeSubscriber (Subscriber toRemove) {
        subscribers =
                subscribers
                        .stream()
                        .filter(item -> !toRemove.getName().equals(item.getName()))
                        .toList();
    }

    private void broadcast () {
        new Thread(() -> {
            while (true) {
                for (Subscriber subscriber : this.subscribers) {
                    try {
                        subscriber.sendMessage("Hello " + subscriber.getName() + " " + new Date());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                System.out.println("Broadcasted message to " + this.subscribers.size() + " subscribers");
                try {
                    Thread.sleep(1000 * 5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }

    @RequestMapping ("/subscribe")
    public SseEmitter subscribe (String name) {
        if (name != null) {
            Subscriber subscriber = new Subscriber(name);
            this.subscribers.add(subscriber);
            return subscriber.getChannel();
        } else {
            return null;
        }
    }

    @RequestMapping ("/send-message")
    public boolean sendMessage (String to, String message) {
        boolean success = false;
        Subscriber subscriber = getSubscriberByName(to);
        if (subscriber != null) {
            subscriber.sendMessage(message);
            success = true;
        }
        return success;
    }




    private Subscriber getSubscriberByName (String name) {
        Subscriber found = null;
        for (Subscriber subscriber : this.subscribers) {
            if (subscriber.getName().equals(name)) {
                found = subscriber;
                break;
            }
        }
        return found;

    }
}
