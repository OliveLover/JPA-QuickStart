package org.example.chapter08.biz.service;

import org.example.chapter08.biz.domain.Department;
import org.example.chapter08.biz.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("deptService")
@Transactional
public class DepartmentService {

    @Autowired
    private DepartmentRepository deptRepository;

    public void insertDepartment(Department department) {
        deptRepository.save(department);
    }

    public Department getDepartment(Department department) {
        return deptRepository.findById(department.getDeptId()).get();
    }
}
