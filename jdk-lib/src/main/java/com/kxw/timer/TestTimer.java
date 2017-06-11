package com.kxw.timer;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by kingson.wu on 2016/1/4.
 */
public class TestTimer {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.schedule(new AppendCacheTask(), 24 * 60 * 60 * 1000);//一天执行一次
    }

    private static class AppendCacheTask extends TimerTask {
        @Override
        public void run() {
            //
        }
    }
}
