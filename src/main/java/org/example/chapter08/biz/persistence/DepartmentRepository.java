package org.example.chapter08.biz.persistence;

import org.example.chapter08.biz.domain.Department;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DepartmentRepository {

    @PersistenceContext
    private EntityManager em;

    public void insertDepartment(Department department) {
        em.persist(department);
    }

    public void updateDepartment(Department department) {
        em.merge(department);
    }

    public void deleteDepartment(Department department) {
        em.remove(em.find(Department.class, department.getDeptId()));
    }

    public Department getDepartment(Department department) {
        return em.find(Department.class, department.getDeptId());
    }

    public List<Department> getDepartmentList(Department department) {
        return em.createQuery("from Department dept order by dept.deptId desc")
                .getResultList();
    }
}
