package com.example.kafka.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    String name;
    List<Partition> partitions;
    public Topic(String name, int partitionCount){
        this.name = name;
        this.partitions = new ArrayList<>();
        for(int i=0; i<partitionCount; i++){
            partitions.add(new Partition(i));
        }
    }
}
