package org.example.chapter01.mybatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class EmployeeDAO {

    private SqlSession mybatis;

    public EmployeeDAO() {
        try {
            Reader reader = Resources.getResourceAsReader("org/example/chapter01/mybatis/sql-map-config.xml");
            SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(reader);
            mybatis = sessionFactory.openSession();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void insertEmployee(EmployeeVO vo) {
        System.out.println("==> MyBatis 기반으로 회원 등록 기능 처리");
        mybatis.insert("EmployeeDAO.insertEmployee", vo);
        mybatis.commit();
    }

    public List<EmployeeVO> getEmployeeList() {
        System.out.println("==> MyBatis 기반으로 회원 목록 조회 기능 처리");
        return mybatis.selectList("EmployeeDAO.getEmployeeList");
    }
}