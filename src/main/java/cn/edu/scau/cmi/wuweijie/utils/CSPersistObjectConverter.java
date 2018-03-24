package cn.edu.scau.cmi.wuweijie.utils;

import java.util.List;

import cn.edu.scau.cmi.wuweijie.entity.client.Syncdata;
import cn.edu.scau.cmi.wuweijie.entity.server.Project;
import cn.edu.scau.cmi.wuweijie.entity.server.Whbuilding;
import cn.edu.scau.cmi.wuweijie.entity.server.WhbuildingDAO;
import cn.edu.scau.cmi.wuweijie.entity.server.Whdatatype;
import cn.edu.scau.cmi.wuweijie.entity.server.WhdatatypeDAO;
import cn.edu.scau.cmi.wuweijie.entity.server.Whdevice;
import cn.edu.scau.cmi.wuweijie.entity.server.WhdeviceDAO;
import cn.edu.scau.cmi.wuweijie.entity.server.Whdevicedata;
import cn.edu.scau.cmi.wuweijie.entity.server.WhdevicedataDAO;

public class CSPersistObjectConverter {

	private WhbuildingDAO whbuildingDAO;

	private WhdeviceDAO whdeviceDAO;

	private WhdatatypeDAO whdatatypeDAO;

	private WhdevicedataDAO whdevicedataDAO;

	private Project project;

	private Whbuilding whbuilding;

	private Whdatatype whdatatype;

	private Whdevice whdevice;

	private Whdevicedata whdevicedata;

	public Whbuilding toWhbuilding(Syncdata item) {
		Whbuilding whbuilding = new Whbuilding(item.getBuildName());
		whbuilding.setProject(getProject());
		List<Whbuilding> whbuildings = getWhbuildingDAO().findByExample(whbuilding);
		if (whbuildings.size() == 1) {
			whbuilding = whbuildings.get(0);
		} else {
			getWhbuildingDAO().save(whbuilding);
		}
		setWhbuilding(whbuilding);
		return whbuilding;
	}

	public Whdatatype toWhdatatype(Syncdata item) {
		Whdatatype whdatatype = null;
		List<Whdatatype> whdatatypes = getWhdatatypeDAO().findByName(item.getParaName());// ParaName
		if (whdatatypes.size() == 1) {
			whdatatype = whdatatypes.get(0);
		} else {
			whdatatype = new Whdatatype();
			whdatatype.setName(item.getParaName());
			getWhdatatypeDAO().save(whdatatype);
		}
		setWhdatatype(whdatatype);
		return whdatatype;
	}

	public Whdevice toWhdevice(Syncdata item) {
		Whdevice whdevice = null;
		List<Whdevice> whdevices = getWhdeviceDAO().findByNumber(item.getDeviceId());// DeviceID
		if (whdevices.size() == 1) {
			whdevice = whdevices.get(0);
		} else {
			whdevice = new Whdevice();
			whdevice.setNumber(item.getDeviceId());
			whdevice.setWhbuilding(getWhbuilding() == null ? toWhbuilding(item) : getWhbuilding());
			getWhdeviceDAO().save(whdevice);
		}
		return whdevice;
	}

	public Whdevicedata toWhdevicedata(Syncdata item) {
		Whdevicedata whdevicedata = new Whdevicedata();
		whdevicedata.setIsio(item.getIsIo() == null ? null : item.getIsIo().shortValue()); // isIO
		whdevicedata.setTime(item.getIdate()); // iDate
		whdevicedata.setValue(Double.parseDouble(item.getIvalue()));// iValue
		whdevicedata.setWhdatatype(getWhdatatype() == null ? toWhdatatype(item) : getWhdatatype());
		whdevicedata.setWhdevice(getWhdevice() == null ? toWhdevice(item) : getWhdevice());
		getWhdevicedataDAO().save(whdevicedata);
		return whdevicedata;
	}

	public WhbuildingDAO getWhbuildingDAO() {
		return whbuildingDAO;
	}

	public void setWhbuildingDAO(WhbuildingDAO whbuildingDAO) {
		this.whbuildingDAO = whbuildingDAO;
	}

	public WhdeviceDAO getWhdeviceDAO() {
		return whdeviceDAO;
	}

	public void setWhdeviceDAO(WhdeviceDAO whdeviceDAO) {
		this.whdeviceDAO = whdeviceDAO;
	}

	public WhdatatypeDAO getWhdatatypeDAO() {
		return whdatatypeDAO;
	}

	public void setWhdatatypeDAO(WhdatatypeDAO whdatatypeDAO) {
		this.whdatatypeDAO = whdatatypeDAO;
	}

	public WhdevicedataDAO getWhdevicedataDAO() {
		return whdevicedataDAO;
	}

	public void setWhdevicedataDAO(WhdevicedataDAO whdevicedataDAO) {
		this.whdevicedataDAO = whdevicedataDAO;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Whbuilding getWhbuilding() {
		return whbuilding;
	}

	public void setWhbuilding(Whbuilding whbuilding) {
		this.whbuilding = whbuilding;
	}

	public Whdatatype getWhdatatype() {
		return whdatatype;
	}

	public void setWhdatatype(Whdatatype whdatatype) {
		this.whdatatype = whdatatype;
	}

	public Whdevice getWhdevice() {
		return whdevice;
	}

	public void setWhdevice(Whdevice whdevice) {
		this.whdevice = whdevice;
	}

	public Whdevicedata getWhdevicedata() {
		return whdevicedata;
	}

	public void setWhdevicedata(Whdevicedata whdevicedata) {
		this.whdevicedata = whdevicedata;
	}

}
