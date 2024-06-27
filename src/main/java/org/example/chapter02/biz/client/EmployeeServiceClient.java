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

        // 엔티티 트랜잭션 생성
        EntityTransaction tx = em.getTransaction();

        try {
            // 회원 등록 요청
            Employee employee = new Employee();
            employee.setName("둘리");

            // 트랜잭션 시작
            tx.begin();

            // 직원 등록 --> 관리 상태로 전환
            em.persist(employee);

            if(em.contains(employee)) {
                System.out.println(employee.toString() + " MANAGED");
            }

            // 1번 직원 엔티티를 분리 상태로 전환
            em.detach(employee);

            if(!em.contains(employee)) {
                System.out.println(employee.toString() + " DETACHED");
            }

            // 분리 상태의 엔티티 수정
            employee.setName("이름 수정");

            tx.commit();

            System.out.println("최종 직원 정보 : " + employee.toString());
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
