package cn.edu.scau.cmi.wuweijie.entity.server;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * CacmalfunctionId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class CacmalfunctionId  implements java.io.Serializable {


    // Fields    

     private Long cacrecordtime;
     private Long cacdevice;


    // Constructors

    /** default constructor */
    public CacmalfunctionId() {
    }

    
    /** full constructor */
    public CacmalfunctionId(Long cacrecordtime, Long cacdevice) {
        this.cacrecordtime = cacrecordtime;
        this.cacdevice = cacdevice;
    }

   
    // Property accessors

    @Column(name="cacrecordtime", nullable=false)

    public Long getCacrecordtime() {
        return this.cacrecordtime;
    }
    
    public void setCacrecordtime(Long cacrecordtime) {
        this.cacrecordtime = cacrecordtime;
    }

    @Column(name="cacdevice", nullable=false)

    public Long getCacdevice() {
        return this.cacdevice;
    }
    
    public void setCacdevice(Long cacdevice) {
        this.cacdevice = cacdevice;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CacmalfunctionId) ) return false;
		 CacmalfunctionId castOther = ( CacmalfunctionId ) other; 
         
		 return ( (this.getCacrecordtime()==castOther.getCacrecordtime()) || ( this.getCacrecordtime()!=null && castOther.getCacrecordtime()!=null && this.getCacrecordtime().equals(castOther.getCacrecordtime()) ) )
 && ( (this.getCacdevice()==castOther.getCacdevice()) || ( this.getCacdevice()!=null && castOther.getCacdevice()!=null && this.getCacdevice().equals(castOther.getCacdevice()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCacrecordtime() == null ? 0 : this.getCacrecordtime().hashCode() );
         result = 37 * result + ( getCacdevice() == null ? 0 : this.getCacdevice().hashCode() );
         return result;
   }   





}