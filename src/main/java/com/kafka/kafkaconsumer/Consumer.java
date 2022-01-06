package com.kafka.kafkaconsumer;

import com.model.Athletics;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;


import java.util.Arrays;
import java.util.Properties;

public class Consumer {
    public static void main(String[] args){
        //creating properties object & Setting the property
        Properties properties= new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringDeserializer");
        //we created the AthleticsDeserializer class that implements the Deserializer interface
        //to deserialize the object value
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,"com.deserializer.AthleticsDeserializer");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG,"1language_");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,"earliest");

        //crating kafka consumer object
        KafkaConsumer<String, Athletics> consumer = new KafkaConsumer<>(properties);

        //subscribing ti the specific topic
        //kafka server sends a list of records so we get that in Array
        consumer.subscribe(Arrays.asList("sb"));

        while (true) {
            ConsumerRecords<String, Athletics> records = consumer.poll(100);
        // we get the each record's object using for loop
            for (ConsumerRecord<String, Athletics> record : records) {
                Athletics value = record.value();
                System.out.println(value.toString());
            }

        }


    }
}
