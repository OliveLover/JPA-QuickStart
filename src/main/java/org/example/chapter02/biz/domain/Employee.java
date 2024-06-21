package org.example.chapter02.biz.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "S_EMP",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME", "MAILID"})})
public class Employee {
    @Id
    private Long id;

    private String name;

    private String mailId;

    @Column(name = "START_DATE")
    private Date startDate;

    private String title;

    @Column(name = "DEPT_NAME")
    private String deptName;

    private Double salary;

    @Column(name = "COMMISSION_PCT")
    private Double commissionPct;
}
