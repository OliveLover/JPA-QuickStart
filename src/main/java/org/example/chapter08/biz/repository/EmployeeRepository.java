package org.example.chapter08.biz.repository;

import org.example.chapter08.biz.domain.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByName(String name);

    List<Employee> findByNameContaining(String name);
}
