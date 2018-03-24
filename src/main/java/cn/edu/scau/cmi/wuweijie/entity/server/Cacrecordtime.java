package cn.edu.scau.cmi.wuweijie.entity.server;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Cacrecordtime entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="cacrecordtime"
)

public class Cacrecordtime  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Timestamp recordTime;
     private String watchkeeper;
     private Set<Cacdevicedata> cacdevicedatas = new HashSet<Cacdevicedata>(0);
     private Set<Cacsensordata> cacsensordatas = new HashSet<Cacsensordata>(0);
     private Set<Cacmalfunction> cacmalfunctions = new HashSet<Cacmalfunction>(0);


    // Constructors

    /** default constructor */
    public Cacrecordtime() {
    }

    
    /** full constructor */
    public Cacrecordtime(Timestamp recordTime, String watchkeeper, Set<Cacdevicedata> cacdevicedatas, Set<Cacsensordata> cacsensordatas, Set<Cacmalfunction> cacmalfunctions) {
        this.recordTime = recordTime;
        this.watchkeeper = watchkeeper;
        this.cacdevicedatas = cacdevicedatas;
        this.cacsensordatas = cacsensordatas;
        this.cacmalfunctions = cacmalfunctions;
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
    
    @Column(name="RecordTime", length=19)

    public Timestamp getRecordTime() {
        return this.recordTime;
    }
    
    public void setRecordTime(Timestamp recordTime) {
        this.recordTime = recordTime;
    }
    
    @Column(name="Watchkeeper", length=20)

    public String getWatchkeeper() {
        return this.watchkeeper;
    }
    
    public void setWatchkeeper(String watchkeeper) {
        this.watchkeeper = watchkeeper;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="cacrecordtime")

    public Set<Cacdevicedata> getCacdevicedatas() {
        return this.cacdevicedatas;
    }
    
    public void setCacdevicedatas(Set<Cacdevicedata> cacdevicedatas) {
        this.cacdevicedatas = cacdevicedatas;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="cacrecordtime")

    public Set<Cacsensordata> getCacsensordatas() {
        return this.cacsensordatas;
    }
    
    public void setCacsensordatas(Set<Cacsensordata> cacsensordatas) {
        this.cacsensordatas = cacsensordatas;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="cacrecordtime")

    public Set<Cacmalfunction> getCacmalfunctions() {
        return this.cacmalfunctions;
    }
    
    public void setCacmalfunctions(Set<Cacmalfunction> cacmalfunctions) {
        this.cacmalfunctions = cacmalfunctions;
    }
   








}