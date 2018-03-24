package cn.edu.scau.cmi.wuweijie.quartz;

import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cn.edu.scau.cmi.wuweijie.Bootstrap;
import cn.edu.scau.cmi.wuweijie.entity.client.Celve;
import cn.edu.scau.cmi.wuweijie.entity.client.CelveDAO;
import cn.edu.scau.cmi.wuweijie.entity.server.Whdevice;
import cn.edu.scau.cmi.wuweijie.entity.server.WhdeviceDAO;
import cn.edu.scau.cmi.wuweijie.entity.server.Whstrategy;
import cn.edu.scau.cmi.wuweijie.entity.server.WhstrategyDAO;
import cn.edu.scau.cmi.wuweijie.entity.server.Whstrategydetail;
import cn.edu.scau.cmi.wuweijie.entity.server.WhstrategydetailDAO;
import cn.edu.scau.cmi.wuweijie.entity.server.WhstrategytypeDAO;
import cn.edu.scau.cmi.wuweijie.utils.WhstrategyConverter;

public class StrategyQuartzJob implements Job {

	private CelveDAO celveDAO;

	private WhstrategyDAO whstrategyDAO;

	private WhstrategytypeDAO whstrategytypeDAO;

	private WhstrategydetailDAO whstrategydetailDAO;

	private WhdeviceDAO whdeviceDAO;

	private ResourceBundle bundle;

	private WhstrategyConverter whstrategyConverter;

	public StrategyQuartzJob() {
		setCelveDAO(Bootstrap.applicationContext.getBean("CelveDAO", CelveDAO.class));
		setWhdeviceDAO(Bootstrap.applicationContext.getBean("WhdeviceDAO", WhdeviceDAO.class));
		setWhstrategyDAO(Bootstrap.applicationContext.getBean("WhstrategyDAO", WhstrategyDAO.class));
		setWhstrategydetailDAO(Bootstrap.applicationContext.getBean("WhstrategydetailDAO", WhstrategydetailDAO.class));
		setWhstrategytypeDAO(Bootstrap.applicationContext.getBean("WhstrategytypeDAO", WhstrategytypeDAO.class));
		setBundle(Bootstrap.applicationContext.getBean("resourceBundle", ResourceBundle.class));
		setWhstrategyConverter(Bootstrap.applicationContext.getBean("whstrategyConverter", WhstrategyConverter.class));
	}

	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		List<Celve> all = getCelveDAO().findAll();
		String regex = "([A-Za-z_]+)(\\d+)?";
		Pattern pattern = Pattern.compile(regex);
		for (Celve c : all) {
			String site = c.getSite();
			String strategyName = c.getName();
			List<Whdevice> devices = getWhdeviceDAO().findByNumber(site);
			Whdevice device = null;
			if (devices.size() > 0) {
				// device exist
				device = devices.get(0);
				Whstrategy example = new Whstrategy();
				example.setWhdevice(device);
				List<Whstrategy> strategies = getWhstrategyDAO().findByExample(example);
				Whstrategy strategy = null;
				Matcher m = pattern.matcher(strategyName);
				String prefix = "";
				String suffix = "";
				if (m.find()) {
					prefix = m.group(1);
					suffix = m.group(2) == null ? "" : m.group(2);
				}
				prefix = getBundle().getString("strategy." + prefix);
				strategyName = prefix + suffix;
				for (Whstrategy s : strategies) {
					if (s.getName().equals(strategyName)) {
						strategy = s;
					}
				}
				// strategy not exists
				if (strategy == null) {
					strategy = new Whstrategy();
					strategy.setName(strategyName);
					strategy.setCreateDate(c.getTime2());
					strategy.setEnable(c.getClsx() == 1);
					strategy.setWhstrategydetails(getWhstrategyConverter().toDetail(c));
					getWhstrategyDAO().save(strategy);
				} else {
					// strategy exists
					Set<Whstrategydetail> serverDetails = strategy.getWhstrategydetails();
					Set<Whstrategydetail> clientDetails = getWhstrategyConverter().toDetail(c);

					if (serverDetails.equals(clientDetails)) {
						continue;
					}

					for (Whstrategydetail clientDetail : clientDetails) {
						for (Whstrategydetail serverDetail : serverDetails) {
							if (clientDetail.getWhstrategytype().equals(serverDetail.getWhstrategytype())
									&& !clientDetail.equals(serverDetail)) {
								if (clientDetail.getTime().after(serverDetail.getTime())) {
									serverDetail.setMin(clientDetail.getMin());
									serverDetail.setMax(clientDetail.getMax());
									getWhstrategydetailDAO().merge(serverDetail);
								} else if (clientDetail.getTime().before(serverDetail.getTime())) {
									clientDetail.setMin(serverDetail.getMin());
									clientDetail.setMax(serverDetail.getMax());
									switch (clientDetail.getWhstrategytype().getName()) {
									case "时间":
										c.setStartHh((byte) (serverDetail.getMin() / 60));
										c.setStartMm((byte) (serverDetail.getMin() % 60));
										c.setShutHh((byte) (serverDetail.getMax() / 60));
										c.setShutMm((byte) (serverDetail.getMax() % 60));
										break;
									case "水位":
										c.setMinlevel(serverDetail.getMin());
										c.setMaxlevel(serverDetail.getMax());
										break;
									case "温度":
										c.setOperatingtemp(serverDetail.getMin());
										c.setShutTemp(serverDetail.getMax());
										break;
									}
									getCelveDAO().merge(c);
								}
							}
						}
					}

				}
			} else {
				// device not exists
				// skip
			}
		}
	}

	public CelveDAO getCelveDAO() {
		return celveDAO;
	}

	public void setCelveDAO(CelveDAO celveDAO) {
		this.celveDAO = celveDAO;
	}

	public WhstrategyDAO getWhstrategyDAO() {
		return whstrategyDAO;
	}

	public void setWhstrategyDAO(WhstrategyDAO whstrategyDAO) {
		this.whstrategyDAO = whstrategyDAO;
	}

	public WhstrategytypeDAO getWhstrategytypeDAO() {
		return whstrategytypeDAO;
	}

	public void setWhstrategytypeDAO(WhstrategytypeDAO whstrategytypeDAO) {
		this.whstrategytypeDAO = whstrategytypeDAO;
	}

	public WhstrategydetailDAO getWhstrategydetailDAO() {
		return whstrategydetailDAO;
	}

	public void setWhstrategydetailDAO(WhstrategydetailDAO whstrategydetailDAO) {
		this.whstrategydetailDAO = whstrategydetailDAO;
	}

	public WhdeviceDAO getWhdeviceDAO() {
		return whdeviceDAO;
	}

	public void setWhdeviceDAO(WhdeviceDAO whdeviceDAO) {
		this.whdeviceDAO = whdeviceDAO;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public WhstrategyConverter getWhstrategyConverter() {
		return whstrategyConverter;
	}

	public void setWhstrategyConverter(WhstrategyConverter whstrategyConverter) {
		this.whstrategyConverter = whstrategyConverter;
	}

}
