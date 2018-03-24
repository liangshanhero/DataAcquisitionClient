package cn.edu.scau.cmi.wuweijie.batch.writer;

import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.springframework.batch.item.ItemWriter;

import cn.edu.scau.cmi.wuweijie.application.ConfigureController;
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
import cn.edu.scau.cmi.wuweijie.utils.CSPersistObjectConverter;

public abstract class WhbuildingItemWriter implements ItemWriter<Syncdata> {

	private WhdevicedataDAO whdevicedataDAO;

	private WhbuildingDAO whbuildingDAO;

	private WhdeviceDAO whdeviceDAO;

	private WhdatatypeDAO whdatatypeDAO;
	
	private String batchRecordFile;
	
	@Override
	public void write(List<? extends Syncdata> items) throws Exception {
		for (Syncdata item : items) {
			CSPersistObjectConverter converter = getConverter();
			converter.setProject(ConfigureController.currentProject);

			// whbuilding
			Whbuilding whbuilding = converter.toWhbuilding(item);

			// whdatatype
			Whdatatype whdatatype = converter.toWhdatatype(item);

			// whdevice
			Whdevice whdevice = converter.toWhdevice(item);

			// whdevicedata
			Whdevicedata whdevicedata = converter.toWhdevicedata(item);

			//突然发现级联保存好像不管用
//			whbuilding.getWhdevices().add(whdevice);
//			whdevice.getWhdatatypes().add(whdatatype);
//			whdevice.getWhdevicedatas().add(whdevicedata);

			// 各实体之间包含级联关系，可直接级联保存
//			getWhbuildingDAO().save(whbuilding);
		}
		// 已处理的最后一条数据Id，记录作为下次调度批处理任务的参数
		int latestId = items.get(items.size() - 1).getId();
		for (Syncdata item : items) {
			latestId = Integer.max(latestId, item.getId());
		}
		try (FileOutputStream out = new FileOutputStream(getBatchRecordFile())) {
			Properties props = new Properties();
			props.setProperty("syncdata.lastId", Integer.toString(latestId));
			props.store(out, "");
		}
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

	public String getBatchRecordFile() {
		return batchRecordFile;
	}

	public void setBatchRecordFile(String batchRecordFile) {
		this.batchRecordFile = batchRecordFile;
	}

	public abstract CSPersistObjectConverter getConverter();

}
