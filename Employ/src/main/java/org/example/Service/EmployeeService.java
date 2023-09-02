package org.example.Service;

import org.example.Dao.EmployeeData;
import org.example.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: He Zhiyang
 * @Date: 2023/04/14/15:05
 * @Description:
 */
@Service
public class EmployeeService {
private EmployeeData employeeData;
@Autowired
    public EmployeeService(@Qualifier("employeeMySQL") EmployeeData employeeData) {
        this.employeeData = employeeData;
    }
    public List<Employee> showEmployee() {
        return employeeData.selectAllEmployee();
    }
    public Optional<Employee> selectEmployeeByid(String employeeid) {
        return employeeData.selectEmployeeById(employeeid);
    }
    public List<Employee> selectEmployeeByname(String employeename) {
        return employeeData.selectEmployeeByName(employeename);
    }
    public List<Employee> updataEmployee( Employee employee) {
        return employeeData.updataEmployee(employee);

    }
    public List<Employee> addEmployee( Employee employee){
        return employeeData.insertEmployee(employee);

    }
    public List<Employee> deleteEmployeeByid(String employeeid) {
        return employeeData.deleteEmployeeById(employeeid);
    }
}

