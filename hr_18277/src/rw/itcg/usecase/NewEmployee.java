/**
 * 
 */
package rw.itcg.usecase;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import rw.itcg.domain.Employee;
import rw.itcg.domain.Gender;
import rw.itcg.service.EmployeeService;

/**
 * @author NIYOMWUNGERI
 * @Date May 17, 2017
 */
@Component
@ManagedBean
public class NewEmployee {

	private boolean diable = true;
	private String empType;
	private Gender gender;
	private String managerId;
	@Autowired
	private EmployeeService empServ;

	public void activateManager() {
		if (empType.equals("1")) {
			diable = true;
		} else if (empType.equals("2")) {
			diable = false;
		} else {
			diable = false;
		}
	}

	public List<Employee> getManagers() {
		try {
			List<Employee> emp = new ArrayList<Employee>();
			for (Employee e : empServ.findAll()) {
				if (e.getManager().getNationalId().equals(null)) {
					emp.add(e);
				}
			}
			return emp;
		} catch (Exception e) {
			return null;
		}
	}

	public List<Employee> getEmplist() {
		List<Employee> tempEmp = new ArrayList<Employee>();
		for (Employee e : empServ.findAll()) {
			if (e == null) {
				tempEmp.add(e);
			} else if (e.getGender().toString().equals(Gender.MALE.toString())) {
				e.setFirstName("Mr. " + e.getFirstName());
				tempEmp.add(e);
			} else {
				e.setFirstName("Ms. " + e.getFirstName());
				tempEmp.add(e);
			}
		}
		return tempEmp;
	}

	public double calcNetsalary(Employee emp) {
		return 0.0;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getEmpType() {
		return empType;
	}

	public void setEmpType(String empType) {
		this.empType = empType;
	}

	public boolean isDiable() {
		return diable;
	}

	public void setDiable(boolean diable) {
		this.diable = diable;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

}
