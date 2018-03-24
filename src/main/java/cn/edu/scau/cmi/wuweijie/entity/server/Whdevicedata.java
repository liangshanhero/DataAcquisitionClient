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
 * Whdevicedata entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="whdevicedata"
)

public class Whdevicedata  implements java.io.Serializable {


    // Fields    

     @Override
	public String toString() {
		return "Whdevicedata [id=" + id + ", time=" + time + ", value=" + value + ", isupdate=" + isupdate + ", isio="
				+ isio + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((isio == null) ? 0 : isio.hashCode());
		result = prime * result + ((isupdate == null) ? 0 : isupdate.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Whdevicedata other = (Whdevicedata) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isio == null) {
			if (other.isio != null)
				return false;
		} else if (!isio.equals(other.isio))
			return false;
		if (isupdate == null) {
			if (other.isupdate != null)
				return false;
		} else if (!isupdate.equals(other.isupdate))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	private Long id;
     private Whdatatype whdatatype;
     private Whdevice whdevice;
     private Timestamp time;
     private Double value;
     private Short isupdate;
     private Short isio;


    // Constructors

    /** default constructor */
    public Whdevicedata() {
    }

	/** minimal constructor */
    public Whdevicedata(Whdevice whdevice) {
        this.whdevice = whdevice;
    }
    
    /** full constructor */
    public Whdevicedata(Whdatatype whdatatype, Whdevice whdevice, Timestamp time, Double value, Short isupdate, Short isio) {
        this.whdatatype = whdatatype;
        this.whdevice = whdevice;
        this.time = time;
        this.value = value;
        this.isupdate = isupdate;
        this.isio = isio;
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
        @JoinColumn(name="whdatatype")

    public Whdatatype getWhdatatype() {
        return this.whdatatype;
    }
    
    public void setWhdatatype(Whdatatype whdatatype) {
        this.whdatatype = whdatatype;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="whdevice", nullable=false)

    public Whdevice getWhdevice() {
        return this.whdevice;
    }
    
    public void setWhdevice(Whdevice whdevice) {
        this.whdevice = whdevice;
    }
    
    @Column(name="time", length=19)

    public Timestamp getTime() {
        return this.time;
    }
    
    public void setTime(Timestamp time) {
        this.time = time;
    }
    
    @Column(name="value", precision=10)

    public Double getValue() {
        return this.value;
    }
    
    public void setValue(Double value) {
        this.value = value;
    }
    
    @Column(name="isupdate")

    public Short getIsupdate() {
        return this.isupdate;
    }
    
    public void setIsupdate(Short isupdate) {
        this.isupdate = isupdate;
    }
    
    @Column(name="isio")

    public Short getIsio() {
        return this.isio;
    }
    
    public void setIsio(Short isio) {
        this.isio = isio;
    }
   








}