package org.example.chapter07.biz.client;

import org.example.chapter07.biz.domain.Department;
import org.example.chapter07.biz.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static javax.persistence.criteria.CriteriaBuilder.Trimspec.TRAILING;

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

        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> criteriaQuery =
                builder.createQuery(Object[].class);

        // FROM Employee emp
        Root<Employee> emp = criteriaQuery.from(Employee.class);

        // SELECT concat, substring, trim, lower, upper, length, locate
        criteriaQuery.multiselect(
                builder.concat(builder.concat(emp.<String>get("name"), "의 급여"),
                        emp.<String>get("salary")),
                builder.substring(emp.<String>get("name"), 1, 2),
                builder.trim(TRAILING,
                        Character.valueOf('부'),
                        emp.<String>get("dept").<String>get("name")),
                builder.lower(emp.<String>get("mailId")),
                builder.upper(emp.<String>get("mailId")),
                builder.length(emp.<String>get("mailId")),
                builder.locate(emp.<String>get("mailId"), "rus")
        );

        TypedQuery<Object[]> query = em.createQuery(criteriaQuery);
        List<Object[]> resultList = query.getResultList();
        for (Object[] result : resultList) {
            System.out.println("---> " + Arrays.toString(result));
        }

        em.close();
    }

    private static void dataInsert(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // 부서 정보 등록
        Department devDept = new Department();
        devDept.setName("개발부");
        em.persist(devDept);

        Department salesDept = new Department();
        salesDept.setName("영업부");
        em.persist(salesDept);

        // 직원 정보 등록
        for (int i = 1; i <= 3; i++) {
            Employee employee = new Employee();
            employee.setName("개발맨 " + i);
//            employee.setDeptName("개발부");
            employee.setMailId("Corona" + i);
            employee.setDept(devDept);
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
//            employee.setDeptName("영업부");
            employee.setDept(salesDept);
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
