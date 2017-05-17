package rw.itcg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rw.itcg.domain.Employee;
import rw.itcg.generics.EmployeeDao;

@Service
public class EmployeeService extends TransactionAware {

	@Autowired
	private EmployeeDao empDao;

	public void saveEmployee(Employee emp) {
		empDao.save(emp);
	}

	public Employee findByNid(String nid) {
		return empDao.findById(nid);
	}
	
	public List<Employee> findAll(){
		return empDao.findAll();
	}
}
