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
 * Cacsensordata entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="cacsensordata"
)

public class Cacsensordata  implements java.io.Serializable {


    // Fields    

     private CacsensordataId id;
     private Cacrecordtime cacrecordtime;
     private Cacsensor cacsensor;
     private String value;
     private Integer isreport;


    // Constructors

    /** default constructor */
    public Cacsensordata() {
    }

	/** minimal constructor */
    public Cacsensordata(CacsensordataId id, Cacrecordtime cacrecordtime, Cacsensor cacsensor, Integer isreport) {
        this.id = id;
        this.cacrecordtime = cacrecordtime;
        this.cacsensor = cacsensor;
        this.isreport = isreport;
    }
    
    /** full constructor */
    public Cacsensordata(CacsensordataId id, Cacrecordtime cacrecordtime, Cacsensor cacsensor, String value, Integer isreport) {
        this.id = id;
        this.cacrecordtime = cacrecordtime;
        this.cacsensor = cacsensor;
        this.value = value;
        this.isreport = isreport;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="cacrecordtime", column=@Column(name="cacrecordtime", nullable=false) ), 
        @AttributeOverride(name="cacsensor", column=@Column(name="cacsensor", nullable=false) ) } )

    public CacsensordataId getId() {
        return this.id;
    }
    
    public void setId(CacsensordataId id) {
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
        @JoinColumn(name="cacsensor", nullable=false, insertable=false, updatable=false)

    public Cacsensor getCacsensor() {
        return this.cacsensor;
    }
    
    public void setCacsensor(Cacsensor cacsensor) {
        this.cacsensor = cacsensor;
    }
    
    @Column(name="value", length=10)

    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    @Column(name="isreport", nullable=false)

    public Integer getIsreport() {
        return this.isreport;
    }
    
    public void setIsreport(Integer isreport) {
        this.isreport = isreport;
    }
   








}