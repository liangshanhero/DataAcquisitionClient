package cn.edu.scau.cmi.wuweijie.entity.server;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Whdevice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "whdevice")

public class Whdevice implements java.io.Serializable {

	// Fields

	@Override
	public String toString() {
		return "Whdevice [id=" + id + ", number=" + number + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((number == null) ? 0 : number.hashCode());
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
		Whdevice other = (Whdevice) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (number == null) {
			if (other.number != null)
				return false;
		} else if (!number.equals(other.number))
			return false;
		return true;
	}

	private Long id;
	private Whbuilding whbuilding;
	private String number;
	private Set<Whdevicedata> whdevicedatas = new HashSet<Whdevicedata>(0);


	private Set<Whdatatype> whdatatypes = new HashSet<Whdatatype>(0);

	// Constructors
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "whdatatype2whdevice", joinColumns = {
			@JoinColumn(name = "whdevice", referencedColumnName = "id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "whdatatype", referencedColumnName = "id", nullable = false) })
	public Set<Whdatatype> getWhdatatypes() {
		return whdatatypes;
	}

	public void setWhdatatypes(Set<Whdatatype> whdatatypes) {
		this.whdatatypes = whdatatypes;
	}

	/** default constructor */
	public Whdevice() {
	}

	/** full constructor */
	public Whdevice(Whbuilding whbuilding, String number, Set<Whdevicedata> whdevicedatas,
			Set<Whdatatype> whdatatypes) {
		this.whbuilding = whbuilding;
		this.number = number;
		this.whdevicedatas = whdevicedatas;
		this.whdatatypes = whdatatypes;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "id", unique = true, nullable = false)

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "whbuilding")

	public Whbuilding getWhbuilding() {
		return this.whbuilding;
	}

	public void setWhbuilding(Whbuilding whbuilding) {
		this.whbuilding = whbuilding;
	}

	@Column(name = "number", length = 20)

	public String getNumber() {
		return this.number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "whdevice")

	public Set<Whdevicedata> getWhdevicedatas() {
		return this.whdevicedatas;
	}

	public void setWhdevicedatas(Set<Whdevicedata> whdevicedatas) {
		this.whdevicedatas = whdevicedatas;
	}

}