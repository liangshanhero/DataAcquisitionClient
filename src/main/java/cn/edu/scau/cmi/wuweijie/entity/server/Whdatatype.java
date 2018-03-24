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
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Whdatatype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "whdatatype")

public class Whdatatype implements java.io.Serializable {

	// Fields

	private Long id;
	private String name;
	private Set<Whdevicedata> whdevicedatas = new HashSet<Whdevicedata>(0);


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Whdatatype other = (Whdatatype) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	private Set<Whdevice> whdevices;

	// Constructors
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "whdatatype2whdevice", joinColumns = {
			@JoinColumn(name = "whdatatype", referencedColumnName = "id", nullable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "whdevice", referencedColumnName = "id", nullable = false) })
	public Set<Whdevice> getWhdevices() {
		return whdevices;
	}

	public void setWhdevices(Set<Whdevice> whdevices) {
		this.whdevices = whdevices;
	}

	/** default constructor */
	public Whdatatype() {
	}

	/** full constructor */
	public Whdatatype(String name, Set<Whdevicedata> whdevicedatas, Set<Whdevice> whdevices) {
		this.name = name;
		this.whdevicedatas = whdevicedatas;
		this.whdevices = whdevices;
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

	@Column(name = "name")

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "whdatatype")

	public Set<Whdevicedata> getWhdevicedatas() {
		return this.whdevicedatas;
	}

	public void setWhdevicedatas(Set<Whdevicedata> whdevicedatas) {
		this.whdevicedatas = whdevicedatas;
	}

}