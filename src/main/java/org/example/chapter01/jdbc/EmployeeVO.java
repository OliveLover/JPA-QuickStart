package org.example.chapter01.jdbc;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class EmployeeVO {
    private Long id;
    private String name;
    private Timestamp startDate;
    private String title;
    private String deptName;
    private Double salary;
}
