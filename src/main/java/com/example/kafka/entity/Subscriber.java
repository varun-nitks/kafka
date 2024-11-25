package com.example.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subscriber {
    String name;
    public void subscribe(Topic topic){
        for(Partition partition : topic.getPartitions()){
            partition.registerSubscriber(this);
            new Thread(() -> consumeMessage(partition)).start();
        }
    }

    private void consumeMessage(Partition partition){
        while(true){
            Message message = partition.getNextMessageForSubscriber(this);
            if(message!=null){
                processMessage(message);
            }
        }
    }
    private void processMessage(Message message) {
        System.out.println("Timestamp:" + System.currentTimeMillis() +
                " Subscriber " + name + " consumed message: " + message.getMessage());
    }
}
