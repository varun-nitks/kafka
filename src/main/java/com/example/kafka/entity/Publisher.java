package com.example.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Random;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Publisher {
    String name;
    private final Random random = new Random();
    public void publish(Topic topic, String content){
        int partitionId = random.nextInt(topic.getPartitions().size());
        Partition partition = topic.getPartitions().get(partitionId);
        partition.addMessage(new Message(content));
        System.out.println("Timestamp: "+ System.currentTimeMillis() +
                " Publisher " + name + " published message to " + topic.getName() +
                " Partition " + partitionId);
    }
}
