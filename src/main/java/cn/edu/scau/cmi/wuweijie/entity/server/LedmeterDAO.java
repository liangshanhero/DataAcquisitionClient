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
 	* A data access object (DAO) providing persistence and search support for Ledmeter entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.edu.scau.cmi.wuweijie.entity.server.Ledmeter
  * @author MyEclipse Persistence Tools 
 */
    @Transactional   
public class LedmeterDAO  {
	     private static final Logger log = LoggerFactory.getLogger(LedmeterDAO.class);
		//property constants
	public static final String NUMBER = "number";
	public static final String WELL = "well";
	public static final String STOREY = "storey";
	public static final String TOTALAMOUT = "totalamout";
	public static final String TOTALDAYS = "totaldays";



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
    
    public void save(Ledmeter transientInstance) {
        log.debug("saving Ledmeter instance");
        try {
            getCurrentSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Ledmeter persistentInstance) {
        log.debug("deleting Ledmeter instance");
        try {
            getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Ledmeter findById( java.lang.Long id) {
        log.debug("getting Ledmeter instance with id: " + id);
        try {
            Ledmeter instance = (Ledmeter) getCurrentSession()
                    .get("cn.edu.scau.cmi.wuweijie.entity.server.Ledmeter", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Ledmeter> findByExample(Ledmeter instance) {
        log.debug("finding Ledmeter instance by example");
        try {
            List<Ledmeter> results = (List<Ledmeter>) getCurrentSession() .createCriteria("cn.edu.scau.cmi.wuweijie.entity.server.Ledmeter").add( create(instance) ).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Ledmeter instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Ledmeter as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getCurrentSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Ledmeter> findByNumber(Object number
	) {
		return findByProperty(NUMBER, number
		);
	}
	
	public List<Ledmeter> findByWell(Object well
	) {
		return findByProperty(WELL, well
		);
	}
	
	public List<Ledmeter> findByStorey(Object storey
	) {
		return findByProperty(STOREY, storey
		);
	}
	
	public List<Ledmeter> findByTotalamout(Object totalamout
	) {
		return findByProperty(TOTALAMOUT, totalamout
		);
	}
	
	public List<Ledmeter> findByTotaldays(Object totaldays
	) {
		return findByProperty(TOTALDAYS, totaldays
		);
	}
	

	public List findAll() {
		log.debug("finding all Ledmeter instances");
		try {
			String queryString = "from Ledmeter";
	         Query queryObject = getCurrentSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Ledmeter merge(Ledmeter detachedInstance) {
        log.debug("merging Ledmeter instance");
        try {
            Ledmeter result = (Ledmeter) getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Ledmeter instance) {
        log.debug("attaching dirty Ledmeter instance");
        try {
            getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Ledmeter instance) {
        log.debug("attaching clean Ledmeter instance");
        try {
                      	getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static LedmeterDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (LedmeterDAO) ctx.getBean("LedmeterDAO");
	}
}