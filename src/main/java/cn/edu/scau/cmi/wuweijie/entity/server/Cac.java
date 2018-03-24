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
 * Cac entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="cac"
)

public class Cac  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Project project;
     private String remark;
     private Set<Cacsensor> cacsensors = new HashSet<Cacsensor>(0);
     private Set<Cacdevice> cacdevices = new HashSet<Cacdevice>(0);


    // Constructors

    /** default constructor */
    public Cac() {
    }

    
    /** full constructor */
    public Cac(Project project, String remark, Set<Cacsensor> cacsensors, Set<Cacdevice> cacdevices) {
        this.project = project;
        this.remark = remark;
        this.cacsensors = cacsensors;
        this.cacdevices = cacdevices;
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
    
    @Column(name="remark", length=100)

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="cac")

    public Set<Cacsensor> getCacsensors() {
        return this.cacsensors;
    }
    
    public void setCacsensors(Set<Cacsensor> cacsensors) {
        this.cacsensors = cacsensors;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="cac")

    public Set<Cacdevice> getCacdevices() {
        return this.cacdevices;
    }
    
    public void setCacdevices(Set<Cacdevice> cacdevices) {
        this.cacdevices = cacdevices;
    }
   








}