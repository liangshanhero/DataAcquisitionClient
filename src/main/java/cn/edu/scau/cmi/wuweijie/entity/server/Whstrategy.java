package cn.edu.scau.cmi.wuweijie.entity.server;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Whstrategy entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "whstrategy")

public class Whstrategy implements java.io.Serializable {

	// Fields

	private Long id;
	private Whdevice whdevice;
	private Boolean enable;
	private Timestamp createDate;
	private String remark;
	private String name;
	private Set<Whstrategydetail> whstrategydetails = new HashSet<Whstrategydetail>(0);

	// Constructors

	/** default constructor */
	public Whstrategy() {
	}

	/** full constructor */
	public Whstrategy(Whdevice whdevice, Boolean enable, Timestamp createDate, String remark, String name,
			Set<Whstrategydetail> whstrategydetails) {
		this.whdevice = whdevice;
		this.enable = enable;
		this.createDate = createDate;
		this.remark = remark;
		this.name = name;
		this.whstrategydetails = whstrategydetails;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)

	@Column(name = "id", unique = true, nullable = false)

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "whdevice")

	public Whdevice getWhdevice() {
		return this.whdevice;
	}

	public void setWhdevice(Whdevice whdevice) {
		this.whdevice = whdevice;
	}

	@Column(name = "enable")

	public Boolean getEnable() {
		return this.enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	@Column(name = "createDate", length = 19)

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	@Column(name = "remark")

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Column(name = "name", length = 30)

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "whstrategy")

	public Set<Whstrategydetail> getWhstrategydetails() {
		return this.whstrategydetails;
	}

	public void setWhstrategydetails(Set<Whstrategydetail> whstrategydetails) {
		this.whstrategydetails = whstrategydetails;
	}

}