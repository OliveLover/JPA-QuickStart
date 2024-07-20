package org.example.chapter08.biz.service;

import org.example.chapter08.biz.domain.Employee;
import org.example.chapter08.biz.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
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

    public List<Employee> getEmployeeList(Employee employee, int pageNumber) {
        Pageable paging = PageRequest.of(pageNumber - 1, 3, Sort.by(new Order(Direction.DESC, "mailId"),
                new Order(Direction.ASC, "salary")));
        return (List<Employee>) empRepository.findByNameContaining(employee.getName(), paging);
    }
}
