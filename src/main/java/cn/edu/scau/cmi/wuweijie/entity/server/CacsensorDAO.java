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
 	* A data access object (DAO) providing persistence and search support for Cacsensor entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.edu.scau.cmi.wuweijie.entity.server.Cacsensor
  * @author MyEclipse Persistence Tools 
 */
    @Transactional   
public class CacsensorDAO  {
	     private static final Logger log = LoggerFactory.getLogger(CacsensorDAO.class);
		//property constants
	public static final String NAME = "name";



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
    
    public void save(Cacsensor transientInstance) {
        log.debug("saving Cacsensor instance");
        try {
            getCurrentSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Cacsensor persistentInstance) {
        log.debug("deleting Cacsensor instance");
        try {
            getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Cacsensor findById( java.lang.Long id) {
        log.debug("getting Cacsensor instance with id: " + id);
        try {
            Cacsensor instance = (Cacsensor) getCurrentSession()
                    .get("cn.edu.scau.cmi.wuweijie.entity.server.Cacsensor", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Cacsensor> findByExample(Cacsensor instance) {
        log.debug("finding Cacsensor instance by example");
        try {
            List<Cacsensor> results = (List<Cacsensor>) getCurrentSession() .createCriteria("cn.edu.scau.cmi.wuweijie.entity.server.Cacsensor").add( create(instance) ).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Cacsensor instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Cacsensor as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getCurrentSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Cacsensor> findByName(Object name
	) {
		return findByProperty(NAME, name
		);
	}
	

	public List findAll() {
		log.debug("finding all Cacsensor instances");
		try {
			String queryString = "from Cacsensor";
	         Query queryObject = getCurrentSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Cacsensor merge(Cacsensor detachedInstance) {
        log.debug("merging Cacsensor instance");
        try {
            Cacsensor result = (Cacsensor) getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Cacsensor instance) {
        log.debug("attaching dirty Cacsensor instance");
        try {
            getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Cacsensor instance) {
        log.debug("attaching clean Cacsensor instance");
        try {
                      	getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static CacsensorDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (CacsensorDAO) ctx.getBean("CacsensorDAO");
	}
}