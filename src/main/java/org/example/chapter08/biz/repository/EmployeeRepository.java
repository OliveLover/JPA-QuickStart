package org.example.chapter08.biz.repository;

import org.example.chapter08.biz.domain.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
