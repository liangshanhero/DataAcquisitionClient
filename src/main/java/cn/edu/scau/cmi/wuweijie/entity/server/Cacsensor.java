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
 * Cacsensor entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="cacsensor"
)

public class Cacsensor  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Cac cac;
     private String name;
     private Set<Cacsensordata> cacsensordatas = new HashSet<Cacsensordata>(0);


    // Constructors

    /** default constructor */
    public Cacsensor() {
    }

    
    /** full constructor */
    public Cacsensor(Cac cac, String name, Set<Cacsensordata> cacsensordatas) {
        this.cac = cac;
        this.name = name;
        this.cacsensordatas = cacsensordatas;
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
    
    @Column(name="name", length=20)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="cacsensor")

    public Set<Cacsensordata> getCacsensordatas() {
        return this.cacsensordatas;
    }
    
    public void setCacsensordatas(Set<Cacsensordata> cacsensordatas) {
        this.cacsensordatas = cacsensordatas;
    }
   








}