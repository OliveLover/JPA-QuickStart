package org.example.chapter02.biz.domain;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;


@Data
@ToString(exclude = {"searchCondition", "searchKeyword"})
@Entity
@Table(name = "S_EMP",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME", "MAILID"})})
@Access(AccessType.PROPERTY)
public class Employee {
    private Long id;
    private String name;
    private String mailId;
    private Date startDate;
    private String title;
    private String deptName;
    private Double salary;
    private Double commissionPct;
    private String searchCondition;
    private String searchKeyword;

    @Id
    @Column(length = 7, nullable = false)
    public long getId() {
        return id;
    }

    @Column(length = 25, nullable = false)
    public String getName() {
        return name;
    }

    @Column(length = 8, unique = true)
    public String getMailId() {
        return mailId;
    }

    @Column(name = "START_DATE")
    @Temporal(TemporalType.DATE)
    public Date getStartDate() {
        return startDate;
    }

    @Column(length = 25)
    public String getTitle() {
        return title;
    }

    @Column(name = "DEPT_NAME", length = 30)
    public String getDeptName() {
        return deptName;
    }

    @Column(precision = 11, scale = 2)
    public Double getSalary() {
        return salary;
    }

    @Column(name = "COMMISSION_PCT", precision = 4, scale = 2,
            columnDefinition = "double CHECK (commission_pct IN (10, 12.5, 15, 17.5, 20))")
    public Double getCommissionPct() {
        return commissionPct;
    }

    @Transient
    public String getSearchCondition() {
        return searchCondition;
    }

    @Transient
    public String getSearchKeyword() {
        return searchKeyword;
    }
}
