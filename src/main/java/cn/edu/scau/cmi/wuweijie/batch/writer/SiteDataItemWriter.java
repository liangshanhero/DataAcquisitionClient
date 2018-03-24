package cn.edu.scau.cmi.wuweijie.batch.writer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.batch.item.ItemWriter;

import cn.edu.scau.cmi.wuweijie.application.ConfigureController;
import cn.edu.scau.cmi.wuweijie.entity.client.Data;
import cn.edu.scau.cmi.wuweijie.entity.server.ProjectDAO;
import cn.edu.scau.cmi.wuweijie.entity.server.Whbuilding;
import cn.edu.scau.cmi.wuweijie.entity.server.WhbuildingDAO;
import cn.edu.scau.cmi.wuweijie.entity.server.Whdatatype;
import cn.edu.scau.cmi.wuweijie.entity.server.Whdatatype2whdevice;
import cn.edu.scau.cmi.wuweijie.entity.server.Whdatatype2whdeviceDAO;
import cn.edu.scau.cmi.wuweijie.entity.server.Whdatatype2whdeviceId;
import cn.edu.scau.cmi.wuweijie.entity.server.WhdatatypeDAO;
import cn.edu.scau.cmi.wuweijie.entity.server.Whdevice;
import cn.edu.scau.cmi.wuweijie.entity.server.WhdeviceDAO;
import cn.edu.scau.cmi.wuweijie.entity.server.Whdevicedata;
import cn.edu.scau.cmi.wuweijie.entity.server.WhdevicedataDAO;

public class SiteDataItemWriter implements ItemWriter<Data> {

	private WhdeviceDAO whdeviceDAO;

	private WhdatatypeDAO whdatatypeDAO;

	private WhdevicedataDAO whdevicedataDAO;

	private Whdatatype2whdeviceDAO whdatatype2whdeviceDAO;
	
	private WhbuildingDAO whbuildingDAO;
	
	private ProjectDAO projectDAO;

	private ResourceBundle bundle;

	private String parameterName;

	@Override
	public void write(List<? extends Data> items) throws Exception {

		Map<String, Whdevice> allDevices = new HashMap<>();
		for (Object o : getWhdeviceDAO().findAll()) {
			if (o instanceof Whdevice) {
				Whdevice device = (Whdevice) o;
				allDevices.put(device.getNumber(), device);
			}
		}

		Map<String, Whdatatype> allTypes = new HashMap<>();
		for (Object o : getWhdatatypeDAO().findAll()) {
			if (o instanceof Whdatatype) {
				Whdatatype type = (Whdatatype) o;
				allTypes.put(type.getName(), type);
			}
		}

		Integer lastId = items.get(0).getId();
		for (Data data : items) {
			if (data.getId() > lastId) {
				lastId = data.getId();
			}
			Whdevice device = allDevices.get(data.getSite());
			if (device == null) {
				device = new Whdevice();
				device.setNumber(data.getSite());
				
				List<Whbuilding> buildings = whbuildingDAO.findByName("默认");
				Whbuilding area = null;
				if (buildings.size() > 0) {
					area = buildings.get(0);
				} else {
					area = new Whbuilding("默认");
					area.setProject(ConfigureController.currentProject);
					whbuildingDAO.save(area);
				}
				device.setWhbuilding(area);
				whdeviceDAO.save(device);
				allDevices.put(device.getNumber(), device);
			}

			String regex = "([A-Za-z_]+)(\\d+)?";
			Pattern pattern = Pattern.compile(regex);
			Map<String, Object> values = data.getValues();
			for (String key : values.keySet()) {

				/**
				 * 列为空即未使用，直接跳过
				 */
				Object value = values.get(key);
				if (value == null) {
					continue;
				}

				Matcher m = pattern.matcher(key);
				String prefix = "";
				String suffix = "";
				if (m.find()) {
					prefix = m.group(1);
					suffix = m.group(2) == null ? "" : m.group(2);
				}
				String paramName = bundle.getString("param." + prefix);
				if (!suffix.equals("")) {
					paramName += suffix;
				}

				Whdatatype type = allTypes.get(paramName);
				if (type == null) {
					type = new Whdatatype();
					type.setName(paramName);
					whdatatypeDAO.save(type);
					allTypes.put(type.getName(), type);
				}

				Whdatatype2whdevice type2device;
				Whdatatype2whdeviceId id = new Whdatatype2whdeviceId(type.getId(), device.getId());

				type2device = getWhdatatype2whdeviceDAO().findById(id);
				if (type2device == null) {
					type2device = new Whdatatype2whdevice(id, type, device);
					getWhdatatype2whdeviceDAO().save(type2device);
				}

				Whdevicedata deviceData = new Whdevicedata();
				deviceData.setTime(data.getTime());
				deviceData.setWhdevice(device);
				deviceData.setWhdatatype(type);

				if (value instanceof Boolean) {
					deviceData.setValue(((Boolean) value) ? 1.0 : 0.0);
				} else if (value instanceof Number) {
					deviceData.setValue(((Number) value).doubleValue());
				}
				whdevicedataDAO.save(deviceData);
			}
		}
		Properties props = new Properties();
		try (FileInputStream in = new FileInputStream(getBatchRecordFile());) {
			props.load(in);
		}
		try (FileOutputStream out = new FileOutputStream(getBatchRecordFile())) {
			props.setProperty(getParameterName(), Integer.toString(lastId));
			props.store(out, "");
		}
	}

	private String batchRecordFile;

	public String getBatchRecordFile() {
		return batchRecordFile;
	}

	public void setBatchRecordFile(String batchRecordFile) {
		this.batchRecordFile = batchRecordFile;
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

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public Whdatatype2whdeviceDAO getWhdatatype2whdeviceDAO() {
		return whdatatype2whdeviceDAO;
	}

	public void setWhdatatype2whdeviceDAO(Whdatatype2whdeviceDAO whdatatype2whdeviceDAO) {
		this.whdatatype2whdeviceDAO = whdatatype2whdeviceDAO;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public WhbuildingDAO getWhbuildingDAO() {
		return whbuildingDAO;
	}

	public void setWhbuildingDAO(WhbuildingDAO whbuildingDAO) {
		this.whbuildingDAO = whbuildingDAO;
	}

	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

}
