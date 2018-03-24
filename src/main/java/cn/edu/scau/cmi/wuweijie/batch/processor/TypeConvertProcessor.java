package cn.edu.scau.cmi.wuweijie.batch.processor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.batch.item.ItemProcessor;

import cn.edu.scau.cmi.wuweijie.entity.client.Syncdata;
import cn.edu.scau.cmi.wuweijie.entity.server.Whbuilding;
import cn.edu.scau.cmi.wuweijie.entity.server.WhbuildingDAO;
import cn.edu.scau.cmi.wuweijie.entity.server.Whdevice;
import cn.edu.scau.cmi.wuweijie.entity.server.WhdeviceDAO;
import cn.edu.scau.cmi.wuweijie.entity.server.Whdevicedata;
import cn.edu.scau.cmi.wuweijie.entity.server.WhdevicedataDAO;

public class TypeConvertProcessor implements ItemProcessor<Syncdata, Whbuilding> {

	private WhbuildingDAO whbuildingDao;

	private WhdeviceDAO whdeviceDao;

	private WhdevicedataDAO WhdevicedataDAO;

	@Override
	public Whbuilding process(Syncdata item) throws Exception {
		Whbuilding whbuilding = null;
		List<Whbuilding> whbuildings = getWhbuildingDao().findByName(item.getBuildName());// BuildName
		if (whbuildings.size() == 1) {
			whbuilding = whbuildings.get(0);
		} else {
			whbuilding = new Whbuilding(item.getBuildName());
		}

		Whdevice whdevice = null;
		List<Whdevice> whdevices = getWhdeviceDao().findByNumber(item.getDeviceId());// DeviceID
		if (whdevices.size() == 1) {
			whdevice = whdevices.get(0);
		} else {
			whdevice = new Whdevice();
			whdevice.setNumber(item.getDeviceId());
			whdevice.setWhbuilding(whbuilding);
		}

		Set<Whdevicedata> whdevicedatas = whdevice.getWhdevicedatas();
		if (whdevicedatas == null) {
			whdevicedatas = new HashSet<Whdevicedata>();
		}
		Whdevicedata whdevicedata = new Whdevicedata();
		whdevicedata.setIsio(item.getIsIo() == null ? null : item.getIsIo().shortValue()); // isIO
		whdevicedata.setTime(item.getIdate()); // iDate
		whdevicedata.setValue(Double.parseDouble(item.getIvalue()));// iValue

		return whbuilding;
	}

	public WhbuildingDAO getWhbuildingDao() {
		return whbuildingDao;
	}

	public void setWhbuildingDao(WhbuildingDAO whbuildingDao) {
		this.whbuildingDao = whbuildingDao;
	}

	public WhdeviceDAO getWhdeviceDao() {
		return whdeviceDao;
	}

	public void setWhdeviceDao(WhdeviceDAO whdeviceDao) {
		this.whdeviceDao = whdeviceDao;
	}

	public WhdevicedataDAO getWhdevicedataDAO() {
		return WhdevicedataDAO;
	}

	public void setWhdevicedataDAO(WhdevicedataDAO whdevicedataDAO) {
		WhdevicedataDAO = whdevicedataDAO;
	}

}
