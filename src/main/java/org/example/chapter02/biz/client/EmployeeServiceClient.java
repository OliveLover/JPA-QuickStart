package org.example.chapter02.biz.client;

import org.example.chapter02.biz.domain.Employee;

import javax.persistence.*;

public class EmployeeServiceClient {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter03");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            Employee employee = new Employee();
            employee.setName("둘리");

            // 직원 등록
            tx.begin();
            em.persist(employee);
            tx.commit();

            // 직원 검색
            Employee findEmp1 = em.find(Employee.class, 1L);
            Employee findEmp2 = em.find(Employee.class, 1L);

            // 객체의 동일성 비교
            if(findEmp1 == findEmp2) {
                System.out.println("검색된 두 객체는 동일한 객체다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
    }
}
