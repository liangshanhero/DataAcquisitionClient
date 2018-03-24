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
 * Company entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="company"
)

public class Company  implements java.io.Serializable {


    // Fields    

     @Override
	public String toString() {
    	 return String.format("%s -- %s", name, address);
	}

	private Long id;
     private Staff staff;
     private String name;
     private String phone;
     private String fax;
     private String postcode;
     private String address;
     private String website;
     private String personduty;
     private String detail;
     private String remark;
     private Set<Project> projects = new HashSet<Project>(0);
     private Set<Staff> staffs = new HashSet<Staff>(0);


    // Constructors

    /** default constructor */
    public Company() {
    }

	/** minimal constructor */
    public Company(String name) {
        this.name = name;
    }
    
    /** full constructor */
    public Company(Staff staff, String name, String phone, String fax, String postcode, String address, String website, String personduty, String detail, String remark, Set<Project> projects, Set<Staff> staffs) {
        this.staff = staff;
        this.name = name;
        this.phone = phone;
        this.fax = fax;
        this.postcode = postcode;
        this.address = address;
        this.website = website;
        this.personduty = personduty;
        this.detail = detail;
        this.remark = remark;
        this.projects = projects;
        this.staffs = staffs;
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
        @JoinColumn(name="representative")

    public Staff getStaff() {
        return this.staff;
    }
    
    public void setStaff(Staff staff) {
        this.staff = staff;
    }
    
    @Column(name="name", nullable=false, length=50)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="phone", length=20)

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    @Column(name="fax", length=20)

    public String getFax() {
        return this.fax;
    }
    
    public void setFax(String fax) {
        this.fax = fax;
    }
    
    @Column(name="postcode", length=6)

    public String getPostcode() {
        return this.postcode;
    }
    
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
    @Column(name="address", length=100)

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    @Column(name="website", length=45)

    public String getWebsite() {
        return this.website;
    }
    
    public void setWebsite(String website) {
        this.website = website;
    }
    
    @Column(name="personduty", length=20)

    public String getPersonduty() {
        return this.personduty;
    }
    
    public void setPersonduty(String personduty) {
        this.personduty = personduty;
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
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy="company")

    public Set<Project> getProjects() {
        return this.projects;
    }
    
    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="company")

    public Set<Staff> getStaffs() {
        return this.staffs;
    }
    
    public void setStaffs(Set<Staff> staffs) {
        this.staffs = staffs;
    }
   








}