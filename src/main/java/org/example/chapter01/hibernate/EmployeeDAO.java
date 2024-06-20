package org.example.chapter01.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class EmployeeDAO {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public EmployeeDAO() {
        String config = "org/example/chapter01/hibernate/hibernate.cfg.xml";
        sessionFactory = new Configuration().configure(config).buildSessionFactory();
        session = sessionFactory.openSession();
        transaction = session.getTransaction();
    }

    public void insertEmployee(EmployeeVO vo) {
        System.out.println("===> Hibernate 기반으로 직원 등록 기능 처리");
        try {
            transaction.begin();
            session.persist(vo);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
    }

    public List<EmployeeVO> getEmployeeList() {
        System.out.println("==> Hibernate 기반으로 직원 목록 조회 기능 처리");
        String jpql = "SELECT e FROM EmployeeVO e ORDER BY e.id";
        List<EmployeeVO> employeeList = session.createQuery(jpql).getResultList();
        return employeeList;
    }
}