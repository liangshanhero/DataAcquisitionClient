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
 * Cacdevicedata entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="cacdevicedata"
)

public class Cacdevicedata  implements java.io.Serializable {


    // Fields    

     private CacdevicedataId id;
     private Cacrecordtime cacrecordtime;
     private Cacdevice cacdevice;
     private Double value;
     private Short isreport;


    // Constructors

    /** default constructor */
    public Cacdevicedata() {
    }

    
    /** full constructor */
    public Cacdevicedata(CacdevicedataId id, Cacrecordtime cacrecordtime, Cacdevice cacdevice, Double value, Short isreport) {
        this.id = id;
        this.cacrecordtime = cacrecordtime;
        this.cacdevice = cacdevice;
        this.value = value;
        this.isreport = isreport;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="cacdevice", column=@Column(name="cacdevice", nullable=false) ), 
        @AttributeOverride(name="cacrecordtime", column=@Column(name="cacrecordtime", nullable=false) ) } )

    public CacdevicedataId getId() {
        return this.id;
    }
    
    public void setId(CacdevicedataId id) {
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
    
    @Column(name="value", nullable=false, precision=10)

    public Double getValue() {
        return this.value;
    }
    
    public void setValue(Double value) {
        this.value = value;
    }
    
    @Column(name="isreport", nullable=false)

    public Short getIsreport() {
        return this.isreport;
    }
    
    public void setIsreport(Short isreport) {
        this.isreport = isreport;
    }
   








}