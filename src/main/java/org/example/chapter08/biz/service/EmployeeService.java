package org.example.chapter08.biz.service;

import org.example.chapter08.biz.domain.Employee;
import org.example.chapter08.biz.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("empService")
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository empRepository;

    public void insertEmployee(Employee employee) {
        empRepository.save(employee);
    }

    public void updateEmployee(Employee employee) {
        empRepository.save(employee);
    }

    public void deleteEmployee(Employee employee) {
        empRepository.delete(employee);
    }

    public Employee getEmployee(Employee employee) {
        return empRepository.findById(employee.getId()).get();
    }

    public List<Object[]> getEmployeeList(Employee employee) {
        Pageable paging = PageRequest.of(0, 3, Sort.Direction.DESC, "id");
        return empRepository.findByJPQL(employee.getName(), paging);
    }
}
