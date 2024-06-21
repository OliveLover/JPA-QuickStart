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
    @Column(length = 7, nullable = false)
    private Long id;

    @Column(length = 25, nullable = false)
    private String name;

    @Column(length = 8, unique = true)
    private String mailId;

    @Column(name = "START_DATE", insertable = false)
    private Date startDate;

    @Column(length = 25)
    private String title;

    @Column(name = "DEPT_NAME", length = 30)
    private String deptName;

    @Column(precision = 11, scale = 2)
    private Double salary;

    @Column(name = "COMMISSION_PCT", precision = 4, scale = 2,
            columnDefinition = "double CHECK(commission_pct IN (10, 12.5, 15, 17.5, 20))")
    private Double commissionPct;
}
