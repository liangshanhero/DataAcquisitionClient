package cn.edu.scau.cmi.wuweijie.entity.client;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Celve entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "celve")

public class Celve implements java.io.Serializable {

	// Fields

	private Integer id;
	private Timestamp time2;
	private String site;
	private String name;
	private Byte startHh;
	private Byte startMm;
	private Byte shutHh;
	private Byte shutMm;
	private Double x1;
	private Double x2;
	private Double x3;
	private Double x4;
	private Double x5;
	private Double x6;
	private Double x7;
	private Double operatingtemp;
	private Double shutTemp;
	private Double maxlevel;
	private Double minlevel;
	private Integer clsx;

	// Constructors

	/** default constructor */
	public Celve() {
	}

	/** full constructor */
	public Celve(Timestamp time2, String site, String name, Byte startHh, Byte startMm, Byte shutHh, Byte shutMm,
			Double x1, Double x2, Double x3, Double x4, Double x5, Double x6, Double x7, Double operatingtemp,
			Double shutTemp, Double maxlevel, Double minlevel, Integer clsx) {
		this.time2 = time2;
		this.site = site;
		this.name = name;
		this.startHh = startHh;
		this.startMm = startMm;
		this.shutHh = shutHh;
		this.shutMm = shutMm;
		this.x1 = x1;
		this.x2 = x2;
		this.x3 = x3;
		this.x4 = x4;
		this.x5 = x5;
		this.x6 = x6;
		this.x7 = x7;
		this.operatingtemp = operatingtemp;
		this.shutTemp = shutTemp;
		this.maxlevel = maxlevel;
		this.minlevel = minlevel;
		this.clsx = clsx;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "ID", unique = true, nullable = false)

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "time2", length = 19)

	public Timestamp getTime2() {
		return this.time2;
	}

	public void setTime2(Timestamp time2) {
		this.time2 = time2;
	}

	@Column(name = "site", length = 20)

	public String getSite() {
		return this.site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	@Column(name = "name", length = 20)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "startHH", precision = 2, scale = 0)

	public Byte getStartHh() {
		return this.startHh;
	}

	public void setStartHh(Byte startHh) {
		this.startHh = startHh;
	}

	@Column(name = "startMM", precision = 2, scale = 0)

	public Byte getStartMm() {
		return this.startMm;
	}

	public void setStartMm(Byte startMm) {
		this.startMm = startMm;
	}

	@Column(name = "shutHH", precision = 2, scale = 0)

	public Byte getShutHh() {
		return this.shutHh;
	}

	public void setShutHh(Byte shutHh) {
		this.shutHh = shutHh;
	}

	@Column(name = "shutMM", precision = 2, scale = 0)

	public Byte getShutMm() {
		return this.shutMm;
	}

	public void setShutMm(Byte shutMm) {
		this.shutMm = shutMm;
	}

	@Column(name = "x1", precision = 3, scale = 1)

	public Double getX1() {
		return this.x1;
	}

	public void setX1(Double x1) {
		this.x1 = x1;
	}

	@Column(name = "x2", precision = 3, scale = 1)

	public Double getX2() {
		return this.x2;
	}

	public void setX2(Double x2) {
		this.x2 = x2;
	}

	@Column(name = "x3", precision = 3, scale = 1)

	public Double getX3() {
		return this.x3;
	}

	public void setX3(Double x3) {
		this.x3 = x3;
	}

	@Column(name = "x4", precision = 3, scale = 1)

	public Double getX4() {
		return this.x4;
	}

	public void setX4(Double x4) {
		this.x4 = x4;
	}

	@Column(name = "x5", precision = 3, scale = 1)

	public Double getX5() {
		return this.x5;
	}

	public void setX5(Double x5) {
		this.x5 = x5;
	}

	@Column(name = "x6", precision = 3, scale = 1)

	public Double getX6() {
		return this.x6;
	}

	public void setX6(Double x6) {
		this.x6 = x6;
	}

	@Column(name = "x7", precision = 3, scale = 1)

	public Double getX7() {
		return this.x7;
	}

	public void setX7(Double x7) {
		this.x7 = x7;
	}

	@Column(name = "operatingtemp", precision = 3, scale = 1)

	public Double getOperatingtemp() {
		return this.operatingtemp;
	}

	public void setOperatingtemp(Double operatingtemp) {
		this.operatingtemp = operatingtemp;
	}

	@Column(name = "shutTemp", precision = 3, scale = 1)

	public Double getShutTemp() {
		return this.shutTemp;
	}

	public void setShutTemp(Double shutTemp) {
		this.shutTemp = shutTemp;
	}

	@Column(name = "maxlevel", precision = 3, scale = 1)

	public Double getMaxlevel() {
		return this.maxlevel;
	}

	public void setMaxlevel(Double maxlevel) {
		this.maxlevel = maxlevel;
	}

	@Column(name = "minlevel", precision = 3, scale = 1)

	public Double getMinlevel() {
		return this.minlevel;
	}

	public void setMinlevel(Double minlevel) {
		this.minlevel = minlevel;
	}

	@Column(name = "clsx")

	public Integer getClsx() {
		return this.clsx;
	}

	public void setClsx(Integer clsx) {
		this.clsx = clsx;
	}

}