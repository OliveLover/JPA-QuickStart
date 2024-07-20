package org.example.chapter08.biz.service;

import org.example.chapter08.biz.domain.Department;
import org.example.chapter08.biz.persistence.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("deptService")
public class DepartmentService {

    @Autowired
    private DepartmentRepository deptRepository;

    @Transactional
    public void insertDepartment(Department department) {
        deptRepository.insertDepartment(department);
    }

    public Department getDepartment(Department department) {
        return deptRepository.getDepartment(department);
    }
}
