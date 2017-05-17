/**
 * 
 */
package rw.itcg.generics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author NIYOMWUNGERI
 * @Date May 17, 2017
 */
public class GenericDao<G> {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session sessionfactory() {
		return sessionFactory.getCurrentSession();
	}

	private Class<G> type;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public GenericDao() {
		Type g = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) g;
		type = (Class) pt.getActualTypeArguments()[0];
	}

	public G save(G g) {
		sessionfactory().save(g);
		return g;
	}

	public G delete(G g) {
		sessionfactory().delete(g);
		return g;
	}

	public Long countRows() {
		return (Long) sessionfactory().createCriteria(this.type).setProjection(Projections.rowCount()).uniqueResult();
	}

	public G update(G g) {
		sessionfactory().update(g);
		return g;
	}

	@SuppressWarnings("unchecked")
	public List<G> findAll() {
		return sessionfactory().createCriteria(this.type).list();
	}
}
