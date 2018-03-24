package cn.edu.scau.cmi.wuweijie.entity.server;

import javax.persistence.Column;
import javax.persistence.Embeddable;


/**
 * CacsensordataId entity. @author MyEclipse Persistence Tools
 */
@Embeddable

public class CacsensordataId  implements java.io.Serializable {


    // Fields    

     private Long cacrecordtime;
     private Long cacsensor;


    // Constructors

    /** default constructor */
    public CacsensordataId() {
    }

    
    /** full constructor */
    public CacsensordataId(Long cacrecordtime, Long cacsensor) {
        this.cacrecordtime = cacrecordtime;
        this.cacsensor = cacsensor;
    }

   
    // Property accessors

    @Column(name="cacrecordtime", nullable=false)

    public Long getCacrecordtime() {
        return this.cacrecordtime;
    }
    
    public void setCacrecordtime(Long cacrecordtime) {
        this.cacrecordtime = cacrecordtime;
    }

    @Column(name="cacsensor", nullable=false)

    public Long getCacsensor() {
        return this.cacsensor;
    }
    
    public void setCacsensor(Long cacsensor) {
        this.cacsensor = cacsensor;
    }
   



   public boolean equals(Object other) {
         if ( (this == other ) ) return true;
		 if ( (other == null ) ) return false;
		 if ( !(other instanceof CacsensordataId) ) return false;
		 CacsensordataId castOther = ( CacsensordataId ) other; 
         
		 return ( (this.getCacrecordtime()==castOther.getCacrecordtime()) || ( this.getCacrecordtime()!=null && castOther.getCacrecordtime()!=null && this.getCacrecordtime().equals(castOther.getCacrecordtime()) ) )
 && ( (this.getCacsensor()==castOther.getCacsensor()) || ( this.getCacsensor()!=null && castOther.getCacsensor()!=null && this.getCacsensor().equals(castOther.getCacsensor()) ) );
   }
   
   public int hashCode() {
         int result = 17;
         
         result = 37 * result + ( getCacrecordtime() == null ? 0 : this.getCacrecordtime().hashCode() );
         result = 37 * result + ( getCacsensor() == null ? 0 : this.getCacsensor().hashCode() );
         return result;
   }   





}