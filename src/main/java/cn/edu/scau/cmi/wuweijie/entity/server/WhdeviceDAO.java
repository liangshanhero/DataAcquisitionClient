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
 	* A data access object (DAO) providing persistence and search support for Whdevice entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.edu.scau.cmi.wuweijie.entity.server.Whdevice
  * @author MyEclipse Persistence Tools 
 */
    @Transactional   
public class WhdeviceDAO  {
	     private static final Logger log = LoggerFactory.getLogger(WhdeviceDAO.class);
		//property constants
	public static final String NUMBER = "number";



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
    
    public void save(Whdevice transientInstance) {
        log.debug("saving Whdevice instance");
        try {
            getCurrentSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Whdevice persistentInstance) {
        log.debug("deleting Whdevice instance");
        try {
            getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Whdevice findById( java.lang.Long id) {
        log.debug("getting Whdevice instance with id: " + id);
        try {
            Whdevice instance = (Whdevice) getCurrentSession()
                    .get("cn.edu.scau.cmi.wuweijie.entity.server.Whdevice", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Whdevice> findByExample(Whdevice instance) {
        log.debug("finding Whdevice instance by example");
        try {
            List<Whdevice> results = (List<Whdevice>) getCurrentSession() .createCriteria("cn.edu.scau.cmi.wuweijie.entity.server.Whdevice").add( create(instance) ).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Whdevice instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Whdevice as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getCurrentSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Whdevice> findByNumber(Object number
	) {
		return findByProperty(NUMBER, number
		);
	}
	

	public List findAll() {
		log.debug("finding all Whdevice instances");
		try {
			String queryString = "from Whdevice";
	         Query queryObject = getCurrentSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Whdevice merge(Whdevice detachedInstance) {
        log.debug("merging Whdevice instance");
        try {
            Whdevice result = (Whdevice) getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Whdevice instance) {
        log.debug("attaching dirty Whdevice instance");
        try {
            getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Whdevice instance) {
        log.debug("attaching clean Whdevice instance");
        try {
                      	getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static WhdeviceDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (WhdeviceDAO) ctx.getBean("WhdeviceDAO");
	}
}