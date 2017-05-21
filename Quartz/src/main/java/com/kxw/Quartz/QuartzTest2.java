package com.kxw.Quartz;

import java.util.Date;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

public class QuartzTest2 {

	private static Logger log = LoggerFactory.getLogger(QuartzTest.class);

	public void run() throws Exception {
		// 通过SchedulerFactory获取一个调度器实例
		SchedulerFactory sf = new StdSchedulerFactory();

		Scheduler sched = sf.getScheduler();
		// 接着设置调度的时间规则.当前时间运行
		Date runTime = evenMinuteDate(new Date());
		Date endTime = evenMinuteDate(new Date(
				System.currentTimeMillis() + 25000L));// 25秒后结束

		// 通过过JobDetail封装HelloJob，同时指定Job在Scheduler中所属组及名称，这里，组名为group1，而名称为job1。
		JobDetail job = newJob(HelloJob.class).withIdentity("job1", "group1")
				.build();
		job.getJobDataMap().put("name", "Kingson_Wu");
		// 创建一个SimpleTrigger实例，指定该Trigger在Scheduler中所属组及名称。

		// Trigger trigger = newTrigger().withIdentity("trigger1",
		// "group1").startAt(runTime).build();
		Trigger trigger = newTrigger().withIdentity("trigger1", "group1")
				.startAt(runTime).endAt(endTime).build();
		// 注册并进行调度
		sched.scheduleJob(job, trigger);

		// 启动调度器
		sched.start();

		try {
			// 当前线程等待20秒
			log.info("Current Thread wait 20 seconds.....");
			Thread.sleep(20L * 1000L);
		} catch (Exception e) {

		}

		// 调度器停止运行
		sched.shutdown(true);
		log.info("-->>end running.......");
	}

	public static void main(String[] args) throws Exception {
		QuartzTest2 example = new QuartzTest2();
		example.run();
	}
}