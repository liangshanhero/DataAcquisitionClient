package cn.edu.scau.cmi.wuweijie.entity.server;

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
 	* A data access object (DAO) providing persistence and search support for Whdatatype2whdevice entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.edu.scau.cmi.wuweijie.entity.server.Whdatatype2whdevice
  * @author MyEclipse Persistence Tools 
 */
    @Transactional   
public class Whdatatype2whdeviceDAO  {
	     private static final Logger log = LoggerFactory.getLogger(Whdatatype2whdeviceDAO.class);
		//property constants



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
    
    public void save(Whdatatype2whdevice transientInstance) {
        log.debug("saving Whdatatype2whdevice instance");
        try {
            getCurrentSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Whdatatype2whdevice persistentInstance) {
        log.debug("deleting Whdatatype2whdevice instance");
        try {
            getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Whdatatype2whdevice findById( cn.edu.scau.cmi.wuweijie.entity.server.Whdatatype2whdeviceId id) {
        log.debug("getting Whdatatype2whdevice instance with id: " + id);
        try {
            Whdatatype2whdevice instance = (Whdatatype2whdevice) getCurrentSession()
                    .get("cn.edu.scau.cmi.wuweijie.entity.server.Whdatatype2whdevice", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Whdatatype2whdevice> findByExample(Whdatatype2whdevice instance) {
        log.debug("finding Whdatatype2whdevice instance by example");
        try {
            List<Whdatatype2whdevice> results = (List<Whdatatype2whdevice>) getCurrentSession() .createCriteria("cn.edu.scau.cmi.wuweijie.entity.server.Whdatatype2whdevice").add( create(instance) ).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Whdatatype2whdevice instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Whdatatype2whdevice as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getCurrentSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}


	public List findAll() {
		log.debug("finding all Whdatatype2whdevice instances");
		try {
			String queryString = "from Whdatatype2whdevice";
	         Query queryObject = getCurrentSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Whdatatype2whdevice merge(Whdatatype2whdevice detachedInstance) {
        log.debug("merging Whdatatype2whdevice instance");
        try {
            Whdatatype2whdevice result = (Whdatatype2whdevice) getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Whdatatype2whdevice instance) {
        log.debug("attaching dirty Whdatatype2whdevice instance");
        try {
            getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Whdatatype2whdevice instance) {
        log.debug("attaching clean Whdatatype2whdevice instance");
        try {
                      	getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static Whdatatype2whdeviceDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (Whdatatype2whdeviceDAO) ctx.getBean("Whdatatype2whdeviceDAO");
	}
}