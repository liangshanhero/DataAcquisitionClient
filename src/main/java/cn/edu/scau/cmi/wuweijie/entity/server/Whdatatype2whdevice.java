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
 * Whdatatype2whdevice entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="whdatatype2whdevice"
    ,catalog="energydevice"
)

public class Whdatatype2whdevice  implements java.io.Serializable {


    // Fields    

     private Whdatatype2whdeviceId id;
     private Whdatatype whdatatype;
     private Whdevice whdevice;


    // Constructors

    /** default constructor */
    public Whdatatype2whdevice() {
    }

    
    /** full constructor */
    public Whdatatype2whdevice(Whdatatype2whdeviceId id, Whdatatype whdatatype, Whdevice whdevice) {
        this.id = id;
        this.whdatatype = whdatatype;
        this.whdevice = whdevice;
    }

   
    // Property accessors
    @EmbeddedId
    
    @AttributeOverrides( {
        @AttributeOverride(name="whdatatype", column=@Column(name="whdatatype", nullable=false) ), 
        @AttributeOverride(name="whdevice", column=@Column(name="whdevice", nullable=false) ) } )

    public Whdatatype2whdeviceId getId() {
        return this.id;
    }
    
    public void setId(Whdatatype2whdeviceId id) {
        this.id = id;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="whdatatype", nullable=false, insertable=false, updatable=false)

    public Whdatatype getWhdatatype() {
        return this.whdatatype;
    }
    
    public void setWhdatatype(Whdatatype whdatatype) {
        this.whdatatype = whdatatype;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="whdevice", nullable=false, insertable=false, updatable=false)

    public Whdevice getWhdevice() {
        return this.whdevice;
    }
    
    public void setWhdevice(Whdevice whdevice) {
        this.whdevice = whdevice;
    }


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Whdatatype2whdevice other = (Whdatatype2whdevice) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
   








}