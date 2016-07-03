package demo.main;

import demo.crawler.timerManager.TimerManager;
import org.apache.log4j.Logger;

/**
 * @see 测试程序
 * @author Herman.Xiong
 * @date 2014年6月19日 13:55:33
 */
public class AppletClient {
	
	public static Logger log4j = Logger.getLogger("");
	
	/**主程序入口*/
	public static void main(String[] args) {
		System.out.println();
		log4j.info("10秒后启动采集");
		//LoadConfig.init();
		new TimerManager().run();
	}
}
