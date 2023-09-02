package org.example.Dao;

import org.example.Entity.Employee;

import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: He Zhiyang
 * @Date: 2023/04/14/14:49
 * @Description:
 */
public interface EmployeeData {
    List<Employee> selectAllEmployee();

    Optional<Employee> selectEmployeeById(String employeeid);

    List<Employee> selectEmployeeByName(String employeename);
    List<Employee>insertEmployee(Employee employee);

    List<Employee> updataEmployee( Employee employee);

    List<Employee> deleteEmployeeById(String employeeid);

}
