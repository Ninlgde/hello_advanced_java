package com.ninlgde.advanced.fastjson.asm;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class ClassFileUtils {
    public static byte[] readFile(String filename) throws IOException {
        // read only file channel
        FileChannel fc = new FileInputStream(filename).getChannel();

        // mmap zero copy to buffer
        MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());

        byte[] bytes = new byte[(int) fc.size()];
        // copy to heap bytes
        buffer.get(bytes);

        return bytes;
    }

    public static void writeFile(String filename, byte[] bytes) throws IOException {
        File f = new File(filename);
        if (f.exists())
            f.delete();
        // read & write file channel
        FileChannel fc = new RandomAccessFile(filename, "rw").getChannel();

        // mmap zero copy: make a kernel spaces buffer
        MappedByteBuffer buffer = fc.map(FileChannel.MapMode.READ_WRITE, 0, bytes.length);

        // copy heap bytes to kernel spaces
        buffer.put(bytes);
    }
}
