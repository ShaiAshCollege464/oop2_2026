package com.ashcollege.entities;

import com.ashcollege.controllers.RealTimeController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Date;

public class Subscriber {
    private SseEmitter channel;
    private String name;

    public Subscriber (String name) {
        this.name = name;
        this.channel = new SseEmitter(1000 * 60 * 1L);
        this.channel.onError((event) -> {
        });
        this.channel.onTimeout(() -> {
            RealTimeController.removeSubscriber(this);
            System.out.println("The subscriber " + this.name + " was timeouted ");
        });
        this.channel.onCompletion(() -> {
            RealTimeController.removeSubscriber(this);
            System.out.println("The subscriber " + this.name + " was removed ");
        });
    }

    public SseEmitter getChannel() {
        return channel;
    }

    public void setChannel(SseEmitter channel) {
        this.channel = channel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sendMessage (String message) {
        try {
            this.channel.send(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
