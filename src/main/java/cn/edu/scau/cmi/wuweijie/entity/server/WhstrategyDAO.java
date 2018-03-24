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
 * A data access object (DAO) providing persistence and search support for
 * Whstrategy entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.edu.scau.cmi.wuweijie.entity.server.Whstrategy
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class WhstrategyDAO {
	private static final Logger log = LoggerFactory.getLogger(WhstrategyDAO.class);
	// property constants
	public static final String ENABLE = "enable";
	public static final String REMARK = "remark";
	public static final String NAME = "name";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Whstrategy transientInstance) {
		log.debug("saving Whstrategy instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Whstrategy persistentInstance) {
		log.debug("deleting Whstrategy instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Whstrategy findById(java.lang.Long id) {
		log.debug("getting Whstrategy instance with id: " + id);
		try {
			Whstrategy instance = (Whstrategy) getCurrentSession()
					.get("cn.edu.scau.cmi.wuweijie.entity.server.Whstrategy", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Whstrategy> findByExample(Whstrategy instance) {
		log.debug("finding Whstrategy instance by example");
		try {
			List<Whstrategy> results = (List<Whstrategy>) getCurrentSession()
					.createCriteria("cn.edu.scau.cmi.wuweijie.entity.server.Whstrategy").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Whstrategy instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Whstrategy as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Whstrategy> findByEnable(Object enable) {
		return findByProperty(ENABLE, enable);
	}

	public List<Whstrategy> findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List<Whstrategy> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findAll() {
		log.debug("finding all Whstrategy instances");
		try {
			String queryString = "from Whstrategy";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Whstrategy merge(Whstrategy detachedInstance) {
		log.debug("merging Whstrategy instance");
		try {
			Whstrategy result = (Whstrategy) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Whstrategy instance) {
		log.debug("attaching dirty Whstrategy instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Whstrategy instance) {
		log.debug("attaching clean Whstrategy instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static WhstrategyDAO getFromApplicationContext(ApplicationContext ctx) {
		return (WhstrategyDAO) ctx.getBean("WhstrategyDAO");
	}
}