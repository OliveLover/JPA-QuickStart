package org.example.chapter02.biz.client;

import org.example.chapter02.biz.domain.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class EmployeeServiceClient {
    public static void main(String[] args) {
        // 엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Chapter02");

        // 엔티티매니저 생성
        EntityManager em = emf.createEntityManager();

        try {
            Employee employee = new Employee();
            employee.setId(1L);
            employee.setName("둘리");
            employee.setMailId("gurum");
            employee.setStartDate(new Date());
            employee.setTitle("과장");
            employee.setDeptName("총무부");
            employee.setSalary(2500.00);
            employee.setCommissionPct(12.50);

            // 직원 등록 처리
            em.persist(employee);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 엔티티 매니저 및 엔티티 매니저 팩토리 종료
            em.close();
            emf.close();
        }
    }
}
