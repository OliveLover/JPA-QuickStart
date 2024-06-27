package org.example.chapter02.biz.client;

import org.example.chapter02.biz.domain.Employee;

import javax.persistence.*;

public class EmployeeServiceClient {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter03");
        EntityManager em = emf.createEntityManager();
        em.setFlushMode(FlushModeType.COMMIT);
        EntityTransaction tx = em.getTransaction();

        try {
            // 직원 엔티티 등록
            Employee employee = new Employee();
            employee.setId(1L);
            employee.setName("둘리");

            tx.begin();
            em.merge(employee);
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
