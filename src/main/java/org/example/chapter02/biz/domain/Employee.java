package org.example.chapter02.biz.domain;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "S_EMP")
@TableGenerator(name = "SEQ_GENERATOR",
        table = "SHOPPING_SEQUENCES",
        pkColumnName = "SEQ_NAME",
        pkColumnValue = "EMP_SEQ",
        valueColumnName = "NEXT_VALUE",
        initialValue = 0,
        allocationSize = 1)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,
            generator = "SEQ_GENERATOR")
    private Long id;

    private String name;
}
