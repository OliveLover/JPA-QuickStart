package org.example.chapter02.biz.client;

import org.example.chapter02.biz.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmployeeServiceClient {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter03");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            // 직원 검색
            Employee findEmp = em.find(Employee.class, 1L);

            // 직원 이름 변경
            tx.begin();
            findEmp.setName("똘리");
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
