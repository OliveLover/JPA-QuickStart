package org.example.chapter06.client;

import org.example.chapter06.domain.Department;
import org.example.chapter06.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class JPQLJoinClient {
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

        String jpql = "SELECT e FROM Employee e";
        TypedQuery<Employee> query = em.createQuery(jpql, Employee.class);

        List<Employee> resultList = query.getResultList();
        System.out.println("검색된 직원 목록");
        for (Employee employee : resultList) {
            System.out.println(employee.getName());
        }

        em.close();
    }

    private static void dataInsert(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Department department1 = new Department();
        department1.setName("개발부");
        for (int i = 1; i <= 3; i++) {
            Employee employee = new Employee();
            employee.setName("개발직원" + i);
            employee.setSalary(i * 12700.00);
            employee.setMailId("Dev-" + i);
            employee.setDept(department1);
        }
        em.persist(department1);

        Department department2 = new Department();
        department2.setName("영업부");
        for (int i = 1; i <= 3; i++) {
            Employee employee = new Employee();
            employee.setName("영원직원 " + i);
            employee.setSalary(27300.00 * i);
            employee.setMailId("Sale-" + i);
            employee.setDept(department2);
        }
        em.persist(department2);

        Department department3 = new Department();
        department3.setName("인재개발부");
        em.persist(department3);

        // 부서 정보가 없는 새로운 직원 추가
        Employee employee = new Employee();
        employee.setName("아르바이트");
        employee.setMailId("Alba-01");
        employee.setSalary(10000.00);
        em.persist(employee);

        // 이름이 영업부인 새로운 직원 추가
        Employee employee2 = new Employee();
        employee2.setName("영업부");
        em.persist(employee2);

        em.getTransaction().commit();
        em.close();
    }
}
