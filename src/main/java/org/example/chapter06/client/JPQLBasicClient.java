package org.example.chapter06.client;

import org.example.chapter06.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class JPQLBasicClient {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter06");

        try {
            dataInsert(emf);
            dataSelect(emf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            emf.close();
        }
    }

    private static void dataSelect(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        // 1번 직원 검색
        Employee findEmp1 = em.find(Employee.class, 1L);

        // 1번 직원 검색
        Employee findEmp2 = em.find(Employee.class, 1L);

        if (findEmp1 == findEmp2) {
            System.out.println("두 객체의 주소는 동일하다.");
        }

        em.close();
    }

    private static void dataInsert(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // 10 명의 직원 정보 등록
        for (int i = 1; i <= 10; i++) {
            Employee employee = new Employee();
            employee.setName("직원 " + i);
            employee.setMailId("anti-corona" + i);
            employee.setDeptName("개발부");
            employee.setSalary(12700.00 * i);
            employee.setStartDate(new Date());
            employee.setTitle("사원");
            employee.setCommissionPct(15.00);
            em.persist(employee);
        }

        em.getTransaction().commit();
        em.close();
    }
}
