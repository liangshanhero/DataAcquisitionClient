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
 * Ledbuilding entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="ledbuilding"
)

public class Ledbuilding  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Project project;
     private String name;
     private Integer well;
     private Integer storey;
     private Set<Ledmeter> ledmeters = new HashSet<Ledmeter>(0);


    // Constructors

    /** default constructor */
    public Ledbuilding() {
    }

	/** minimal constructor */
    public Ledbuilding(String name) {
        this.name = name;
    }
    
    /** full constructor */
    public Ledbuilding(Project project, String name, Integer well, Integer storey, Set<Ledmeter> ledmeters) {
        this.project = project;
        this.name = name;
        this.well = well;
        this.storey = storey;
        this.ledmeters = ledmeters;
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
    
    @Column(name="well")

    public Integer getWell() {
        return this.well;
    }
    
    public void setWell(Integer well) {
        this.well = well;
    }
    
    @Column(name="storey")

    public Integer getStorey() {
        return this.storey;
    }
    
    public void setStorey(Integer storey) {
        this.storey = storey;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="ledbuilding")

    public Set<Ledmeter> getLedmeters() {
        return this.ledmeters;
    }
    
    public void setLedmeters(Set<Ledmeter> ledmeters) {
        this.ledmeters = ledmeters;
    }
   








}