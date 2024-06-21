package org.example.chapter02.biz.client;

import org.example.chapter02.biz.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class EmployeeServiceClient {
    public static void main(String[] args) {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter02");

        // 엔티티 매니저 생성
        EntityManager em = emf.createEntityManager();

        // 엔티티 트랜잭션 생성
        EntityTransaction tx = em.getTransaction();

        try {
            // 엔티티 생성 및 초기화
            Employee employee = new Employee();
//            employee.setId(1L);
            employee.setName("둘리");

            // 회원 등록 요청
            tx.begin();
            System.out.println("등록 전 id : " + employee.getId());
            em.persist(employee);

            for (int i = 0; i < 30; i++) {
                Thread.sleep(1000);
                System.out.println("ZZZ...");
            }

            System.out.println("등록 후 id : " + employee.getId());

            tx.commit();

            // 등록한 회원 검색
            Employee findEmployee = em.find(Employee.class, 1L);
            System.out.println("검색한 회원 정보");
            System.out.println(findEmployee.toString());

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
