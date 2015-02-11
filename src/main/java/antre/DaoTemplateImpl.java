package antre;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.jodah.typetools.TypeResolver;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public abstract class DaoTemplateImpl<T> {

	private static Logger LOGGER = LogManager.getLogger(DaoTemplateImpl.class);
	
	@Autowired
	protected SessionFactory sf;

	public Long saveWithS(T entity) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(entity);
		tx.commit();
		session.close();
		return getId(entity);
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public Long save(T entity) {
		Session session = sf.getCurrentSession();
		session.persist(entity);
		return getId(entity);
	}

	public Long saveOrUpdateWithS(T entity) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(entity);
		tx.commit();
		session.close();
		return getId(entity);
	}
	
	public Long saveOrUpdate(T entity) {
		Session session = sf.getCurrentSession();
		session.saveOrUpdate(entity);
		return getId(entity);
	}

	public Long save(T entity, Session session) {
		session.save(entity);
		return getId(entity);
	}

	private Long getId(T entity) {
		try {
			Method method = getClazz().getMethod("getId");
			Long id = (Long) method.invoke(entity);
			return id;
		} catch (Exception e) {
			throw new RuntimeException("Metoda " + getClazz().getCanonicalName() + ".getId() nie istnieje: ", e);
		}
	}

	public void updateWithS(T entity) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.update(entity);
		tx.commit();
		session.close();
	}
	@Transactional(propagation = Propagation.MANDATORY)
	public void update(T entity) {
		Session session = sf.getCurrentSession();
		session.update(entity);
	}

	public void update(T entity, Session session) {
		session.update(entity);
	}

	public void removeWithS(T entity) {
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
		session.close();
	}
	@Transactional(propagation = Propagation.MANDATORY)
	public void remove(T entity) {
		Session session = sf.getCurrentSession();
		session.delete(entity);
	}

	public void remove(T entity, Session session) {
		session.delete(entity);
	}

	//TODO
	@SuppressWarnings("unchecked")
	public T findByIdWithS(Long id) {
		try {
			Session session = sf.openSession();
			T entity = (T) session.get(getClazz(), id);
			session.close();
			if (entity == null) {
				return Null();
			}
			return entity;
		} catch (Throwable e) {
			LOGGER.error(this.getClass().getSimpleName(),e);
			return Null();
		}
	}

	@Transactional(propagation = Propagation.MANDATORY)
	public T findById(Long id) {
		Session session = sf.getCurrentSession();
		T entity = (T) session.get(getClazz(), id);
		if (entity == null) {
			return Null();
		}
		return entity;
	}
	
	@Transactional(propagation = Propagation.MANDATORY)
	public List<T> findByIds(List<Long> entityIds ) {
		Session session = sf.getCurrentSession();
		List<T> list = (List<T>)session.createCriteria(getClazz())
				.add(Restrictions.in("id", entityIds))
				.list();
		if(list == null) 
			return new ArrayList<T>();
		
		return list;
	}

	@SuppressWarnings("unchecked")
	public T findById(Long id, Session session) {

		T entity = (T) session.get(getClazz(), id);
		if (entity == null) {
			return Null();
		}
		return entity;

	}

	@SuppressWarnings("unchecked")
	protected Class<T> getClazz() {
		Class<?> c = TypeResolver.resolveRawArgument(this.getClass(), DaoTemplateImpl.class);
		return (Class<T>) c;
	}

	@SuppressWarnings("unchecked")
	public T Null() {
		Class<T> c = getClazz();

		try {
			Field field = c.getField("Null");
			return (T) (field.get(null));
		} catch (Exception e) {
			LOGGER.error(this.getClass().getSimpleName(),e);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public Collection<T> getAllWithS() {
		Session session = sf.openSession();
		List<T> list = (List<T>) session.createCriteria(getClazz()).list();
		session.close();
		if (list == null) {
			return new ArrayList<>();
		}
		return list;
	}
	
	public Collection<T> getAll() {
		Session session = sf.getCurrentSession();
		List<T> list = (List<T>) session.createCriteria(getClazz()).list();
		if (list == null) {
			return new ArrayList<>();
		}
		return list;
	}


	
	
	

}

