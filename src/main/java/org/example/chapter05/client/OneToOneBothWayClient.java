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


        Employee employee = em.find(Employee.class, 1L);
//        System.out.println("사원증을 통한 직원 정보 접근 : " + employee.getCard().toString());
    }

    private static void dataInsert(EntityManagerFactory emf) throws ParseException {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // 사원증 등록
        EmployeeCard card = new EmployeeCard();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        card.setExpireDate(dateFormat.parse("2025-12-31"));
        card.setRole("MASTER");
        em.persist(card);

        // 직원 등록
        Employee employee = new Employee();
        employee.setName("둘리");
        // 사원증에 대한 참조 설정
//        employee.setEmployeeCard(card);
        em.persist(employee);


        em.getTransaction().commit();
        em.close();
    }
}
