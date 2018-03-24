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
 * Cacdevice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="cacdevice"
)

public class Cacdevice  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Cac cac;
     private String name;
     private String unit;
     private Set<Cacdevicedata> cacdevicedatas = new HashSet<Cacdevicedata>(0);
     private Set<Cacmalfunction> cacmalfunctions = new HashSet<Cacmalfunction>(0);


    // Constructors

    /** default constructor */
    public Cacdevice() {
    }

    
    /** full constructor */
    public Cacdevice(Cac cac, String name, String unit, Set<Cacdevicedata> cacdevicedatas, Set<Cacmalfunction> cacmalfunctions) {
        this.cac = cac;
        this.name = name;
        this.unit = unit;
        this.cacdevicedatas = cacdevicedatas;
        this.cacmalfunctions = cacmalfunctions;
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
        @JoinColumn(name="cac")

    public Cac getCac() {
        return this.cac;
    }
    
    public void setCac(Cac cac) {
        this.cac = cac;
    }
    
    @Column(name="name", length=50)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="unit", length=10)

    public String getUnit() {
        return this.unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="cacdevice")

    public Set<Cacdevicedata> getCacdevicedatas() {
        return this.cacdevicedatas;
    }
    
    public void setCacdevicedatas(Set<Cacdevicedata> cacdevicedatas) {
        this.cacdevicedatas = cacdevicedatas;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="cacdevice")

    public Set<Cacmalfunction> getCacmalfunctions() {
        return this.cacmalfunctions;
    }
    
    public void setCacmalfunctions(Set<Cacmalfunction> cacmalfunctions) {
        this.cacmalfunctions = cacmalfunctions;
    }
   








}