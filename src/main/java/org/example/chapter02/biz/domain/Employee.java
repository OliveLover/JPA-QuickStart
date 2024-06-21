package org.example.chapter02.biz.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Entity
@Table(name = "S_EMP")
public class Employee {
    @Id
    private EmployeeId empId;

    private String name;
}
