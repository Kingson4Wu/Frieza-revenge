package com.kxw.disruptor.bigfilemd5;

import java.io.InputStream;

import com.lmax.disruptor.RingBuffer;

public class FileMd5EventProducer {

    //private final static Map<String, >

    private final RingBuffer<FileMd5Event> ringBuffer;

    public FileMd5EventProducer(RingBuffer<FileMd5Event> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(int size, String groupId) {
        long sequence = ringBuffer.next();  // Grab the next sequence
        try {
            FileMd5Event event = ringBuffer.get(sequence); // Get the entry in the Disruptor

            InputStream inputStream = GenerareBigFileMd5.class.getClassLoader().getResourceAsStream("bigfile.txt");

            event.setGroupId(groupId);


            // for the sequence
            //event.set(bb.getLong(0));  // Fill with data
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}