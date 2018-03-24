package cn.edu.scau.cmi.wuweijie.quartz;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cn.edu.scau.cmi.wuweijie.Bootstrap;
import cn.edu.scau.cmi.wuweijie.entity.server.Ledmeter;
import cn.edu.scau.cmi.wuweijie.entity.server.LedmeterDAO;
import cn.edu.scau.cmi.wuweijie.entity.server.Ledmeterdata;
import cn.edu.scau.cmi.wuweijie.entity.server.LedmeterdataDAO;
import cn.edu.scau.cmi.wuweijie.utils.led.LedDataFactory;

@DisallowConcurrentExecution
public class LedProjectQuartzJob implements Job {
	
	private static Log log = LogFactory.getLog(LedProjectQuartzJob.class);

	private LedmeterdataDAO ledmeterdataDAO;

	private LedmeterDAO ledmeterDAO;

	private LedDataFactory ledDataFactory;

	public LedProjectQuartzJob() {
		setLedmeterdataDAO((LedmeterdataDAO) Bootstrap.applicationContext.getBean("LedmeterdataDAO"));
		setLedmeterDAO((LedmeterDAO) Bootstrap.applicationContext.getBean("LedmeterDAO"));
		setLedDataFactory((LedDataFactory) Bootstrap.applicationContext.getBean("ledDataFactory"));
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		log.info("Start generating LED project value.");
		List<Ledmeter> ledmeters = getLedmeterDAO().findAll();
		for (Ledmeter ledmeter : ledmeters) {
			Ledmeterdata data = getLedDataFactory().generateData(ledmeter);
			getLedmeterdataDAO().save(data);
		}
	}

	public LedmeterdataDAO getLedmeterdataDAO() {
		return ledmeterdataDAO;
	}

	public void setLedmeterdataDAO(LedmeterdataDAO ledmeterdataDAO) {
		this.ledmeterdataDAO = ledmeterdataDAO;
	}

	public LedDataFactory getLedDataFactory() {
		return ledDataFactory;
	}

	public void setLedDataFactory(LedDataFactory ledDataFactory) {
		this.ledDataFactory = ledDataFactory;
	}

	public LedmeterDAO getLedmeterDAO() {
		return ledmeterDAO;
	}

	public void setLedmeterDAO(LedmeterDAO ledmeterDAO) {
		this.ledmeterDAO = ledmeterDAO;
	}

}
