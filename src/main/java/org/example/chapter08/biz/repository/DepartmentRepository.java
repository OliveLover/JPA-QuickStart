package org.example.chapter08.biz.repository;

import org.example.chapter08.biz.domain.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Long> {
}
