package cn.edu.scau.cmi.wuweijie.utils;

import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import cn.edu.scau.cmi.wuweijie.entity.client.Celve;
import cn.edu.scau.cmi.wuweijie.entity.server.Whstrategy;
import cn.edu.scau.cmi.wuweijie.entity.server.Whstrategydetail;
import cn.edu.scau.cmi.wuweijie.entity.server.WhstrategytypeDAO;

public class WhstrategyConverter {

	private WhstrategytypeDAO whstrategytypeDAO;

	private ResourceBundle bundle;

	public Set<Whstrategydetail> toDetail(Celve c, Whstrategy belongs) {
		Set<Whstrategydetail> details = new HashSet<>();
		Whstrategydetail detail = null;

		if (notNull(c.getStartHh()) && notNull(c.getStartMm()) && notNull(c.getShutHh()) && notNull(c.getShutMm())) {
			detail = new Whstrategydetail();
			detail.setMin(c.getStartHh().doubleValue() * 60 + c.getStartMm().doubleValue());
			detail.setMax(c.getShutHh().doubleValue() * 60 + c.getShutMm().doubleValue());
			detail.setTime(c.getTime2());
			detail.setWhstrategytype(getWhstrategytypeDAO().getOrCreateByName("时间"));
			detail.setWhstrategy(belongs);
			details.add(detail);
		}

		if (notNull(c.getOperatingtemp()) && notNull(c.getShutTemp())) {
			detail = new Whstrategydetail();
			detail.setMin(c.getOperatingtemp());
			detail.setMax(c.getShutTemp());
			detail.setTime(c.getTime2());
			detail.setWhstrategytype(getWhstrategytypeDAO().getOrCreateByName("温度"));
			detail.setWhstrategy(belongs);
			details.add(detail);
		}

		if (notNull(c.getMinlevel()) && notNull(c.getMaxlevel())) {
			detail = new Whstrategydetail();
			detail.setMin(c.getMinlevel());
			detail.setMax(c.getMaxlevel());
			detail.setTime(c.getTime2());
			detail.setWhstrategytype(getWhstrategytypeDAO().getOrCreateByName("水位"));
			detail.setWhstrategy(belongs);
			details.add(detail);
		}

		return details;
	}

	public final static boolean notNull(Object o) {
		return o != null;
	}

	public WhstrategytypeDAO getWhstrategytypeDAO() {
		return whstrategytypeDAO;
	}

	public void setWhstrategytypeDAO(WhstrategytypeDAO whstrategytypeDAO) {
		this.whstrategytypeDAO = whstrategytypeDAO;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

}
