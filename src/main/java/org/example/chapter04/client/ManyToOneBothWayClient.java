package org.example.chapter04.client;

import org.example.chapter04.domain.Department;
import org.example.chapter04.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class ManyToOneBothWayClient {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
        try {
            dataInsert(emf);
            dataDelete(emf);
//            dataSelect(emf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            emf.close();
        }
    }

    private static void dataSelect(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        Department department = em.find(Department.class, 1L);

        System.out.println("검색된 부서 : " + department.getName());
        System.out.println("부서에 소속된 직원 명단");
        for (Employee employee : department.getEmployeeList()) {
            System.out.println(employee.getName() + "(" +
                    employee.getDept().getName() + ")");
        }
    }

    private static void dataDelete(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // 부서 검색
        Department department = em.find(Department.class, 1L);

        // 부서에 등록된 직원 삭제
//        List<Employee> employeeList = department.getEmployeeList();
//        for (Employee employee : employeeList) {
//            em.remove(employee);
//        }

        // 부서 삭제
        em.remove(department);

        em.getTransaction().commit();
        em.close();
    }

    private static void dataInsert(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // 부서 등록
        Department department = new Department();
        department.setName("개발부");
//        em.persist(department);

        // 직원 여러 명 등록
        for (int i = 1; i <= 5; i++) {
            Employee employee = new Employee();
            employee.setName("직원-" + i);
            employee.setDept(department);
//            em.persist(employee);
        }
        em.persist(department);

        em.getTransaction().commit();
        em.close();
    }
}
