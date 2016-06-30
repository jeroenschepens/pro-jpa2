package nl.jeroenschepens.projpa;

import nl.jeroenschepens.projpa.domain.Employee;
import nl.jeroenschepens.projpa.service.EmployeeService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeeTest {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("EmployeeService");
        EntityManager em = emf.createEntityManager();
        EmployeeService service = new EmployeeService(em);
        // create and persist an employee
        em.getTransaction().begin();
        Employee emp = service.createEmployee(158, "John Doe", 45000);
        em.getTransaction().commit();
        System.out.println("Persisted " + emp);
        // find a specific employee
        emp = service.findEmployee(158);
        System.out.println("Found " + emp);
        // find all employees
        List<Employee> emps = service.findAllEmployees();
        for (Employee e : emps) {
            System.out.println("Found employee: " + e);
        }
        // update the employee
        em.getTransaction().begin();
        emp = service.raiseEmployeeSalary(158, 1000);
        em.getTransaction().commit();
        System.out.println("Updated " + emp);
        // remove an employee
        em.getTransaction().begin();
        service.removeEmployee(158);
        em.getTransaction().commit();
        System.out.println("Removed Employee 158");
        // close the EM and EMF when done
        em.close();
        emf.close();
    }
}