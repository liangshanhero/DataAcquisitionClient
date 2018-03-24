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
 	* A data access object (DAO) providing persistence and search support for Cacsensordata entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.edu.scau.cmi.wuweijie.entity.server.Cacsensordata
  * @author MyEclipse Persistence Tools 
 */
    @Transactional   
public class CacsensordataDAO  {
	     private static final Logger log = LoggerFactory.getLogger(CacsensordataDAO.class);
		//property constants
	public static final String VALUE = "value";
	public static final String ISREPORT = "isreport";



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
    
    public void save(Cacsensordata transientInstance) {
        log.debug("saving Cacsensordata instance");
        try {
            getCurrentSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Cacsensordata persistentInstance) {
        log.debug("deleting Cacsensordata instance");
        try {
            getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Cacsensordata findById( cn.edu.scau.cmi.wuweijie.entity.server.CacsensordataId id) {
        log.debug("getting Cacsensordata instance with id: " + id);
        try {
            Cacsensordata instance = (Cacsensordata) getCurrentSession()
                    .get("cn.edu.scau.cmi.wuweijie.entity.server.Cacsensordata", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Cacsensordata> findByExample(Cacsensordata instance) {
        log.debug("finding Cacsensordata instance by example");
        try {
            List<Cacsensordata> results = (List<Cacsensordata>) getCurrentSession() .createCriteria("cn.edu.scau.cmi.wuweijie.entity.server.Cacsensordata").add( create(instance) ).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Cacsensordata instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Cacsensordata as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getCurrentSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Cacsensordata> findByValue(Object value
	) {
		return findByProperty(VALUE, value
		);
	}
	
	public List<Cacsensordata> findByIsreport(Object isreport
	) {
		return findByProperty(ISREPORT, isreport
		);
	}
	

	public List findAll() {
		log.debug("finding all Cacsensordata instances");
		try {
			String queryString = "from Cacsensordata";
	         Query queryObject = getCurrentSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Cacsensordata merge(Cacsensordata detachedInstance) {
        log.debug("merging Cacsensordata instance");
        try {
            Cacsensordata result = (Cacsensordata) getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Cacsensordata instance) {
        log.debug("attaching dirty Cacsensordata instance");
        try {
            getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Cacsensordata instance) {
        log.debug("attaching clean Cacsensordata instance");
        try {
                      	getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static CacsensordataDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (CacsensordataDAO) ctx.getBean("CacsensordataDAO");
	}
}