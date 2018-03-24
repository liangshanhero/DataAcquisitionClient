package cn.edu.scau.cmi.wuweijie.entity.server;

import java.sql.Timestamp;
import java.util.List;
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
 	* A data access object (DAO) providing persistence and search support for Ledmeterdata entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.edu.scau.cmi.wuweijie.entity.server.Ledmeterdata
  * @author MyEclipse Persistence Tools 
 */
    @Transactional   
public class LedmeterdataDAO  {
	     private static final Logger log = LoggerFactory.getLogger(LedmeterdataDAO.class);
		//property constants
	public static final String VALUE = "value";



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
    
    public void save(Ledmeterdata transientInstance) {
        log.debug("saving Ledmeterdata instance");
        try {
            getCurrentSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Ledmeterdata persistentInstance) {
        log.debug("deleting Ledmeterdata instance");
        try {
            getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Ledmeterdata findById( java.lang.Long id) {
        log.debug("getting Ledmeterdata instance with id: " + id);
        try {
            Ledmeterdata instance = (Ledmeterdata) getCurrentSession()
                    .get("cn.edu.scau.cmi.wuweijie.entity.server.Ledmeterdata", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Ledmeterdata> findByExample(Ledmeterdata instance) {
        log.debug("finding Ledmeterdata instance by example");
        try {
            List<Ledmeterdata> results = (List<Ledmeterdata>) getCurrentSession() .createCriteria("cn.edu.scau.cmi.wuweijie.entity.server.Ledmeterdata").add( create(instance) ).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Ledmeterdata instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Ledmeterdata as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getCurrentSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Ledmeterdata> findByValue(Object value
	) {
		return findByProperty(VALUE, value
		);
	}
	

	public List findAll() {
		log.debug("finding all Ledmeterdata instances");
		try {
			String queryString = "from Ledmeterdata";
	         Query queryObject = getCurrentSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Ledmeterdata merge(Ledmeterdata detachedInstance) {
        log.debug("merging Ledmeterdata instance");
        try {
            Ledmeterdata result = (Ledmeterdata) getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Ledmeterdata instance) {
        log.debug("attaching dirty Ledmeterdata instance");
        try {
            getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Ledmeterdata instance) {
        log.debug("attaching clean Ledmeterdata instance");
        try {
                      	getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static LedmeterdataDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (LedmeterdataDAO) ctx.getBean("LedmeterdataDAO");
	}
}