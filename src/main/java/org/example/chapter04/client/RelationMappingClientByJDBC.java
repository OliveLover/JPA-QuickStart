package org.example.chapter04.client;

import org.example.chapter04.domain.Employee;
import org.example.chapter04.repository.EmployeeDAO;

import java.util.List;

public class RelationMappingClientByJDBC {

    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        List<Employee> employeeList = employeeDAO.getEmployeeList();

        for (Employee employee : employeeList) {
            System.out.println(employee.getName() + "직원의 부서명 : " +
                    employee.getDept().getName()
            );
        }
    }
}
