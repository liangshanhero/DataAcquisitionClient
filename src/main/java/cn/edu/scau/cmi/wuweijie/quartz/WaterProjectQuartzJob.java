package cn.edu.scau.cmi.wuweijie.quartz;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;

import cn.edu.scau.cmi.wuweijie.Bootstrap;

/**
 * 一个 Quartz Job，定时调度执行批处理任务
 * 
 * @author TESLA_CN
 *
 */
@DisallowConcurrentExecution
public class WaterProjectQuartzJob implements org.quartz.Job {

	private JobLauncher jobLauncher;

	private Job job;

	private String batchRecordFile;

	private Properties parameters;

	/**
	 * 对象由 Quartz 的 JobBuilder 创建，目前不采用注入方式
	 */
	public WaterProjectQuartzJob() {
		ApplicationContext ctx = Bootstrap.applicationContext;
		setJobLauncher(ctx.getBean("jobLauncher", JobLauncher.class));
		setJob(ctx.getBean("db2dbJob", Job.class));
		setBatchRecordFile(ctx.getBean("batchRecordFile", String.class));
		setParameters(ctx.getBean("parameters", Properties.class));
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		Log log = LogFactory.getLog(WaterProjectQuartzJob.class);
		JobExecution execution;
		try {
			try {
				// 尝试加载任务相关参数
				getParameters().load(new FileInputStream(getBatchRecordFile()));
			} catch (FileNotFoundException e) {
				// 任务相关参数配置文件不存在，则初始化参数并创建一个配置文件
				try {
					getParameters().store(new FileOutputStream(getBatchRecordFile()), "");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			// 传入 lastId，即批处理所处理过的最后一个数据Id，批处理任务从 lastId 的下一条记录开始处理（若存在）
			Map<String, JobParameter> params = new HashMap<String, JobParameter>();
			for (String key : getParameters().stringPropertyNames()) {
				params.put(key, new JobParameter(getParameters().getProperty(key, "0")));
			}
			params.put("currentMillis", new JobParameter(System.currentTimeMillis()));
			// 附带参数开始执行批处理
			execution = jobLauncher.run(job, new JobParameters(params));

			log.info(execution.getExitStatus());
		} catch (JobInstanceAlreadyCompleteException e) {
			log.info(e.getLocalizedMessage());
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}

	public JobLauncher getJobLauncher() {
		return jobLauncher;
	}

	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public String getBatchRecordFile() {
		return batchRecordFile;
	}

	public void setBatchRecordFile(String batchRecordFile) {
		this.batchRecordFile = batchRecordFile;
	}

	public Properties getParameters() {
		return parameters;
	}

	public void setParameters(Properties parameters) {
		this.parameters = parameters;
	}

}
