package org.example.chapter08.biz.persistence;

import org.example.chapter08.biz.domain.Employee;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class EmployeeRepository {

    @PersistenceContext
    private EntityManager em;

    public void insertEmployee(Employee employee) {
        System.out.println("===> JPA로 insertEmployee() 기능 처리");
        em.persist(employee);
    }

    public void updateEmployee(Employee employee) {
        System.out.println("===> JPA로 updateEmployee() 기능 처리");
        em.merge(employee);
    }

    public void deleteEmployee(Employee employee) {
        System.out.println("===> JPA로 deleteEmployee() 기능 처리");
        em.remove(em.find(Employee.class, employee.getId()));
    }

    public Employee getEmployee(Employee employee) {
        System.out.println("===> JPA로 getEmployee() 기능 처리");
        return (Employee) em.find(Employee.class, employee.getId());
    }

    public List<Employee> getEmployeeList(Employee employee) {
        System.out.println("===> JPA로 getEmployeeList() 기능 처리");
        return em.createQuery("FROM Employee emp JOIN FETCH emp.dept dept "
                + "ORDER BY emp.id DESC")
                .getResultList();
    }
}
