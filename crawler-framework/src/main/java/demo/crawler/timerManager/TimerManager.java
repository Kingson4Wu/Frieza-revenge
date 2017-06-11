package demo.crawler.timerManager;

import java.util.Timer;

/**
 * @see 定时任务
 * @date 2014年6月19日 13:55:04
 * @author Herman.Xiong
 */
public class TimerManager {
	
	public void run() {
		/**10秒后执行，2分钟执行一次*/
		Timer timer = new Timer();
		timer.schedule(new SearchTask(),10* 1000L, 120 * 1000L);
	}
	
}