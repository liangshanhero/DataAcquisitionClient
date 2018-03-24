package cn.edu.scau.cmi.wuweijie.quartz;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 创建一条线程，启动 Quartz 调度批处理任务
 * 
 * @author TESLA_CN
 *
 */
public class ScheduleLauncher implements Runnable {
	
	private Job job;
	
	private int intervalSeconds;
	
	private String jobName;

	@Override
	public void run() {
		// 创建一个调度任务
		JobDetail jobDetail = JobBuilder.newJob(getJob().getClass()).withIdentity("jobName", getJobName()).build();

		// 配置任务调度相关参数
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("jobName", getJobName())
				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(getIntervalSeconds()).repeatForever())
				.build();

		Scheduler scheduler;
		try {
			// 启动调度器并传入调度任务
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();
			scheduler.scheduleJob(jobDetail, trigger);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public int getIntervalSeconds() {
		return intervalSeconds;
	}

	public void setIntervalSeconds(int intervalSeconds) {
		this.intervalSeconds = intervalSeconds;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
}
