package cn.edu.scau.cmi.wuweijie.entity.server;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * Cacmalfunction entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="cacmalfunction"
)

public class Cacmalfunction  implements java.io.Serializable {


    // Fields    

     private CacmalfunctionId id;
     private Cacrecordtime cacrecordtime;
     private Cacdevice cacdevice;
     private String status;


    // Constructors

    /** default constructor */
    public Cacmalfunction() {
    }

	/** minimal constructor */
    public Cacmalfunction(CacmalfunctionId id, Cacrecordtime cacrecordtime, Cacdevice cacdevice) {
        this.id = id;
        this.cacrecordtime = cacrecordtime;
        this.cacdevice = cacdevice;
    }
    
    /** full constructor */
    public Cacmalfunction(CacmalfunctionId id, Cacrecordtime cacrecordtime, Cacdevice cacdevice, String status) {
        this.id = id;
        this.cacrecordtime = cacrecordtime;
        this.cacdevice = cacdevice;
        this.status = status;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="cacrecordtime", column=@Column(name="cacrecordtime", nullable=false) ), 
        @AttributeOverride(name="cacdevice", column=@Column(name="cacdevice", nullable=false) ) } )

    public CacmalfunctionId getId() {
        return this.id;
    }
    
    public void setId(CacmalfunctionId id) {
        this.id = id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="cacrecordtime", nullable=false, insertable=false, updatable=false)

    public Cacrecordtime getCacrecordtime() {
        return this.cacrecordtime;
    }
    
    public void setCacrecordtime(Cacrecordtime cacrecordtime) {
        this.cacrecordtime = cacrecordtime;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="cacdevice", nullable=false, insertable=false, updatable=false)

    public Cacdevice getCacdevice() {
        return this.cacdevice;
    }
    
    public void setCacdevice(Cacdevice cacdevice) {
        this.cacdevice = cacdevice;
    }
    
    @Column(name="status", length=3)

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
   








}