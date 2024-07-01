package org.example.chapter04.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = "employeeList")
@Entity
@Table(name = "S_DEPT")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DEPT_ID")
    private Long deptId;

    @Column(length = 25, nullable = false)
    private String name;

    @OneToMany(mappedBy = "dept")
    private Set<Employee> employeeList = new HashSet<>();
}
