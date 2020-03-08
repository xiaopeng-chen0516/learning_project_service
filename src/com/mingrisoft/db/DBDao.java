package com.mingrisoft.db;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.mingrisoft.bean.Commodity;
import com.mingrisoft.bean.GoodDetails;
import com.mingrisoft.bean.User;

/**
 * Create by lyan on 5/5/17
 * 
 * @author Administrator
 *
 */
public class DBDao {

	/**
	 * 声明
	 */
	private SessionFactory sessionFactory;

	/**
	 * 初始化
	 */
	public DBDao() {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	/**
	 * 插入数据
	 */
	public String insert(Object object) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(object);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return "插入数据失败！";
		} finally {
			session.close();
		}
		return "插入数据成功！";
	}

	/**
	 * 关闭
	 */
	public void close() {
		if (sessionFactory.isOpen()) {
			sessionFactory.close();
		}
	}

	/**
	 * 返回指定表中所有的数据
	 * 
	 * @param tableName
	 * @param clazz
	 * @return
	 */
	public <T> List<T> selectAll(String tableName, Class<T> clazz) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Query<T> query = session.createQuery("from " + tableName, clazz);
			session.getTransaction().commit();
			return query.getResultList();// 返回查询结果集
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return null;
		} finally {
			session.close();
		}
	}

	/**
	 * 返回指定表的总数据条数
	 * 
	 * @param tableName
	 * @return
	 */
	public long selectCount(String tableName) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "select count(*) from " + tableName;
			Long count = (Long) (session.createQuery(hql)).uniqueResult();
			session.getTransaction().commit();
			return count;// 返回查询结果集
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return 0;
		} finally {
			session.close();
		}
	}

	/**
	 * 分页查询返回指定范围的数据
	 * 
	 * @param tableName
	 * @param clazz
	 * @param startIndex
	 * @param rowCount
	 * @return
	 */
	public <T> List<T> selectPage(String tableName, Class<T> clazz, int startIndex, int rowCount) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Query<T> query = session.createQuery("from " + tableName, clazz);
			query.setFirstResult(startIndex).setMaxResults(rowCount);
			session.getTransaction().commit();
			return query.getResultList();// 返回查询结果集
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return null;
		} finally {
			session.close();
		}
	}

	/**
	 * 分页查询返回指定范围的数据
	 * 
	 * @param tableName
	 * @param clazz
	 * @param startIndex
	 * @param rowCount
	 * @return
	 */
	public List<Commodity> selectType(String type) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "from Commodity comm where comm.classification =:type";
			Query<Commodity> query = session.createQuery(hql, Commodity.class);
			query.setParameter("type", type);
			session.getTransaction().commit();
			return query.getResultList();// 返回查询结果集
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return null;
		} finally {
			session.close();
		}
	}

	/**
	 * 分页查询返回指定范围的数据
	 * 
	 * @param tableName
	 * @param clazz
	 * @param startIndex
	 * @param rowCount
	 * @return
	 */
	public List<Commodity> selectShop(String shop) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "from Commodity comm where comm.typeName =:shop";
			Query<Commodity> query = session.createQuery(hql, Commodity.class);
			query.setParameter("shop", shop);
			session.getTransaction().commit();
			return query.getResultList();// 返回查询结果集
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return null;
		} finally {
			session.close();
		}
	}

	/**
	 * 条件查询
	 */
	public GoodDetails getGoodDetails(String id) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			Query<GoodDetails> query = session.createQuery("from GoodDetails details where details.id =:id",
					GoodDetails.class);
			query.setParameter("id", id);
			session.getTransaction().commit();
			return query.getSingleResult();// 返回查询结果集
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return null;
		} finally {
			session.close();
		}
	}

	public List<String> selectType() {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			String hql = "select comm.typeName from Commodity comm group by comm.typeName";
			Query<String> query = session.createQuery(hql, String.class);
			session.getTransaction().commit();
			return query.getResultList();// 返回查询结果集
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return null;
		} finally {
			session.close();
		}
	}

	public List<User> login(String user) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Transaction transaction=session.getTransaction();
		System.out.println("登录的用户名："+user);
		try {
			String hql = "from User user WHERE user.name=:name";
			Query<User> query = session.createQuery(hql, User.class);
			query.setParameter("name", user);
			transaction.commit();
			return query.getResultList();
		} catch (HibernateException e) {
			e.printStackTrace();
			transaction.rollback();
			return null;
		} finally {
			session.close();
		}

	}
}
