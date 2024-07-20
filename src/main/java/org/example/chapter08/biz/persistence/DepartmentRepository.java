package org.example.chapter08.biz.persistence;

import org.example.chapter08.biz.domain.Department;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class DepartmentRepository {

    @PersistenceContext
    private EntityManager em;

    public void insertDepartment(Department department) {
        System.out.println("===> JPA로 insertDepartment() 기능 처리");
        em.persist(department);
    }

    public Department getDepartment(Department department) {
        System.out.println("===> JPA로 getDepartment() 기능 처리");
        return em.find(Department.class, department.getDeptId());
    }
}
