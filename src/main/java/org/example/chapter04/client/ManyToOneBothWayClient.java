package org.example.chapter04.client;

import org.example.chapter04.domain.Department;
import org.example.chapter04.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ManyToOneBothWayClient {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter04");
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
        Department department = em.find(Department.class, 1L);

        System.out.println("검색된 부서 : " + department.getName());
        System.out.println("부서에 소속된 직원 명단");
        for (Employee employee : department.getEmployeeList()) {
            System.out.println(employee.getName() + "(" +
                    employee.getDept().getName() + ")");
        }
    }

    private static void dataInsert(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // 부서 등록
        Department department = new Department();
        department.setName("개발부");
        em.persist(department);

        // 직원1 등록(Employee --> Department 참조)
        Employee employee1 = new Employee();
        employee1.setName("둘리");
        employee1.setDept(department);
        em.persist(employee1);

        // 직원2 등록(Employee --> Department 참조)
        Employee employee2 = new Employee();
        employee2.setName("도우너");
        employee2.setDept(department);
        em.persist(employee2);

        // Department.employeeList에 Employee 등록
        department.getEmployeeList().add(employee1);
        department.getEmployeeList().add(employee2);
        
        System.out.println(department.getName() + "의 직원 수 : " +
                department.getEmployeeList().size());

        em.getTransaction().commit();
        em.close();
    }
}
