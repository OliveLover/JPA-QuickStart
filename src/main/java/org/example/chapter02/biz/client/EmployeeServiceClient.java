package org.example.chapter02.biz.client;

import org.example.chapter02.biz.domain.Employee;
import org.example.chapter02.biz.domain.EmployeeId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmployeeServiceClient {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter02");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();

            // 회원 등록 요청
            EmployeeId empId = new EmployeeId(1L, "guest123");

            Employee employee = new Employee();
            employee.setEmpId(empId);
            employee.setName("둘리");
            em.persist(employee);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
