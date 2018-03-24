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
 * A data access object (DAO) providing persistence and search support for
 * Syncdata entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see cn.edu.scau.cmi.wuweijie.entity.client.Syncdata
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class SyncdataDAO {
	private static final Logger log = LoggerFactory.getLogger(SyncdataDAO.class);
	// property constants
	public static final String BUILD_NAME = "buildName";
	public static final String DEVICE_ID = "deviceId";
	public static final String PARA_NAME = "paraName";
	public static final String IVALUE = "ivalue";
	public static final String IS_IO = "isIo";
	public static final String UPLOAD = "upload";

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

	public void save(Syncdata transientInstance) {
		log.debug("saving Syncdata instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Syncdata persistentInstance) {
		log.debug("deleting Syncdata instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Syncdata findById(java.lang.Integer id) {
		log.debug("getting Syncdata instance with id: " + id);
		try {
			Syncdata instance = (Syncdata) getCurrentSession().get("cn.edu.scau.cmi.wuweijie.entity.Syncdata", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Syncdata> findByExample(Syncdata instance) {
		log.debug("finding Syncdata instance by example");
		try {
			List<Syncdata> results = (List<Syncdata>) getCurrentSession()
					.createCriteria("cn.edu.scau.cmi.wuweijie.entity.Syncdata").add(create(instance)).list();
			log.debug("find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Syncdata instance with property: " + propertyName + ", value: " + value);
		try {
			String queryString = "from Syncdata as model where model." + propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Syncdata> findByBuildName(Object buildName) {
		return findByProperty(BUILD_NAME, buildName);
	}

	public List<Syncdata> findByDeviceId(Object deviceId) {
		return findByProperty(DEVICE_ID, deviceId);
	}

	public List<Syncdata> findByParaName(Object paraName) {
		return findByProperty(PARA_NAME, paraName);
	}

	public List<Syncdata> findByIvalue(Object ivalue) {
		return findByProperty(IVALUE, ivalue);
	}

	public List<Syncdata> findByIsIo(Object isIo) {
		return findByProperty(IS_IO, isIo);
	}

	public List<Syncdata> findByUpload(Object upload) {
		return findByProperty(UPLOAD, upload);
	}

	public List findAll() {
		log.debug("finding all Syncdata instances");
		try {
			String queryString = "from Syncdata";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Syncdata merge(Syncdata detachedInstance) {
		log.debug("merging Syncdata instance");
		try {
			Syncdata result = (Syncdata) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Syncdata instance) {
		log.debug("attaching dirty Syncdata instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Syncdata instance) {
		log.debug("attaching clean Syncdata instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SyncdataDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SyncdataDAO) ctx.getBean("SyncdataDAO");
	}
}