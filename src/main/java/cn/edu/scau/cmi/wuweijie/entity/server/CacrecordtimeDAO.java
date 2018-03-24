package cn.edu.scau.cmi.wuweijie.entity.server;

import java.sql.Timestamp;
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
 	* A data access object (DAO) providing persistence and search support for Cacrecordtime entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.edu.scau.cmi.wuweijie.entity.server.Cacrecordtime
  * @author MyEclipse Persistence Tools 
 */
    @Transactional   
public class CacrecordtimeDAO  {
	     private static final Logger log = LoggerFactory.getLogger(CacrecordtimeDAO.class);
		//property constants
	public static final String WATCHKEEPER = "watchkeeper";



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
    
    public void save(Cacrecordtime transientInstance) {
        log.debug("saving Cacrecordtime instance");
        try {
            getCurrentSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Cacrecordtime persistentInstance) {
        log.debug("deleting Cacrecordtime instance");
        try {
            getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Cacrecordtime findById( java.lang.Long id) {
        log.debug("getting Cacrecordtime instance with id: " + id);
        try {
            Cacrecordtime instance = (Cacrecordtime) getCurrentSession()
                    .get("cn.edu.scau.cmi.wuweijie.entity.server.Cacrecordtime", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Cacrecordtime> findByExample(Cacrecordtime instance) {
        log.debug("finding Cacrecordtime instance by example");
        try {
            List<Cacrecordtime> results = (List<Cacrecordtime>) getCurrentSession() .createCriteria("cn.edu.scau.cmi.wuweijie.entity.server.Cacrecordtime").add( create(instance) ).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Cacrecordtime instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Cacrecordtime as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getCurrentSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Cacrecordtime> findByWatchkeeper(Object watchkeeper
	) {
		return findByProperty(WATCHKEEPER, watchkeeper
		);
	}
	

	public List findAll() {
		log.debug("finding all Cacrecordtime instances");
		try {
			String queryString = "from Cacrecordtime";
	         Query queryObject = getCurrentSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Cacrecordtime merge(Cacrecordtime detachedInstance) {
        log.debug("merging Cacrecordtime instance");
        try {
            Cacrecordtime result = (Cacrecordtime) getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Cacrecordtime instance) {
        log.debug("attaching dirty Cacrecordtime instance");
        try {
            getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Cacrecordtime instance) {
        log.debug("attaching clean Cacrecordtime instance");
        try {
                      	getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static CacrecordtimeDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (CacrecordtimeDAO) ctx.getBean("CacrecordtimeDAO");
	}
}