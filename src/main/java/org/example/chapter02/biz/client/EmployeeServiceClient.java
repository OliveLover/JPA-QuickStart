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
            // 직원 엔티티 등록
            Employee employee = new Employee();
            employee.setName("둘리");

            tx.begin();
            em.persist(employee);
            tx.commit();

            // 모든 엔티티를 분리 상태로 전환시킨다.
            em.clear();

            // 직원 엔티티 이름 수정
            tx.begin();
            employee.setName("똘리");
            Employee mergedEmp = em.merge(employee);
            tx.commit();

            // 관리 상태 여부 확인
            System.out.println("employee 관리 : " + em.contains(employee));
            System.out.println("mergedEmp 관리 : " + em.contains(mergedEmp));
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
