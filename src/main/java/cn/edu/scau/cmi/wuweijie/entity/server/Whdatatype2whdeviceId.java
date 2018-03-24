package cn.edu.scau.cmi.wuweijie.entity.server;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Whdatatype2whdeviceId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class Whdatatype2whdeviceId implements java.io.Serializable {

	// Fields

	private Long whdatatype;
	private Long whdevice;

	// Constructors

	/** default constructor */
	public Whdatatype2whdeviceId() {
	}

	/** full constructor */
	public Whdatatype2whdeviceId(Long whdatatype, Long whdevice) {
		this.whdatatype = whdatatype;
		this.whdevice = whdevice;
	}

	// Property accessors

	@Column(name = "whdatatype", nullable = false)

	public Long getWhdatatype() {
		return this.whdatatype;
	}

	public void setWhdatatype(Long whdatatype) {
		this.whdatatype = whdatatype;
	}

	@Column(name = "whdevice", nullable = false)

	public Long getWhdevice() {
		return this.whdevice;
	}

	public void setWhdevice(Long whdevice) {
		this.whdevice = whdevice;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof Whdatatype2whdeviceId))
			return false;
		Whdatatype2whdeviceId castOther = (Whdatatype2whdeviceId) other;

		return ((this.getWhdatatype() == castOther.getWhdatatype()) || (this.getWhdatatype() != null
				&& castOther.getWhdatatype() != null && this.getWhdatatype().equals(castOther.getWhdatatype())))
				&& ((this.getWhdevice() == castOther.getWhdevice()) || (this.getWhdevice() != null
						&& castOther.getWhdevice() != null && this.getWhdevice().equals(castOther.getWhdevice())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getWhdatatype() == null ? 0 : this.getWhdatatype().hashCode());
		result = 37 * result + (getWhdevice() == null ? 0 : this.getWhdevice().hashCode());
		return result;
	}

}