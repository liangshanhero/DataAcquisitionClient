package cn.edu.scau.cmi.wuweijie.entity.server;

import java.sql.Timestamp;
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
 * A data access object (DAO) providing persistence and search support for
 * Whstrategydetail entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see cn.edu.scau.cmi.wuweijie.entity.server.Whstrategydetail
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class WhstrategydetailDAO {
	private static final Logger log = LoggerFactory.getLogger(WhstrategydetailDAO.class);
	// property constants
	public static final String MAX = "max";
	public static final String MIN = "min";

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

	public void save(Whstrategydetail transientInstance) {
		log.debug("saving Whstrategydetail instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Whstrategydetail persistentInstance) {
		log.debug("deleting Whstrategydetail instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Whstrategydetail findById(java.lang.Long id) {
		log.debug("getting Whstrategydetail instance with id: " + id);
		try {
			Whstrategydetail instance = (Whstrategydetail) getCurrentSession()
					.get("cn.edu.scau.cmi.wuweijie.entity.server.Whstrategydetail", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Whstrategydetail> findByExample(Whstrategydetail instance) {
		log.debug("finding Whstrategydetail instance by example");
		try {
			List<Whstrategydetail> results = (List<Whstrategydetail>) getCurrentSession()
					.createCriteria("cn.edu.scau.cmi.wuweijie.entity.server.Whstrategydetail").add(create(instance))
					.list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Whstrategydetail instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Whstrategydetail as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Whstrategydetail> findByMax(Object max) {
		return findByProperty(MAX, max);
	}

	public List<Whstrategydetail> findByMin(Object min) {
		return findByProperty(MIN, min);
	}

	public List findAll() {
		log.debug("finding all Whstrategydetail instances");
		try {
			String queryString = "from Whstrategydetail";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Whstrategydetail merge(Whstrategydetail detachedInstance) {
		log.debug("merging Whstrategydetail instance");
		try {
			Whstrategydetail result = (Whstrategydetail) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Whstrategydetail instance) {
		log.debug("attaching dirty Whstrategydetail instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Whstrategydetail instance) {
		log.debug("attaching clean Whstrategydetail instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static WhstrategydetailDAO getFromApplicationContext(ApplicationContext ctx) {
		return (WhstrategydetailDAO) ctx.getBean("WhstrategydetailDAO");
	}
}