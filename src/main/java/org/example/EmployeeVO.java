package org.example;

import java.sql.Timestamp;

public class EmployeeVO {
    private Long id;
    private String name;
    private Timestamp startDate;
    private String title;
    private String deptName;
    private Double salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeeVO [id=" + id + ", name=" + name + ", startDate=" + startDate + ", title=" + title +
                " , deptName=" + deptName + ", salary=" + salary + "]";
    }

}
