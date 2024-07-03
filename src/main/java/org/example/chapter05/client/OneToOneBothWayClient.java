package org.example.chapter05.client;

import org.example.chapter05.domain.Employee;
import org.example.chapter05.domain.EmployeeCard;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OneToOneBothWayClient {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("Chapter05");
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

        // 검색된 사원증을 통해 직원 정보 사용하기
        EmployeeCard employeeCard = em.find(EmployeeCard.class, 1L);
        System.out.println("사원증을 통한 직원 정보 접근 : " + employeeCard.toString());
        System.out.println("사원증 유효기간 : " + employeeCard.getExpireDate());
        System.out.println("사원증 소유자 : " + employeeCard.getEmployee().getName());

        // 검색된 직원을 통해 사원증 정보 사용하기
        Employee employee = em.find(Employee.class, 1L);
        System.out.println("사원증 소유자 : " + employee.getName());
        System.out.println("사원증 유효기간 : " + employee.getCard().getExpireDate());
    }

    private static void dataInsert(EntityManagerFactory emf) throws ParseException {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // 직원 등록
        Employee employee = new Employee();
        employee.setName("둘리");
        em.persist(employee);

        // 사원증 등록
        EmployeeCard card = new EmployeeCard();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        card.setExpireDate(dateFormat.parse("2025-12-31"));
        card.setRole("MASTER");
        // 직원에 대한 참조 설정
        card.setEmployee(employee);

        em.persist(card);

        em.getTransaction().commit();
        em.close();

        System.out.println("사원증을 통한 직원 정보 접근 : " + card.getEmployee().getName());
        System.out.println("직원을 통한 사원증 정보 접근 : " + employee.getCard().getExpireDate());
    }
}
