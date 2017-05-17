/**
 * 
 */
package rw.itcg.test;

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
			List<Employee> em = empService.findAll();

			for (Employee e : em) {
				if (e.getManager().getNationalId().equals(null)) {
					System.out.println(e.getManager().getFirstName());
				}

			}

			System.out.println("success");
		} catch (Exception e) {

		}

	}
}
