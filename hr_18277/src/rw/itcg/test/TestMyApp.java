/**
 * 
 */
package rw.itcg.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import rw.itcg.domain.Employee;
import rw.itcg.service.EmployeeService;

/**
 * @author NIYOMWUNGERI
 * @Date May 17, 2017
 */
public class TestMyApp {

	public static void main(String args[]) {
		try {
			@SuppressWarnings("resource")
			ApplicationContext ct = new ClassPathXmlApplicationContext("classpath:rw/itcg/config/app-context.xml");
			EmployeeService empService = ct.getBean(EmployeeService.class);
			List<Employee> l = new ArrayList<Employee>();
			for (Employee emp : empService.findAll()) {
				if (emp.getManager() == null) {
					l.add(emp);
				}
			}
			for (Employee e : l) {
				String f = e.getFirstName();
				String ln = e.getLastName();
				System.out.println(f + " " + ln);
			}

		} catch (Exception e) {

		}

	}
}
