package com.ninlgde.kafka.serialization;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class CompanySerializer implements Serializer<Company> {
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
    }

    @Override
    public byte[] serialize(String topic, Company data) {
        if (data == null) {
            return null;
        }
//        byte[] name, address;
//        if (data.getName() != null) {
//            name = data.getName().getBytes(StandardCharsets.UTF_8);
//        } else {
//            name = new byte[0];
//        }
//        if (data.getAddress() != null) {
//            address = data.getAddress().getBytes(StandardCharsets.UTF_8);
//        } else {
//            address = new byte[0];
//        }
//        ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + name.length + address.length);
//        buffer.putInt(name.length);
//        buffer.put(name);
//        buffer.putInt(address.length);
//        buffer.put(address);
//        return buffer.array();
        Schema schema = (Schema) RuntimeSchema.getSchema(data.getClass());
        LinkedBuffer buffer =
                LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        byte[] protostuff = null;
        try {
            protostuff = ProtostuffIOUtil.toByteArray(data, schema, buffer);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
        return protostuff;
    }

    @Override
    public void close() {
    }
}
