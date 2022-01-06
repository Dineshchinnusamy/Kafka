package com.kafka.kafkaproducer;

import com.model.Athletics;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class Producer {
    public static void main(String[] args){

        //creating properties object & Setting the property
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        // we created the own serializer class, so we have to give that class path
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,"com.serializer.AthleticsSerializer");
        //crating kafka producer object
        KafkaProducer producer= new KafkaProducer(properties);
        //Creating a Athletics object passing it into the producer record
        //Storing value in ProducerRecord's object
        Athletics athletics= new Athletics("kavin","100m","22");
        ProducerRecord<String,Athletics> record= new ProducerRecord<>("sb", athletics );

        try {

            producer.send(record);
            producer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.print(record.value().toString());
    }
}
