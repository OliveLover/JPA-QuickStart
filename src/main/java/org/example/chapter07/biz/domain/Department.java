package org.example.chapter07.biz.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString(exclude = "employeeList")
@Entity
@Table(name = "S_DEPT")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long deptId;

    private String name;

    @OneToMany(mappedBy = "dept")
    private List<Employee> employeeList = new ArrayList<>();
}
