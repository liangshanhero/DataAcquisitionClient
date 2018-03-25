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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Whstrategytype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "whstrategytype")

public class Whstrategytype implements java.io.Serializable {

	// Fields

	@Override
	public String toString() {
		return "Whstrategytype [id=" + id + ", name=" + name + "]";
	}

	private Long id;
	private String name;
	private Set<Whstrategydetail> whstrategydetails = new HashSet<Whstrategydetail>(0);

	// Constructors

	/** default constructor */
	public Whstrategytype() {
	}

	/** full constructor */
	public Whstrategytype(String name, Set<Whstrategydetail> whstrategydetails) {
		this.name = name;
		this.whstrategydetails = whstrategydetails;
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

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "whstrategytype")

	public Set<Whstrategydetail> getWhstrategydetails() {
		return this.whstrategydetails;
	}

	public void setWhstrategydetails(Set<Whstrategydetail> whstrategydetails) {
		this.whstrategydetails = whstrategydetails;
	}

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
		Whstrategytype other = (Whstrategytype) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}