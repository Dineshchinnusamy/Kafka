package com.deserializer;

import com.model.Athletics;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.ByteBuffer;
import java.util.Map;

public class AthleticsDeserializer implements Deserializer<Athletics> {

    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        //Nothing to configure
    }

    @Override
    public Athletics deserialize(String topic, byte[] data) {

        try {
            if (data == null){
                System.out.println("Null recieved at deserialize");
                return null;
            }
            ByteBuffer buffer = ByteBuffer.wrap(data);

            int sizeOfAthleticsName = buffer.getInt();
            byte[] athleticsNameBytes = new byte[sizeOfAthleticsName];
            buffer.get(athleticsNameBytes);
            String deserializedName = new String(athleticsNameBytes, encoding);


            int sizeOfAthleticsEvent = buffer.getInt();
            byte[] athleticsEventBytes = new byte[sizeOfAthleticsEvent];
            buffer.get(athleticsEventBytes);
            String deserializedEvent = new String(athleticsEventBytes, encoding);

            int sizeOfAthleticsAge = buffer.getInt();
            byte[] athleticsAgeBytes = new byte[sizeOfAthleticsAge];
            buffer.get(athleticsAgeBytes);
            String deserializedAge = new String(athleticsAgeBytes, encoding);

            return new Athletics(deserializedName,deserializedEvent,deserializedAge);

        } catch (Exception e) {
            throw new SerializationException("Error when deserialize byte[] to Athletics");
        }
    }

    @Override
    public void close() {
        // nothing to do
    }
}
