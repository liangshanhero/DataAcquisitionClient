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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Whbuilding entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="whbuilding"
)

public class Whbuilding  implements java.io.Serializable {


    // Fields    

     @Override
	public String toString() {
		return "Whbuilding [id=" + id + ", name=" + name + ", whdevices=" + whdevices + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Whbuilding other = (Whbuilding) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	private Long id;
     private Project project;
     private String name;
     private Set<Whdevice> whdevices = new HashSet<Whdevice>(0);


    // Constructors

    /** default constructor */
    public Whbuilding() {
    }

	/** minimal constructor */
    public Whbuilding(String name) {
        this.name = name;
    }
    
    /** full constructor */
    public Whbuilding(Project project, String name, Set<Whdevice> whdevices) {
        this.project = project;
        this.name = name;
        this.whdevices = whdevices;
    }

   
    // Property accessors
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name="id", unique=true, nullable=false)

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="project")

    public Project getProject() {
        return this.project;
    }
    
    public void setProject(Project project) {
        this.project = project;
    }
    
    @Column(name="name", nullable=false, length=40)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="whbuilding")

    public Set<Whdevice> getWhdevices() {
        return this.whdevices;
    }
    
    public void setWhdevices(Set<Whdevice> whdevices) {
        this.whdevices = whdevices;
    }
   








}