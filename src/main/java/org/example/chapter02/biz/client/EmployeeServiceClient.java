package org.example.chapter02.biz.client;

import org.example.chapter02.biz.domain.Employee;

import javax.persistence.*;

public class EmployeeServiceClient {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter03");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            // 직원 엔티티 검색
            Employee findEmp = em.getReference(Employee.class, 1L);
            System.out.println("검색된 직원의 이름 : " + findEmp.getName());
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
