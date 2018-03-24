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
 * Staff entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="staff"
)

public class Staff  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Company company;
     private String name;
     private String duty;
     private String token;
     private String type;
     private String status;
     private String level;
     private String loginname;
     private String password;
     private String remark;
     private Set<Project> projects = new HashSet<Project>(0);
     private Set<Company> companies = new HashSet<Company>(0);


    // Constructors

    /** default constructor */
    public Staff() {
    }

	/** minimal constructor */
    public Staff(String name, String type, String status, String level, String loginname, String password) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.level = level;
        this.loginname = loginname;
        this.password = password;
    }
    
    /** full constructor */
    public Staff(Company company, String name, String duty, String token, String type, String status, String level, String loginname, String password, String remark, Set<Project> projects, Set<Company> companies) {
        this.company = company;
        this.name = name;
        this.duty = duty;
        this.token = token;
        this.type = type;
        this.status = status;
        this.level = level;
        this.loginname = loginname;
        this.password = password;
        this.remark = remark;
        this.projects = projects;
        this.companies = companies;
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
        @JoinColumn(name="company")

    public Company getCompany() {
        return this.company;
    }
    
    public void setCompany(Company company) {
        this.company = company;
    }
    
    @Column(name="name", nullable=false, length=20)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="duty", length=20)

    public String getDuty() {
        return this.duty;
    }
    
    public void setDuty(String duty) {
        this.duty = duty;
    }
    
    @Column(name="token", length=36)

    public String getToken() {
        return this.token;
    }
    
    public void setToken(String token) {
        this.token = token;
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
    
    @Column(name="level", nullable=false, length=10)

    public String getLevel() {
        return this.level;
    }
    
    public void setLevel(String level) {
        this.level = level;
    }
    
    @Column(name="loginname", nullable=false, length=20)

    public String getLoginname() {
        return this.loginname;
    }
    
    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }
    
    @Column(name="password", nullable=false, length=32)

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    @Column(name="remark")

    public String getRemark() {
        return this.remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="staff")

    public Set<Project> getProjects() {
        return this.projects;
    }
    
    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="staff")

    public Set<Company> getCompanies() {
        return this.companies;
    }
    
    public void setCompanies(Set<Company> companies) {
        this.companies = companies;
    }
   








}