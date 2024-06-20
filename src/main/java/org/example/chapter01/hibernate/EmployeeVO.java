package org.example.chapter01.hibernate;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Data
//@Entity
//@Table(name = "S_EMP")
public class EmployeeVO {

    @Id
    private Long id;

    private String name;

    @Column(name = "START_DATE")
    private Timestamp startDate;

    private String title;

    @Column(name = "DEPT_NAME")
    private String deptName;

    private Double salary;

    private String email;
}
