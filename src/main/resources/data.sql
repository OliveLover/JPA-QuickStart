CREATE TABLE s_emp(
    id          NUMBER(7)       CONSTRAINT s_emp_id_nn NOT NULL,
    name        VARCHAR2(25)    CONSTRAINT s_emp_name_nn NOT NULL,
    title       VARCHAR2(25),
    dept_name   VARCHAR2(25),
    salary      NUMBER(11, 2),
    CONSTRAINT s_emp_id_pk PRIMARY KEY (id)
);

INSERT INTO s_emp_VALUES(1, '안은경', '2002-12-03', '영업대표이사', '영업부', 2500);
SELECT * FROM s_emp;