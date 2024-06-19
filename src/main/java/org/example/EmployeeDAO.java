package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    private String INSERT_EMP = "insert into s_emp(id, name, start_date, title, dept_name, salary) " +
            "values((select nvl(max(id), 0) + 1 from s_emp), ?, ?, ?, ?, ?)";
    private String LIST_EMP =
            "select id, name, start_date, title, dept_name, salary " +
                    "from s_emp order by name";

    public void insertEmployee(EmployeeVO vo) {
        System.out.println("===> JDBC 기반으로 직원 등록 기능 처리");
        try {
            conn = getConnection();

            stmt = conn.prepareStatement(INSERT_EMP);
            stmt.setString(1, vo.getName());
            stmt.setTimestamp(2, vo.getStartDate());
            stmt.setString(3, vo.getTitle());
            stmt.setString(4, vo.getDeptName());
            stmt.setDouble(5, vo.getSalary());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(stmt, conn);
        }
    }

    public List<EmployeeVO> getEmployeeList() {
        System.out.println("===> JDBC 기반으로 직원 목록 조회 기능 처리");
        List<EmployeeVO> employeeList = new ArrayList<>();
        try {
            conn = getConnection();
            stmt = conn.prepareStatement(LIST_EMP);
            rs = stmt.executeQuery();
            while (rs.next()) {
                EmployeeVO employee = new EmployeeVO();

                employee.setId(rs.getLong("ID"));
                employee.setName(rs.getString("NAME"));
                employee.setStartDate(rs.getTimestamp("START_DATE"));
                employee.setTitle(rs.getString("TITLE"));
                employee.setDeptName(rs.getString("DEPT_NAME"));
                employee.setSalary(rs.getDouble("SALARY"));
                employeeList.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs, stmt, conn);
        }
        return employeeList;
    }

    public Connection getConnection() {
        try {
            Class.forName("org.h2.Driver");
            String url = "jdbc:h2:tcp://localhost/~/test";
            conn = DriverManager.getConnection(url, "sa", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public void close(ResultSet rs, Statement stmt, Connection conn) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rs = null;
        }

        try {
            if (stmt != null) stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt = null;
        }

        try {
            if (conn != null && !conn.isClosed()) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }

    public void close(Statement stmt, Connection conn) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            stmt = null;
        }

        try {
            if (conn != null && !conn.isClosed()) conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn = null;
        }
    }
}
