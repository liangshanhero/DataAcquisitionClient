package cn.edu.scau.cmi.wuweijie.entity.client;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "processed")
public class Processed {
	
	@Override
	public String toString() {
		return "Processed [id=" + id + ", idate=" + idate + ", buildName=" + buildName + ", deviceId=" + deviceId
				+ ", paraName=" + paraName + ", ivalue=" + ivalue + ", isIo=" + isIo + ", upload=" + upload + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private Timestamp idate;

	private String buildName;

	private String deviceId;

	private String paraName;

	private String ivalue;

	private Long isIo;

	private Integer upload;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getIdate() {
		return idate;
	}

	public void setIdate(Timestamp idate) {
		this.idate = idate;
	}

	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getParaName() {
		return paraName;
	}

	public void setParaName(String paraName) {
		this.paraName = paraName;
	}

	public String getIvalue() {
		return ivalue;
	}

	public void setIvalue(String ivalue) {
		this.ivalue = ivalue;
	}

	public Long getIsIo() {
		return isIo;
	}

	public void setIsIo(Long isIo) {
		this.isIo = isIo;
	}

	public Integer getUpload() {
		return upload;
	}

	public void setUpload(Integer upload) {
		this.upload = upload;
	}

	
	
}
