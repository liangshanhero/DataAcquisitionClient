package cn.edu.scau.cmi.wuweijie.entity.client;

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
 * A data access object (DAO) providing persistence and search support for Celve
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see cn.edu.scau.cmi.wuweijie.entity.client.Celve
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class CelveDAO {
	private static final Logger log = LoggerFactory.getLogger(CelveDAO.class);
	// property constants
	public static final String SITE = "site";
	public static final String NAME = "name";
	public static final String START_HH = "startHh";
	public static final String START_MM = "startMm";
	public static final String SHUT_HH = "shutHh";
	public static final String SHUT_MM = "shutMm";
	public static final String X1 = "x1";
	public static final String X2 = "x2";
	public static final String X3 = "x3";
	public static final String X4 = "x4";
	public static final String X5 = "x5";
	public static final String X6 = "x6";
	public static final String X7 = "x7";
	public static final String OPERATINGTEMP = "operatingtemp";
	public static final String SHUT_TEMP = "shutTemp";
	public static final String MAXLEVEL = "maxlevel";
	public static final String MINLEVEL = "minlevel";
	public static final String CLSX = "clsx";

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

	public void save(Celve transientInstance) {
		log.debug("saving Celve instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Celve persistentInstance) {
		log.debug("deleting Celve instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Celve findById(java.lang.Integer id) {
		log.debug("getting Celve instance with id: " + id);
		try {
			Celve instance = (Celve) getCurrentSession().get("cn.edu.scau.cmi.wuweijie.entity.client.Celve", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Celve> findByExample(Celve instance) {
		log.debug("finding Celve instance by example");
		try {
			List<Celve> results = (List<Celve>) getCurrentSession()
					.createCriteria("cn.edu.scau.cmi.wuweijie.entity.client.Celve").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Celve instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Celve as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Celve> findBySite(Object site) {
		return findByProperty(SITE, site);
	}

	public List<Celve> findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List<Celve> findByStartHh(Object startHh) {
		return findByProperty(START_HH, startHh);
	}

	public List<Celve> findByStartMm(Object startMm) {
		return findByProperty(START_MM, startMm);
	}

	public List<Celve> findByShutHh(Object shutHh) {
		return findByProperty(SHUT_HH, shutHh);
	}

	public List<Celve> findByShutMm(Object shutMm) {
		return findByProperty(SHUT_MM, shutMm);
	}

	public List<Celve> findByX1(Object x1) {
		return findByProperty(X1, x1);
	}

	public List<Celve> findByX2(Object x2) {
		return findByProperty(X2, x2);
	}

	public List<Celve> findByX3(Object x3) {
		return findByProperty(X3, x3);
	}

	public List<Celve> findByX4(Object x4) {
		return findByProperty(X4, x4);
	}

	public List<Celve> findByX5(Object x5) {
		return findByProperty(X5, x5);
	}

	public List<Celve> findByX6(Object x6) {
		return findByProperty(X6, x6);
	}

	public List<Celve> findByX7(Object x7) {
		return findByProperty(X7, x7);
	}

	public List<Celve> findByOperatingtemp(Object operatingtemp) {
		return findByProperty(OPERATINGTEMP, operatingtemp);
	}

	public List<Celve> findByShutTemp(Object shutTemp) {
		return findByProperty(SHUT_TEMP, shutTemp);
	}

	public List<Celve> findByMaxlevel(Object maxlevel) {
		return findByProperty(MAXLEVEL, maxlevel);
	}

	public List<Celve> findByMinlevel(Object minlevel) {
		return findByProperty(MINLEVEL, minlevel);
	}

	public List<Celve> findByClsx(Object clsx) {
		return findByProperty(CLSX, clsx);
	}

	public List findAll() {
		log.debug("finding all Celve instances");
		try {
			String queryString = "from Celve";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Celve merge(Celve detachedInstance) {
		log.debug("merging Celve instance");
		try {
			Celve result = (Celve) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Celve instance) {
		log.debug("attaching dirty Celve instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Celve instance) {
		log.debug("attaching clean Celve instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CelveDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CelveDAO) ctx.getBean("CelveDAO");
	}
}