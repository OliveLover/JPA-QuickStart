package org.example.chapter02.biz.client;

import org.example.chapter02.biz.domain.Employee;
import org.example.chapter02.biz.domain.EmployeeId;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmployeeServiceClient {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter03");
        EntityManager em = emf.createEntityManager();

        try {
            // 회원 등록 요청
            Employee employee = new Employee();
            employee.setName("둘리");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
