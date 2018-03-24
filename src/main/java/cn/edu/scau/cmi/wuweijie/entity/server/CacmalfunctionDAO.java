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
 	* A data access object (DAO) providing persistence and search support for Cacmalfunction entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.edu.scau.cmi.wuweijie.entity.server.Cacmalfunction
  * @author MyEclipse Persistence Tools 
 */
    @Transactional   
public class CacmalfunctionDAO  {
	     private static final Logger log = LoggerFactory.getLogger(CacmalfunctionDAO.class);
		//property constants
	public static final String STATUS = "status";



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
    
    public void save(Cacmalfunction transientInstance) {
        log.debug("saving Cacmalfunction instance");
        try {
            getCurrentSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Cacmalfunction persistentInstance) {
        log.debug("deleting Cacmalfunction instance");
        try {
            getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Cacmalfunction findById( cn.edu.scau.cmi.wuweijie.entity.server.CacmalfunctionId id) {
        log.debug("getting Cacmalfunction instance with id: " + id);
        try {
            Cacmalfunction instance = (Cacmalfunction) getCurrentSession()
                    .get("cn.edu.scau.cmi.wuweijie.entity.server.Cacmalfunction", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Cacmalfunction> findByExample(Cacmalfunction instance) {
        log.debug("finding Cacmalfunction instance by example");
        try {
            List<Cacmalfunction> results = (List<Cacmalfunction>) getCurrentSession() .createCriteria("cn.edu.scau.cmi.wuweijie.entity.server.Cacmalfunction").add( create(instance) ).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Cacmalfunction instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Cacmalfunction as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getCurrentSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Cacmalfunction> findByStatus(Object status
	) {
		return findByProperty(STATUS, status
		);
	}
	

	public List findAll() {
		log.debug("finding all Cacmalfunction instances");
		try {
			String queryString = "from Cacmalfunction";
	         Query queryObject = getCurrentSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Cacmalfunction merge(Cacmalfunction detachedInstance) {
        log.debug("merging Cacmalfunction instance");
        try {
            Cacmalfunction result = (Cacmalfunction) getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Cacmalfunction instance) {
        log.debug("attaching dirty Cacmalfunction instance");
        try {
            getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Cacmalfunction instance) {
        log.debug("attaching clean Cacmalfunction instance");
        try {
                      	getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static CacmalfunctionDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (CacmalfunctionDAO) ctx.getBean("CacmalfunctionDAO");
	}
}