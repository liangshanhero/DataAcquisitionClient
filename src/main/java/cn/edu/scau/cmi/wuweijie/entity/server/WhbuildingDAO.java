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
 	* A data access object (DAO) providing persistence and search support for Whbuilding entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.edu.scau.cmi.wuweijie.entity.server.Whbuilding
  * @author MyEclipse Persistence Tools 
 */
    @Transactional   
public class WhbuildingDAO  {
	     private static final Logger log = LoggerFactory.getLogger(WhbuildingDAO.class);
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
    
    public void save(Whbuilding transientInstance) {
        log.debug("saving Whbuilding instance");
        try {
            getCurrentSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Whbuilding persistentInstance) {
        log.debug("deleting Whbuilding instance");
        try {
            getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Whbuilding findById( java.lang.Long id) {
        log.debug("getting Whbuilding instance with id: " + id);
        try {
            Whbuilding instance = (Whbuilding) getCurrentSession()
                    .get("cn.edu.scau.cmi.wuweijie.entity.server.Whbuilding", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Whbuilding> findByExample(Whbuilding instance) {
        log.debug("finding Whbuilding instance by example");
        try {
            List<Whbuilding> results = (List<Whbuilding>) getCurrentSession() .createCriteria("cn.edu.scau.cmi.wuweijie.entity.server.Whbuilding").add( create(instance) ).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Whbuilding instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Whbuilding as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getCurrentSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Whbuilding> findByName(Object name
	) {
		return findByProperty(NAME, name
		);
	}
	

	public List findAll() {
		log.debug("finding all Whbuilding instances");
		try {
			String queryString = "from Whbuilding";
	         Query queryObject = getCurrentSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Whbuilding merge(Whbuilding detachedInstance) {
        log.debug("merging Whbuilding instance");
        try {
            Whbuilding result = (Whbuilding) getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Whbuilding instance) {
        log.debug("attaching dirty Whbuilding instance");
        try {
            getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Whbuilding instance) {
        log.debug("attaching clean Whbuilding instance");
        try {
                      	getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static WhbuildingDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (WhbuildingDAO) ctx.getBean("WhbuildingDAO");
	}
}