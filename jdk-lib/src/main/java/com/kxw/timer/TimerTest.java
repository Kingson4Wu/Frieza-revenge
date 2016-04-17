package com.kxw.timer;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * <a href='http://nmj1987.iteye.com/blog/343748'>@link</a>
 */
//Timer应该是个计时器，而TimerTask是需要计时的任务。
public class TimerTest extends TimerTask
{
    private Timer timer;

    public static void main(String[] args)
    {
        TimerTest timerTest= new TimerTest();
        timerTest.timer = new Timer();

        //立刻开始执行timerTest任务，只执行一次
        timerTest.timer.schedule(timerTest,new Date());

        //立刻开始执行timerTest任务，执行完本次任务后，隔2秒再执行一次
        //timerTest.timer.schedule(timerTest,new Date(),2000);

        //一秒钟后开始执行timerTest任务，只执行一次
        //timerTest.timer.schedule(timerTest,1000);

        //一秒钟后开始执行timerTest任务，执行完本次任务后，隔2秒再执行一次
        //timerTest.timer.schedule(timerTest,1000,2000);

        //立刻开始执行timerTest任务，每隔2秒执行一次
        //timerTest.timer.scheduleAtFixedRate(timerTest,new Date(),2000);

        //一秒钟后开始执行timerTest任务，每隔2秒执行一次
        //timerTest.timer.scheduleAtFixedRate(timerTest,1000,2000);

        //结束任务执行，程序终止
        timerTest.timer.cancel();
        //结束任务执行，程序并不终止,为什么呢，还没想明白
        //timerTest.cancel();
    }

    @Override
    public void run()
    {
        System.out.println("Task is running!");
    }
}
/**
 *  需要注意的是scheduleAtFixedRate和schedule在参数完全相同的情况下，执行效果是不同的。
 *  上面例子中任务只是打印一个字符串，比较简单。但如果任务比较复杂，或者由于任何原因
 * （如垃圾回收或其他后台活动）而延迟了某次执行，则scheduleAtFixedRate方法将快速连续地出现两次或更多的执行，
 * 从而使后续执行能够“追赶上来”；而schedule方法的后续执行也将被延迟。所以，在长期运行中，scheduleAtFixedRate执行的频率将正好是指定周期的倒数，schedule 执行的频率一般要稍慢于指定周期的倒数。
 * 另外，TimerTask的构造函数是protected，所以无法再类中直接new一个TimerTask，
 * 而只能写一个类继承TimerTask。
 */