package cn.edu.scau.cmi.wuweijie.entity.server;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * CacdevicedataId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class CacdevicedataId  implements java.io.Serializable {


    // Fields    

     private Long cacdevice;
     private Long cacrecordtime;


    // Constructors

    /** default constructor */
    public CacdevicedataId() {
    }

    
    /** full constructor */
    public CacdevicedataId(Long cacdevice, Long cacrecordtime) {
        this.cacdevice = cacdevice;
        this.cacrecordtime = cacrecordtime;
    }

   
    // Property accessors

    @Column(name="cacdevice", nullable=false)

    public Long getCacdevice() {
        return this.cacdevice;
    }
    
    public void setCacdevice(Long cacdevice) {
        this.cacdevice = cacdevice;
    }

    @Column(name="cacrecordtime", nullable=false)

    public Long getCacrecordtime() {
        return this.cacrecordtime;
    }
    
    public void setCacrecordtime(Long cacrecordtime) {
        this.cacrecordtime = cacrecordtime;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CacdevicedataId) ) return false;
		 CacdevicedataId castOther = ( CacdevicedataId ) other; 
         
		 return ( (this.getCacdevice()==castOther.getCacdevice()) || ( this.getCacdevice()!=null && castOther.getCacdevice()!=null && this.getCacdevice().equals(castOther.getCacdevice()) ) )
 && ( (this.getCacrecordtime()==castOther.getCacrecordtime()) || ( this.getCacrecordtime()!=null && castOther.getCacrecordtime()!=null && this.getCacrecordtime().equals(castOther.getCacrecordtime()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCacdevice() == null ? 0 : this.getCacdevice().hashCode() );
         result = 37 * result + ( getCacrecordtime() == null ? 0 : this.getCacrecordtime().hashCode() );
         return result;
   }   





}