package org.example.chapter07.biz.client;

import org.example.chapter07.biz.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
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
        String searchCondition = "NAME";
        String searchKeyword = "아르바이트";

        // 검색 관련 쿼리
        String jpqlByMailId = "SELECT e FROM Employee e "
                + "WHERE e.mailId=:searchKeyword";
        String jpqlByName = "SELECT e FROM Employee e "
                + "WHERE e.name=:searchKeyword";

        TypedQuery<Employee> query = null;

        // 검색 조건에 따른 분기 처리
        if (searchCondition.equals("NAME")) {
            query = em.createQuery(jpqlByName, Employee.class);
        } else if (searchCondition.equals("MAILID")) {
            query = em.createQuery(jpqlByMailId, Employee.class);
        }

        query.setParameter("searchKeyword", searchKeyword);
        List<Employee> resultList = query.getResultList();

        System.out.println(searchCondition + "을 기준으로한 검색 결과 ");
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
