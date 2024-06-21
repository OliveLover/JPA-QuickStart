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
            // 회원 등록 요청
            EmployeeId empId = new EmployeeId(1L, "guest123");
            Employee findEmployee = em.find(Employee.class, empId);
            System.out.println("검색된 직원 정보 : " + findEmployee.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
