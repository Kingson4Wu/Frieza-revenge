package com.kxw.disruptor.basic.java8;

import com.kxw.disruptor.basic.LongEvent;
import com.kxw.disruptor.basic.LongEventProducer;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LongEventMain
{
    public static void main(String[] args) throws Exception
    {
        // Executor that will be used to construct new threads for consumers
        Executor executor = Executors.newCachedThreadPool();

        // Specify the size of the ring buffer, must be power of 2.
        int bufferSize = 1024;

        // Construct the Disruptor
        Disruptor<LongEvent> disruptor = new Disruptor<>(LongEvent::new, bufferSize, executor);

        // Connect the handler
        disruptor.handleEventsWith((event, sequence, endOfBatch) -> System.out.println("Event: " + event));

        // Start the Disruptor, starts all threads running
        disruptor.start();

        // Get the ring buffer from the Disruptor to be used for publishing.
        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);

        ByteBuffer bb = ByteBuffer.allocate(8);
        for (long l = 0; true; l++)
        {
            bb.putLong(0, l);
            ringBuffer.publishEvent((event, sequence, buffer) -> event.set(buffer.getLong(0)), bb);
            Thread.sleep(1000);
        }
    }

    /**
     * Note how a number of the classes (e.g. handler, translator) are no longer required. Also note how the lambda used for publishEvent() only refers to the parameters that are passed in. If we were to instead write that code as:

     ByteBuffer bb = ByteBuffer.allocate(8);
     for (long l = 0; true; l++)
     {
     bb.putLong(0, l);
     ringBuffer.publishEvent((event, sequence) -> event.set(bb.getLong(0)));
     Thread.sleep(1000);
     }
     This would create a capturing lambda, meaning that it would need to instantiate an object to hold the ByteBuffer bb variable as it passes the lambda through to the publishEvent() call. This will create additional (unnecessary) garbage, so the call that passes the argument through to the lambda should be preferred if low GC pressure is a requirement.

     Give that method references can be used instead of anonymous lamdbas it is possible to rewrite the example in this fashion.
     */
}