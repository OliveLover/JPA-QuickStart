package org.example.chapter06.client;

import org.example.chapter06.domain.Employee;
import org.example.chapter06.domain.EmployeeSalaryData;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
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
        String jpql = "SELECT " +
                "NEW org.example.chapter06.domain.EmployeeSalaryData(id, salary, " +
                "commissionPct) FROM Employee";

        // JPQL 전송
        TypedQuery<EmployeeSalaryData> query = em.createQuery(jpql, EmployeeSalaryData.class);

        // 검색 결과 처리
        List<EmployeeSalaryData> resultList = query.getResultList();
        System.out.println("검색된 직원 목록");
        for (EmployeeSalaryData result : resultList) {
            System.out.println("---> " + result.toString());
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
            employee.setCommissionPct(15.00);
            em.persist(employee);
        }

        em.getTransaction().commit();
        em.close();
    }
}
