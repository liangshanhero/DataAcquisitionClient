package cn.edu.scau.cmi.wuweijie.entity.server;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Whstrategydetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "whstrategydetail", catalog = "energydevice")

public class Whstrategydetail implements java.io.Serializable {

	// Fields

	private Long id;
	private Whstrategytype whstrategytype;
	private Whstrategy whstrategy;
	private Double max;
	private Double min;
	private Timestamp time;

	// Constructors

	/** default constructor */
	public Whstrategydetail() {
	}

	/** minimal constructor */
	public Whstrategydetail(Timestamp time) {
		this.time = time;
	}

	/** full constructor */
	public Whstrategydetail(Whstrategytype whstrategytype, Whstrategy whstrategy, Double max, Double min,
			Timestamp time) {
		this.whstrategytype = whstrategytype;
		this.whstrategy = whstrategy;
		this.max = max;
		this.min = min;
		this.time = time;
	}

	// Property accessors
	@Id
	@GeneratedValue

	@Column(name = "id", unique = true, nullable = false)

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "whstrategytype")

	public Whstrategytype getWhstrategytype() {
		return this.whstrategytype;
	}

	public void setWhstrategytype(Whstrategytype whstrategytype) {
		this.whstrategytype = whstrategytype;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "whstrategy")

	public Whstrategy getWhstrategy() {
		return this.whstrategy;
	}

	public void setWhstrategy(Whstrategy whstrategy) {
		this.whstrategy = whstrategy;
	}

	@Column(name = "max", precision = 10)

	public Double getMax() {
		return this.max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	@Column(name = "min", precision = 10)

	public Double getMin() {
		return this.min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	@Column(name = "time", nullable = false, length = 19)

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((max == null) ? 0 : max.hashCode());
		result = prime * result + ((min == null) ? 0 : min.hashCode());
		result = prime * result + ((whstrategytype == null) ? 0 : whstrategytype.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Whstrategydetail other = (Whstrategydetail) obj;
		if (max == null) {
			if (other.max != null)
				return false;
		} else if (!max.equals(other.max))
			return false;
		if (min == null) {
			if (other.min != null)
				return false;
		} else if (!min.equals(other.min))
			return false;
		if (whstrategytype == null) {
			if (other.whstrategytype != null)
				return false;
		} else if (!whstrategytype.equals(other.whstrategytype))
			return false;
		return true;
	}

}