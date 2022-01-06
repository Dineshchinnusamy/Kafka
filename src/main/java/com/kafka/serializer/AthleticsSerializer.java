package com.kafka.serializer;

import com.model.Athletics;
import com.sun.xml.internal.bind.v2.runtime.output.StAXExStreamWriterOutput;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.Map;

public class AthleticsSerializer implements Serializer<Athletics> {
    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // nothing to configure
    }

    @Override
    public byte[] serialize(String topic, Athletics athletics) {

        int sizeOfAthleticsName;
        int sizeOfAthleticsEvent;
        int sizeOfAthleticsAge;

        byte[] serializedAthleticsName;
        byte[] serializedAthleticsEvent;
        byte[] serializedAthleticsAge;

        try {
            if (athletics == null)
                return null;
            serializedAthleticsName = athletics.getName().getBytes(encoding);
            sizeOfAthleticsName = serializedAthleticsName.length;

            serializedAthleticsEvent = athletics.getEvent().getBytes(encoding);
            sizeOfAthleticsEvent = serializedAthleticsEvent.length;

            serializedAthleticsAge = athletics.getAge().getBytes(encoding);
            sizeOfAthleticsAge = serializedAthleticsAge.length;

            ByteBuffer buffer = ByteBuffer.allocate(4 +  sizeOfAthleticsName + 4 + sizeOfAthleticsEvent + 4 + sizeOfAthleticsAge);
            buffer.putInt(sizeOfAthleticsName);
            buffer.put(serializedAthleticsName);
            buffer.putInt(sizeOfAthleticsEvent);
            buffer.put(serializedAthleticsEvent);
            buffer.putInt(sizeOfAthleticsAge);
            buffer.put(serializedAthleticsAge);
            return buffer.array();

        } catch (Exception e) {
            throw new SerializationException("Error when serializing Employee to byte[]");
        }
    }

    @Override
    public void close() {
        // nothing to do
    }

}
