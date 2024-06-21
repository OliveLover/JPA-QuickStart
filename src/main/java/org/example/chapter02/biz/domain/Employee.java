package org.example.chapter02.biz.domain;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "S_EMP")
@SequenceGenerator(name = "S_EMP_GENERATOR",
        sequenceName = "S_EMP_SEQUENCE",
        initialValue = 1,
        allocationSize = 1)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "S_EMP_GENERATOR")
    private Long id;

    private String name;
}
