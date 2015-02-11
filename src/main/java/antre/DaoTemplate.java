package antre;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;




public abstract class DaoTemplate<T> { 
	private static final Logger LOGGER = LogManager.getLogger(DaoTemplate.class);
	@Autowired
	protected SessionFactory sf;

	public Long saveWithS(T entity){
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.persist(entity);  
		tx.commit();
		session.close();
		return getId(entity);
	} 
	
	@Transactional(propagation = Propagation.MANDATORY)
	public Long save(T entity){
		Session session = sf.getCurrentSession();
		session.persist(entity);  
		return getId(entity);
	} 

	public Long save(T entity, Session session){
		session.save(entity); 
		return getId(entity);
	} 

	private Long getId(T entity) {
		try {
			Method method = getClazz().getMethod("getId");
			Long id = (Long)method.invoke(entity);
			return id;
		} catch (Exception e) {  
			throw new RuntimeException("Metoda "+getClazz().getCanonicalName()+".getId() nie istnieje: ",e);
		}   
	}
	@Transactional(propagation = Propagation.MANDATORY)
	public void updateWithCS(T entity){ 
		Session session = sf.getCurrentSession();
		session.update(entity);
	}

	public void update(T entity){ 
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.update(entity);
		tx.commit();
		session.close();
	}

	public void update(T entity, Session session){ 
		session.update(entity);
	}
	public void remove(T entity){
		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		tx.commit();
		session.close();
	}

	public void remove(T entity, Session session){
		session.delete(entity);
	}

	//TODO
	@SuppressWarnings("unchecked")
	public T findById(Long id){
		try{
			Session session = sf.openSession();
			T entity = (T)session.get(getClazz(), id);
			session.close();
			if(entity == null) 
				return Null(); 
			return entity; 
		}
		catch(Throwable e){
			LOGGER.error(this.getClass().getSimpleName(),e);
			return Null(); 
		}
	}
	@SuppressWarnings("unchecked")
	public T testFind(Long id){
			Session session = sf.getCurrentSession();
			T entity = (T)session.get(getClazz(), id);
			if(entity == null) 
				return Null(); 
			return entity; 
		
		
	}


	@SuppressWarnings("unchecked")
	public T findById(Long id, Session session){

		T entity = (T)session.get(getClazz(), id); 
		if(entity == null) 
			return Null(); 
		return entity; 

	}

	@SuppressWarnings("unchecked")
	protected Class<T> getClazz() { 
		Class<?> c = TypeResolver.resolveArgument(this.getClass(), DaoTemplate.class);
		return (Class<T>)c;
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

	public Collection<T> getAll(Session session){
		List<T> list = (List<T>)session.createCriteria(getClazz()).list();
		if(list == null) 
			return new ArrayList<T>();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<T> getAll(){
		Session session = sf.openSession();
		List<T> list = (List<T>)session.createCriteria(getClazz()).list();
		session.close();
		if(list == null) 
			return new ArrayList<T>();
		return list;
	}

	

}

