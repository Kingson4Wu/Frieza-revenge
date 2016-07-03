package demo.crawler.timerManager;

import demo.crawler.search.SearchBKeyword;
import demo.system.LoadConfig;

import java.util.TimerTask;

/**
 * @see 搜索定时器
 * @author Herman.Xiong
 * @date 2014年6月19日 13:54:44
 */
public class SearchTask extends TimerTask{
	public void run() {
		if(!SearchBKeyword.process){
			/**加载关键词和采集页数*/
			LoadConfig.loadDemoConfig();
			/**调用采集方法*/
			new SearchBKeyword().search();
		}
	}
}
