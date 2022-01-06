package com.connector.producer.serializer;

import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.serialization.Serializer;

import java.io.*;
import java.util.Map;

public class FileSerializer implements Serializer {

    @Override
    public void configure(Map configs, boolean isKey) {
       // Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String topic, Object data) {

        try {
        //    FileInputStream fout = new FileInputStream("C:\\Connect\\Source.txt");

        }catch(Exception e){

        }




        return new byte[0];
    }

    @Override
    public byte[] serialize(String topic, Headers headers, Object data) {
        return Serializer.super.serialize(topic, headers, data);
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
