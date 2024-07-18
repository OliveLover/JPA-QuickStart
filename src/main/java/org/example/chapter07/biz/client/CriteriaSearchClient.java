package org.example.chapter07.biz.client;

import org.example.chapter07.biz.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

public class CriteriaSearchClient {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter07");
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

        // 검색 정보 설정
        String searchCondition = "TITLE";
        String searchKeyword = "과장";

        // 크라이테리어 빌더 생성
        CriteriaBuilder builder = em.getCriteriaBuilder();

        // 크라이테리어 쿼리 생성
        CriteriaQuery<Employee> criteriaQuery =
                builder.createQuery(Employee.class);

        // FROM Employee emp
        Root<Employee> emp = criteriaQuery.from(Employee.class);

        // SELECT emp
        criteriaQuery.select(emp);

        // 검색 조건에 따른 분기 처리
        if (searchCondition.equals("NAME")) {
            // WHERE name = :searchKeyword
            criteriaQuery.where(
                    builder.equal(emp.get("name"), searchKeyword)
            );
        } else if (searchCondition.equals("MAILID")) {
            // WHERE mailId = :searchKeyword
            criteriaQuery.where(
                    builder.equal(emp.get("mailId"), searchKeyword)
            );
        } else if (searchCondition.equals("TITLE")) {
            // WHERE name = :searchKeyword
            criteriaQuery.where(
                    builder.equal(emp.get("title"), searchKeyword)
            );
        }

        TypedQuery<Employee> query = em.createQuery(criteriaQuery);
        List<Employee> resultList = query.getResultList();

        for (Employee result : resultList) {
            System.out.println("---> " + result.toString());
        }

        em.close();
    }

    private static void dataInsert(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // 직원 정보 등록
        for (int i = 1; i <= 3; i++) {
            Employee employee = new Employee();
            employee.setName("개발맨 " + i);
            employee.setDeptName("개발부");
            employee.setSalary(12700.00 * i);
            employee.setStartDate(new Date());
            employee.setTitle("사원");
            employee.setCommissionPct(10.00);
            em.persist(employee);
        }

        for (int i = 1; i <= 3; i++) {
            Employee employee = new Employee();
            employee.setName("영업맨 " + i);
            employee.setMailId("Virus" + i);
            employee.setDeptName("영업부");
            employee.setSalary(23800.00 * i);
            employee.setStartDate(new Date());
            employee.setTitle("과장");
            employee.setCommissionPct(15.00);
            em.persist(employee);
        }

        // 부서 정보가 없는 직원 등록
        Employee employee = new Employee();
        employee.setName("아르바이트");
        employee.setMailId("Alba-01");
        employee.setSalary(10000.00);
        em.persist(employee);

        em.getTransaction().commit();
        em.close();
    }
}
