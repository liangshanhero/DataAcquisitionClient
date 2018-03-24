package cn.edu.scau.cmi.wuweijie.entity.server;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Project entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="project"
)

public class Project  implements java.io.Serializable {

	@Override
	public String toString() {
		return String.format("%s - %s %s %s %s", name, province, city, area, address);
	}
	
    // Fields    

     private Long id;
     private Company company;
     private Staff staff;
     private String name;
     private String type;
     private String status;
     private String province;
     private String city;
     private String area;
     private String address;
     private String detail;
     private String remark;
     private Set<Cac> cacs = new HashSet<Cac>(0);
     private Set<Whbuilding> whbuildings = new HashSet<Whbuilding>(0);
     private Set<Ledbuilding> ledbuildings = new HashSet<Ledbuilding>(0);


    // Constructors

    /** default constructor */
    public Project() {
    }

	/** minimal constructor */
    public Project(Company company, String name, String type, String status) {
        this.company = company;
        this.name = name;
        this.type = type;
        this.status = status;
    }
    
    /** full constructor */
    public Project(Company company, Staff staff, String name, String type, String status, String province, String city, String area, String address, String detail, String remark, Set<Cac> cacs, Set<Whbuilding> whbuildings, Set<Ledbuilding> ledbuildings) {
        this.company = company;
        this.staff = staff;
        this.name = name;
        this.type = type;
        this.status = status;
        this.province = province;
        this.city = city;
        this.area = area;
        this.address = address;
        this.detail = detail;
        this.remark = remark;
        this.cacs = cacs;
        this.whbuildings = whbuildings;
        this.ledbuildings = ledbuildings;
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
        @JoinColumn(name="company", nullable=false)

    public Company getCompany() {
        return this.company;
    }
    
    public void setCompany(Company company) {
        this.company = company;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="responsibility")

    public Staff getStaff() {
        return this.staff;
    }
    
    public void setStaff(Staff staff) {
        this.staff = staff;
    }
    
    @Column(name="name", nullable=false, length=20)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="type", nullable=false, length=10)

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    @Column(name="status", nullable=false, length=10)

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="province", length=45)

    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }
    
    @Column(name="city", length=45)

    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    @Column(name="area", length=45)

    public String getArea() {
        return this.area;
    }
    
    public void setArea(String area) {
        this.area = area;
    }
    
    @Column(name="address", length=100)

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Column(name="detail", length=65535)

    public String getDetail() {
        return this.detail;
    }
    
    public void setDetail(String detail) {
        this.detail = detail;
    }
    
    @Column(name="remark", length=65535)

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="project")

    public Set<Cac> getCacs() {
        return this.cacs;
    }
    
    public void setCacs(Set<Cac> cacs) {
        this.cacs = cacs;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="project")

    public Set<Whbuilding> getWhbuildings() {
        return this.whbuildings;
    }
    
    public void setWhbuildings(Set<Whbuilding> whbuildings) {
        this.whbuildings = whbuildings;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="project")

    public Set<Ledbuilding> getLedbuildings() {
        return this.ledbuildings;
    }
    
    public void setLedbuildings(Set<Ledbuilding> ledbuildings) {
        this.ledbuildings = ledbuildings;
    }
   








}