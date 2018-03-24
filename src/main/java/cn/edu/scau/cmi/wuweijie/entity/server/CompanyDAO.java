package cn.edu.scau.cmi.wuweijie.entity.server;

import java.util.List;
import java.util.Set;
import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import static org.hibernate.criterion.Example.create;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

/**
 	* A data access object (DAO) providing persistence and search support for Company entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.edu.scau.cmi.wuweijie.entity.server.Company
  * @author MyEclipse Persistence Tools 
 */
    @Transactional   
public class CompanyDAO  {
	     private static final Logger log = LoggerFactory.getLogger(CompanyDAO.class);
		//property constants
	public static final String NAME = "name";
	public static final String PHONE = "phone";
	public static final String FAX = "fax";
	public static final String POSTCODE = "postcode";
	public static final String ADDRESS = "address";
	public static final String WEBSITE = "website";
	public static final String PERSONDUTY = "personduty";
	public static final String DETAIL = "detail";
	public static final String REMARK = "remark";



    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory){
       this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession(){
     return sessionFactory.getCurrentSession(); 
    }
	protected void initDao() {
		//do nothing
	}
    
    public void save(Company transientInstance) {
        log.debug("saving Company instance");
        try {
            getCurrentSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Company persistentInstance) {
        log.debug("deleting Company instance");
        try {
            getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Company findById( java.lang.Long id) {
        log.debug("getting Company instance with id: " + id);
        try {
            Company instance = (Company) getCurrentSession()
                    .get("cn.edu.scau.cmi.wuweijie.entity.server.Company", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Company> findByExample(Company instance) {
        log.debug("finding Company instance by example");
        try {
            List<Company> results = (List<Company>) getCurrentSession() .createCriteria("cn.edu.scau.cmi.wuweijie.entity.server.Company").add( create(instance) ).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Company instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Company as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getCurrentSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Company> findByName(Object name
	) {
		return findByProperty(NAME, name
		);
	}
	
	public List<Company> findByPhone(Object phone
	) {
		return findByProperty(PHONE, phone
		);
	}
	
	public List<Company> findByFax(Object fax
	) {
		return findByProperty(FAX, fax
		);
	}
	
	public List<Company> findByPostcode(Object postcode
	) {
		return findByProperty(POSTCODE, postcode
		);
	}
	
	public List<Company> findByAddress(Object address
	) {
		return findByProperty(ADDRESS, address
		);
	}
	
	public List<Company> findByWebsite(Object website
	) {
		return findByProperty(WEBSITE, website
		);
	}
	
	public List<Company> findByPersonduty(Object personduty
	) {
		return findByProperty(PERSONDUTY, personduty
		);
	}
	
	public List<Company> findByDetail(Object detail
	) {
		return findByProperty(DETAIL, detail
		);
	}
	
	public List<Company> findByRemark(Object remark
	) {
		return findByProperty(REMARK, remark
		);
	}
	

	public List findAll() {
		log.debug("finding all Company instances");
		try {
			String queryString = "from Company";
	         Query queryObject = getCurrentSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Company merge(Company detachedInstance) {
        log.debug("merging Company instance");
        try {
            Company result = (Company) getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Company instance) {
        log.debug("attaching dirty Company instance");
        try {
            getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Company instance) {
        log.debug("attaching clean Company instance");
        try {
                      	getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static CompanyDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (CompanyDAO) ctx.getBean("CompanyDAO");
	}
}