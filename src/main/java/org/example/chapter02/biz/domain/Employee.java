package org.example.chapter02.biz.domain;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@DynamicUpdate
//@Entity
//@Table(name = "S_EMP")
public class Employee {
    @Id
    @GeneratedValue//(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
