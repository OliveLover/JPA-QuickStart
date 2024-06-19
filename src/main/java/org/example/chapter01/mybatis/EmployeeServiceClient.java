package org.example.chapter01.mybatis;

import java.sql.Timestamp;
import java.util.List;

public class EmployeeServiceClient {
    public static void main(String[] args) {
        EmployeeVO vo = new EmployeeVO();
        vo.setName("둘리");
        vo.setStartDate(new Timestamp(System.currentTimeMillis()));
        vo.setTitle("사원");
        vo.setDeptName("영업부");
        vo.setSalary(1700.00);

        EmployeeDAO employeeDAO = new EmployeeDAO();
        employeeDAO.insertEmployee(vo);

        List<EmployeeVO> employeeList = employeeDAO.getEmployeeList();
        for (EmployeeVO employee : employeeList) {
            System.out.println("--> " + employee.toString());
        }
    }
}
