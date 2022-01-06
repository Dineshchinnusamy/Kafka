package com.connector.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.*;
import java.util.Properties;

public class ProducerConnect {

    public static void main(String[] args) {

        //creating properties object & Setting the property
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        //crating kafka producer object
        KafkaProducer producer = new KafkaProducer(properties);
        try {
            //Reading a file with use of File reader & BufferedReader
            BufferedReader buffer = new BufferedReader(new FileReader("C:\\Connect\\Source.txt"));
            String line = null;
            while (true) {
                line = buffer.readLine();
                if (line == null) {
                    //do nothing
                } else {
                    ProducerRecord<String, String> record = new ProducerRecord<>("connect1", line);
                    producer.send(record);
                    System.out.println(record.value());
                    producer.flush();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

