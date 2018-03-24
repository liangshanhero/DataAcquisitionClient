package cn.edu.scau.cmi.wuweijie.entity.server;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Ledmeterdata entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="ledmeterdata"
)

public class Ledmeterdata  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Ledmeter ledmeter;
     private Double value;
     private Timestamp time;


    // Constructors

    /** default constructor */
    public Ledmeterdata() {
    }

	/** minimal constructor */
    public Ledmeterdata(Double value) {
        this.value = value;
    }
    
    /** full constructor */
    public Ledmeterdata(Ledmeter ledmeter, Double value, Timestamp time) {
        this.ledmeter = ledmeter;
        this.value = value;
        this.time = time;
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
        @JoinColumn(name="ledmeter")

    public Ledmeter getLedmeter() {
        return this.ledmeter;
    }
    
    public void setLedmeter(Ledmeter ledmeter) {
        this.ledmeter = ledmeter;
    }
    
    @Column(name="value", nullable=false, precision=20, scale=4)

    public Double getValue() {
        return this.value;
    }
    
    public void setValue(Double value) {
        this.value = value;
    }
    
    @Column(name="time", length=19)

    public Timestamp getTime() {
        return this.time;
    }
    
    public void setTime(Timestamp time) {
        this.time = time;
    }
   








}