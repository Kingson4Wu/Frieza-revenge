package org.kxw.coroutine;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableRunnable;
import co.paralleluniverse.strands.channels.Channel;
import co.paralleluniverse.strands.channels.Channels;

import java.util.concurrent.ExecutionException;

/**
 * Created by kingsonwu on 16/12/25.
 * <a href = 'https://blog.maxleap.cn/archives/816'>@link</a>
 */
public class Example {

    private static void printer(Channel in) throws SuspendExecution, InterruptedException {
        Integer v;
        while ((v = (Integer) in.receive()) != null) {
            System.out.println(v);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException, SuspendExecution {
        //定义两个Channel
        Channel naturals = Channels.newChannel(-1);
        Channel squares = Channels.newChannel(-1);

        //运行两个Fiber实现.
        new Fiber((SuspendableRunnable) () -> {
            for (int i = 0; i < 10; i++) naturals.send(i);
            naturals.close();
        }).start();

        new Fiber((SuspendableRunnable) () -> {
            Integer v;
            while ((v = (Integer) naturals.receive()) != null)
                squares.send(v * v);
            squares.close();
        }).start();

        printer(squares);
    }
}