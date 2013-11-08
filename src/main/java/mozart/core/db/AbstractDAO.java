package mozart.core.db;

import java.util.List;

import mozart.core.exception.MozartException;
import mozart.core.pagination.FilterableDao;
import mozart.core.pagination.FilterableQuery;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.SessionFactoryUtils;

public abstract class AbstractDAO<T> implements FilterableDao {

	protected abstract Class<T> getModel();

	public final Session getSession() {
		return SessionFactoryUtils.getSession(HibernateUtil.getSessionFactory(), true);
	}

	@SuppressWarnings("unchecked")
	public List<T> loadAll() throws MozartException {
		Session session = getSession();
		try {
			session.beginTransaction();
			return session.createQuery(
			    "from " + getModel().getName() + " domain order by domain.id asc").list();
		} catch (HibernateException e) {
			throw new MozartException(
			                          e.getCause().getMessage(),
			                          SessionFactoryUtils.convertHibernateAccessException(e));
		}
	}

	@SuppressWarnings("unchecked")
	public T loadById(Long id) throws MozartException {
		Session session = getSession();
		try {
			Query query = session.createQuery("from " +
			                                  getModel().getName() +
			                                  " domain where domain.id =:id");
			query.setLong("id", id);
			return (T) query.uniqueResult();
		} catch (HibernateException e) {
			throw new MozartException(
			                          e.getCause().getMessage(),
			                          SessionFactoryUtils.convertHibernateAccessException(e));
		}
	}

	public Long save(T obj) throws MozartException {
		Session session = getSession();
		try {
			return (Long) session.save(obj);
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new MozartException(
			                          e.getCause().getMessage(),
			                          SessionFactoryUtils.convertHibernateAccessException(e));
		}
	}

	public void update(T obj) throws MozartException {
		Session session = getSession();
		try {
			session.update(obj);
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new MozartException(
			                          e.getCause().getMessage(),
			                          SessionFactoryUtils.convertHibernateAccessException(e));
		}
	}

	public void delete(T obj) throws MozartException {
		Session session = getSession();
		try {
			session.delete(obj);
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new MozartException(
			                          e.getCause().getMessage(),
			                          SessionFactoryUtils.convertHibernateAccessException(e));
		}
	}

	public Object execUnique(String query) throws MozartException {
		Session session = getSession();
		try {
			return session.createQuery(query).uniqueResult();
		} catch (HibernateException e) {
			throw new MozartException(
			                          e.getCause().getMessage(),
			                          SessionFactoryUtils.convertHibernateAccessException(e));
		}
	}

	public Object execUnique(String query, Object... parameters) throws MozartException {
		Session session = getSession();
		try {
			Query object = session.createQuery(query);
			setParameters(object, parameters);
			return object.uniqueResult();
		} catch (HibernateException e) {
			throw new MozartException(
			                          e.getCause().getMessage(),
			                          SessionFactoryUtils.convertHibernateAccessException(e));
		}
	}

	public Object execList(String query) throws MozartException {
		Session session = getSession();
		try {
			return session.createQuery(query).list();
		} catch (HibernateException e) {
			throw new MozartException(
			                          e.getCause().getMessage(),
			                          SessionFactoryUtils.convertHibernateAccessException(e));
		}
	}

	public Object execList(String query, Object... parameters) throws MozartException {
		Session session = getSession();
		try {
			Query object = session.createQuery(query);
			setParameters(object, parameters);
			return object.list();
		} catch (HibernateException e) {
			throw new MozartException(
			                          e.getCause().getMessage(),
			                          SessionFactoryUtils.convertHibernateAccessException(e));
		}
	}

	public Object execUnique(Query query) throws MozartException {
		try {
			return query.uniqueResult();
		} catch (HibernateException e) {
			throw new MozartException(
			                          e.getCause().getMessage(),
			                          SessionFactoryUtils.convertHibernateAccessException(e));
		}
	}

	public Object execList(Query query) throws MozartException {
		try {
			return query.list();
		} catch (HibernateException e) {
			throw new MozartException(
			                          e.getCause().getMessage(),
			                          SessionFactoryUtils.convertHibernateAccessException(e));
		}
	}

	@Override
	public List<?> filter(FilterableQuery query) throws MozartException {
		query.setFilterableDao(this);
		query.setSession(getSession());
		return query.execute();
	}

	// @Override
	// public Long count(FilterableQuery query) {
	// query.setFilterableDao(this);
	// return query.count();
	// }

	private void setParameters(Query query, Object... parameters) throws MozartException {
		if (parameters == null || parameters.length == 0) {
			return;
		}

		for (int i = 0; i < parameters.length; i++) {
			if (parameters[i] == null) {
				throw new MozartException("Query Parameter at position " + (i + 1) + " is null");
			}
			query.setParameter(i, parameters[i]);
		}
	}
}