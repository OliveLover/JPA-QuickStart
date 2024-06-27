package org.example.chapter02.biz.client;

import org.example.chapter02.biz.domain.Employee;

import javax.persistence.*;

public class EmployeeServiceClient {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter03");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            // 직원 엔티티 등록
            Employee employee = new Employee();
            employee.setName("이름을 수정한 똘리");

            // 직원 엔티티 이름 수정
            tx.begin();
            em.persist(employee);
            tx.commit();

            for(int i = 0; i < 30; i++) {
                Thread.sleep(1000);
                System.out.println("다른 사용자가 데이터 수정중... " + i);
            }

            // 엔티티 REFRESH
            em.refresh(employee);
            System.out.println("갱신된 직원 정보 : " + employee.toString());

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
