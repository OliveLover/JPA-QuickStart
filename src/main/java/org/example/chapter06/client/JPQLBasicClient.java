package org.example.chapter06.client;

import org.example.chapter06.domain.Employee;
import org.example.chapter06.domain.EmployeeSalaryData;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

        // JPQL
        String jpql = "SELECT id, name, title, deptName, salary " +
                "FROM Employee WHERE id = ?1 AND name = ?2";

        // JPQL 전송
        Query query = em.createQuery(jpql);
        query.setParameter(1, 1L);
        query.setParameter(2, "직원 1");

        // 검색 결과 처리
        Object[] result = (Object[])query.getSingleResult();
        System.out.println(result[0] + "번 직원의 정보");
        System.out.println(Arrays.toString(result));

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
