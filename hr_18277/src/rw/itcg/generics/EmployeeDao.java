package rw.itcg.generics;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import rw.itcg.domain.Employee;

/**
 * @author NIYOMWUNGERI
 * @Date May 17, 2017
 */
@Repository
public class EmployeeDao extends GenericDao<Employee> {
	
	public Employee findById(String nid) {
		Query query = sessionfactory().createQuery("select u from Employee u where u.nationalId = :nationalId");
		query.setParameter("nationalId", nid);
		return (Employee) query.uniqueResult();
	}
}
