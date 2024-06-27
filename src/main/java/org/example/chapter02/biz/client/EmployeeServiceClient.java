package org.example.chapter02.biz.client;

import org.example.chapter02.biz.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class EmployeeServiceClient {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter03");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            // 직원 등록
            tx.begin();
            for (int i = 1; i <= 10; i++) {
                Employee employee = new Employee();
                employee.setName("직원-" + i);
                em.persist(employee);
            }
            tx.commit();

            // 직원 목록 조회
            String jpql = "SELECT e FROM Employee e ORDER BY e.id DESC";
            List<Employee> employeeList =
                    em.createQuery(jpql, Employee.class).getResultList();
            for (Employee employee : employeeList) {
                System.out.println("--> " + employee.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
