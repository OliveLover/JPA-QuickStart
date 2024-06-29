-- 테이블 삭제
DROP TABLE s_dept;
DROP TABLE s_emp;

-- 부서 테이블
CREATE TABLE s_dept (
    dept_id NUMBER(7)    CONSTRAINT s_ept_id_nn NOT NULL,    -- 부서 아이디
    name    VARCHAR2(25) CONSTRAINT s_dept_name_nn NOT NULL, -- 부서 이름
    CONSTRAINT s_dept_id_pk PRIMARY KEY (dept_id)
);

-- 직원 테이블
CREATE TABLE s_emp (
    id      NUMBER(7)    CONSTRAINT s_emp_id_nn NOT NULL,    -- 직원 아이디
    name    VARCHAR2(25) CONSTRAINT s_emp_name_nn NOT NULL,  -- 직원 이름
    dept_id NUMBER(7),
    CONSTRAINT s_emp_id_pk PRIMARY KEY(id)
);

ALTER TABLE s_emp ADD CONSTRAINT s_emp_dept_id_fk
      FOREIGN KEY (dept_id) REFERENCES s_dept (dept_id);

INSERT INTO s_dept VALUES(1, '개발부');
INSERT INTO s_emp VALUES(1, '둘리', 1);
INSERT INTO s_emp VALUES(2, '도우너', 1);