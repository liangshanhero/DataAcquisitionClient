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
 * Ledmeter entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="ledmeter"
)

public class Ledmeter  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Ledbuilding ledbuilding;
     private String number;
     private String well;
     private Long storey;
     private Double totalamout;
     private Double totaldays;
     private Set<Ledmeterdata> ledmeterdatas = new HashSet<Ledmeterdata>(0);


    // Constructors

    /** default constructor */
    public Ledmeter() {
    }

    
    /** full constructor */
    public Ledmeter(Ledbuilding ledbuilding, String number, String well, Long storey, Double totalamout, Double totaldays, Set<Ledmeterdata> ledmeterdatas) {
        this.ledbuilding = ledbuilding;
        this.number = number;
        this.well = well;
        this.storey = storey;
        this.totalamout = totalamout;
        this.totaldays = totaldays;
        this.ledmeterdatas = ledmeterdatas;
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
        @JoinColumn(name="ledbuilding")

    public Ledbuilding getLedbuilding() {
        return this.ledbuilding;
    }
    
    public void setLedbuilding(Ledbuilding ledbuilding) {
        this.ledbuilding = ledbuilding;
    }
    
    @Column(name="number")

    public String getNumber() {
        return this.number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
    
    @Column(name="well", length=20)

    public String getWell() {
        return this.well;
    }
    
    public void setWell(String well) {
        this.well = well;
    }
    
    @Column(name="storey")

    public Long getStorey() {
        return this.storey;
    }
    
    public void setStorey(Long storey) {
        this.storey = storey;
    }
    
    @Column(name="totalamout", precision=10)

    public Double getTotalamout() {
        return this.totalamout;
    }
    
    public void setTotalamout(Double totalamout) {
        this.totalamout = totalamout;
    }
    
    @Column(name="totaldays", precision=10)

    public Double getTotaldays() {
        return this.totaldays;
    }
    
    public void setTotaldays(Double totaldays) {
        this.totaldays = totaldays;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="ledmeter")

    public Set<Ledmeterdata> getLedmeterdatas() {
        return this.ledmeterdatas;
    }
    
    public void setLedmeterdatas(Set<Ledmeterdata> ledmeterdatas) {
        this.ledmeterdatas = ledmeterdatas;
    }
   








}