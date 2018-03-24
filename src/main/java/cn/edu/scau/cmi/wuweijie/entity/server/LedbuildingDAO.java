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
 	* A data access object (DAO) providing persistence and search support for Ledbuilding entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see cn.edu.scau.cmi.wuweijie.entity.server.Ledbuilding
  * @author MyEclipse Persistence Tools 
 */
    @Transactional   
public class LedbuildingDAO  {
	     private static final Logger log = LoggerFactory.getLogger(LedbuildingDAO.class);
		//property constants
	public static final String NAME = "name";
	public static final String WELL = "well";
	public static final String STOREY = "storey";



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
    
    public void save(Ledbuilding transientInstance) {
        log.debug("saving Ledbuilding instance");
        try {
            getCurrentSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Ledbuilding persistentInstance) {
        log.debug("deleting Ledbuilding instance");
        try {
            getCurrentSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Ledbuilding findById( java.lang.Long id) {
        log.debug("getting Ledbuilding instance with id: " + id);
        try {
            Ledbuilding instance = (Ledbuilding) getCurrentSession()
                    .get("cn.edu.scau.cmi.wuweijie.entity.server.Ledbuilding", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List<Ledbuilding> findByExample(Ledbuilding instance) {
        log.debug("finding Ledbuilding instance by example");
        try {
            List<Ledbuilding> results = (List<Ledbuilding>) getCurrentSession() .createCriteria("cn.edu.scau.cmi.wuweijie.entity.server.Ledbuilding").add( create(instance) ).list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Ledbuilding instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Ledbuilding as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getCurrentSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List<Ledbuilding> findByName(Object name
	) {
		return findByProperty(NAME, name
		);
	}
	
	public List<Ledbuilding> findByWell(Object well
	) {
		return findByProperty(WELL, well
		);
	}
	
	public List<Ledbuilding> findByStorey(Object storey
	) {
		return findByProperty(STOREY, storey
		);
	}
	

	public List findAll() {
		log.debug("finding all Ledbuilding instances");
		try {
			String queryString = "from Ledbuilding";
	         Query queryObject = getCurrentSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Ledbuilding merge(Ledbuilding detachedInstance) {
        log.debug("merging Ledbuilding instance");
        try {
            Ledbuilding result = (Ledbuilding) getCurrentSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Ledbuilding instance) {
        log.debug("attaching dirty Ledbuilding instance");
        try {
            getCurrentSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Ledbuilding instance) {
        log.debug("attaching clean Ledbuilding instance");
        try {
                      	getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
          	            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }

	public static LedbuildingDAO getFromApplicationContext(ApplicationContext ctx) {
    	return (LedbuildingDAO) ctx.getBean("LedbuildingDAO");
	}
}