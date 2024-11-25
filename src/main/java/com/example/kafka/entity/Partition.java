package com.example.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Partition {
    private final int id;
    private final List<Message> messages;
    private final Map<Subscriber, AtomicInteger> subscriberOffset;
    public Partition(int id){
        this.id = id;
        this.messages = new CopyOnWriteArrayList<>();
        this.subscriberOffset = new ConcurrentHashMap<>();
    }

    public void addMessage(Message message){
        messages.add(message);
    }

    public void registerSubscriber(Subscriber subscriber){
        subscriberOffset.putIfAbsent(subscriber, new AtomicInteger(0));
    }

    public Message getNextMessageForSubscriber(Subscriber subscriber){
        AtomicInteger offset = subscriberOffset.get(subscriber);
        int currentOffset = offset.get();
        if(currentOffset < messages.size()){
            offset.incrementAndGet();
            return messages.get(currentOffset);
        }
        return null;
    }
}
