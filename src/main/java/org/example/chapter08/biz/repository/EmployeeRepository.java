package org.example.chapter08.biz.repository;

import org.example.chapter08.biz.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByName(String name);

    Page<Employee> findByNameContaining(String name, Pageable paging);

    List<Employee> findByNameContainingOrMailIdContaining(String name, String mailId);

    List<Employee> findByMailIdContainingOrderByNameDesc(String mailId);

    @Query("SELECT emp "
            + "FROM Employee emp "
            + "WHERE emp.name like %:name% "
            + "  OR emp.mailId like %:mailId%")
    List<Employee> findByJPQL(@Param("name") String name
            , @Param("mailId") String email);
}
