/**
 * 
 */
package rw.itcg.usecase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

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
	private Employee employee = new Employee();
	private String selectedSex;
	@Autowired
	private EmployeeService empServ;

	public String gotoIndex() {
		return "index";
	}

	public String createEmployee() {
		try {
			NewEmployee ne = new NewEmployee();
			int age = ne.calculateAge(employee);
			if (age >= 18) {
				if (empType.equals("1")) {
					double bonus = employee.getGrossSalary() * 0.3;
					employee.setBonus(bonus);
					employee.setManager(null);
					employee.setGender(Gender.valueOf(selectedSex));
					empServ.saveEmployee(employee);
					FacesContext.getCurrentInstance().addMessage(null,
							new FacesMessage(FacesMessage.SEVERITY_INFO, "Manager Created Successfully", null));

					return "viewEmp";
				} else {
					Employee officer = empServ.findByNid(managerId);
					if (officer != null) {
						employee.setManager(officer);
						employee.setBonus(0);
						employee.setGender(Gender.valueOf(selectedSex));
						empServ.saveEmployee(employee);
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage(FacesMessage.SEVERITY_INFO, "Officer Created Successfully", null));
						return "viewEmp";
					}

				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Age must be greater than 18", null));
				return "index";
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error:" + e.getMessage(), null));
		}
		return "index";

	}

	public void activateManager() {
		if (empType.equals("1")) {
			diable = true;
		} else if (empType.equals("2")) {
			diable = false;
		} else {
			diable = false;
		}
	}

	public int calculateAge(Employee emp) {

		Calendar currentDate = Calendar.getInstance();
		currentDate.setTime(new Date());
		int currentYear = currentDate.get(Calendar.YEAR);

		Calendar bDate = Calendar.getInstance();
		bDate.setTime(emp.getDateOfBirth());
		int bYear = bDate.get(Calendar.YEAR);

		return (currentYear - bYear);

	}

	public Gender[] getSex() {
		return Gender.values();
	}

	public List<Employee> getManagers() {
		try {
			List<Employee> l = new ArrayList<Employee>();
			for (Employee emp : empServ.findAll()) {
				if (emp.getManager() == null) {
					l.add(emp);
				}
			}
			return l;
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
		double totalEarnings = (emp.getGrossSalary() + emp.getBonus());
		double taxes = totalEarnings * 0.2;
		return (totalEarnings - taxes);
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getSelectedSex() {
		return selectedSex;
	}

	public void setSelectedSex(String selectedSex) {
		this.selectedSex = selectedSex;
	}

	public void testGender() {
		System.out.println(selectedSex);
	}
}
