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
 	* A data access object (DAO) providing persistence and search support for Cacdevice entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.edu.scau.cmi.wuweijie.entity.server.Cacdevice
  * @author MyEclipse Persistence Tools 
 */
    @Transactional   
public class CacdeviceDAO  {
	     private static final Logger log = LoggerFactory.getLogger(CacdeviceDAO.class);
		//property constants
	public static final String NAME = "name";
	public static final String UNIT = "unit";



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
    
    public void save(Cacdevice transientInstance) {
        log.debug("saving Cacdevice instance");
        try {
            getCurrentSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Cacdevice persistentInstance) {
        log.debug("deleting Cacdevice instance");
        try {
            getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Cacdevice findById( java.lang.Long id) {
        log.debug("getting Cacdevice instance with id: " + id);
        try {
            Cacdevice instance = (Cacdevice) getCurrentSession()
                    .get("cn.edu.scau.cmi.wuweijie.entity.server.Cacdevice", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Cacdevice> findByExample(Cacdevice instance) {
        log.debug("finding Cacdevice instance by example");
        try {
            List<Cacdevice> results = (List<Cacdevice>) getCurrentSession() .createCriteria("cn.edu.scau.cmi.wuweijie.entity.server.Cacdevice").add( create(instance) ).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Cacdevice instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Cacdevice as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getCurrentSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Cacdevice> findByName(Object name
	) {
		return findByProperty(NAME, name
		);
	}
	
	public List<Cacdevice> findByUnit(Object unit
	) {
		return findByProperty(UNIT, unit
		);
	}
	

	public List findAll() {
		log.debug("finding all Cacdevice instances");
		try {
			String queryString = "from Cacdevice";
	         Query queryObject = getCurrentSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Cacdevice merge(Cacdevice detachedInstance) {
        log.debug("merging Cacdevice instance");
        try {
            Cacdevice result = (Cacdevice) getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Cacdevice instance) {
        log.debug("attaching dirty Cacdevice instance");
        try {
            getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Cacdevice instance) {
        log.debug("attaching clean Cacdevice instance");
        try {
                      	getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static CacdeviceDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (CacdeviceDAO) ctx.getBean("CacdeviceDAO");
	}
}