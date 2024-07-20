package org.example.chapter08.biz.client;

import org.example.chapter08.biz.config.SpringConfiguration;
import org.example.chapter08.biz.domain.Department;
import org.example.chapter08.biz.domain.Employee;
import org.example.chapter08.biz.service.DepartmentService;
import org.example.chapter08.biz.service.EmployeeService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.domain.Page;

import java.util.List;

public class QueryMethodClient {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext container =
                new AnnotationConfigApplicationContext(SpringConfiguration.class);

        DepartmentService deptService =
                (DepartmentService) container.getBean("deptService");
        EmployeeService employeeService =
                (EmployeeService) container.getBean("empService");

        dataInsert(deptService, employeeService);
        dataSelect(employeeService);

        container.close();
    }

    private static void dataSelect(EmployeeService employeeService) {
        Employee employee = new Employee();
        employee.setName("");
        employee.setMailId("Dev");
        Page<Employee> pageInfo = employeeService.getEmployeeList(employee, 2);

        System.out.println("한 페이지에 출력되는 데이터 수 : " + pageInfo.getSize());
        System.out.println("전체 페이지 수 : " + pageInfo.getTotalPages());
        System.out.println("전체 데이터 수 : " + pageInfo.getTotalElements());
        if (pageInfo.hasPrevious()) {
            System.out.println("이전 페이지 : " + pageInfo.previousPageable());
        } else {
            System.out.println("첫 번째 페이지 입니다.");
        }

        if (pageInfo.hasNext()) {
            System.out.println("다음 페이지 : " + pageInfo.nextPageable());
        } else {
            System.out.println("마지막 페이지 입니다.");
        }

        List<Employee> employeeList = pageInfo.getContent();
        System.out.println("\n[ 검색된 회원 목록 ] ");
        for (Employee emp : employeeList) {
            System.out.println("---> " + emp.toString());
        }
    }

    private static void dataInsert(DepartmentService deptService, EmployeeService employeeService) {
        Department department1 = new Department();
        department1.setName("개발부");
        deptService.insertDepartment(department1);

        Department department2 = new Department();
        department2.setName("영업부");
        deptService.insertDepartment(department2);

        for (int i = 1; i <= 5; i++) {
            Employee employee = new Employee();
            employee.setName("개발직원 " + i);
            employee.setSalary(i * 12700.00);
            employee.setMailId("Dev-" + i);
            employee.setDept(department1);
            employeeService.insertEmployee(employee);
        }

        for (int i = 1; i <= 7; i++) {
            Employee employee = new Employee();
            employee.setName("영업직원 " + i);
            employee.setSalary(i * 24300.00);
            employee.setMailId("Sales-" + i);
            employee.setDept(department2);
            employeeService.insertEmployee(employee);
        }
    }
}
