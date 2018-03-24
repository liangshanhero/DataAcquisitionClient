package cn.edu.scau.cmi.wuweijie.entity.client;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Syncdata entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "syncdata")

public class Syncdata implements java.io.Serializable {

	// Fields

	@Override
	public String toString() {
		return "Syncdata [id=" + id + ", idate=" + idate + ", buildName=" + buildName + ", deviceId=" + deviceId
				+ ", paraName=" + paraName + ", ivalue=" + ivalue + ", isIo=" + isIo + ", upload=" + upload + "]";
	}

	private Integer id;
	private Timestamp idate;
	private String buildName;
	private String deviceId;
	private String paraName;
	private String ivalue;
	private Long isIo;
	private Integer upload;

	// Constructors

	/** default constructor */
	public Syncdata() {
	}

	/** full constructor */
	public Syncdata(Timestamp idate, String buildName, String deviceId, String paraName, String ivalue, Long isIo,
			Integer upload) {
		this.idate = idate;
		this.buildName = buildName;
		this.deviceId = deviceId;
		this.paraName = paraName;
		this.ivalue = ivalue;
		this.isIo = isIo;
		this.upload = upload;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "ID", unique = true, nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "iDate", length = 19)

	public Timestamp getIdate() {
		return this.idate;
	}

	public void setIdate(Timestamp idate) {
		this.idate = idate;
	}

	@Column(name = "BuildName", length = 65535)

	public String getBuildName() {
		return this.buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	@Column(name = "DeviceID", length = 65535)

	public String getDeviceId() {
		return this.deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	@Column(name = "ParaName", length = 65535)

	public String getParaName() {
		return this.paraName;
	}

	public void setParaName(String paraName) {
		this.paraName = paraName;
	}

	@Column(name = "iValue", length = 65535)

	public String getIvalue() {
		return this.ivalue;
	}

	public void setIvalue(String ivalue) {
		this.ivalue = ivalue;
	}

	@Column(name = "isIO", precision = 10, scale = 0)

	public Long getIsIo() {
		return this.isIo;
	}

	public void setIsIo(Long isIo) {
		this.isIo = isIo;
	}

	@Column(name = "Upload")

	public Integer getUpload() {
		return this.upload;
	}

	public void setUpload(Integer upload) {
		this.upload = upload;
	}

}