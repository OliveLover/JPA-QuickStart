package org.example.chapter04.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "S_DEPT")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deptId;

    @Column(length = 25, nullable = false)
    private String name;
}
