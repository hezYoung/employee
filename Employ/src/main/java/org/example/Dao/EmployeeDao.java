package org.example.Dao;

import org.example.Entity.EmploRowmapper;
import org.example.Entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: He Zhiyang
 * @Date: 2023/04/14/14:51
 * @Description:
 */
@Repository("employeeMySQL")
public class EmployeeDao implements EmployeeData{
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public EmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }



    @Override
    public List<Employee> selectAllEmployee() {
        final String sql="select * from employ;";

        return jdbcTemplate.query(sql,new EmploRowmapper());
    }

    @Override
    public Optional<Employee> selectEmployeeById(String employeeid) {
        final String sql = "select * from employ where employeeid=?";
        List<Employee> employeeList = jdbcTemplate.query(sql, new EmploRowmapper(), employeeid);
        return employeeList.stream().findFirst();
    }

    @Override
    public List<Employee> selectEmployeeByName(String employeename) {
        final String sql = "select * from employ where employeename like ?";
        List<Employee> employeeList = jdbcTemplate.query(sql, new EmploRowmapper(), employeename);
        return employeeList;
    }

    @Override
    public List<Employee> insertEmployee(Employee employee) {
        final String sql = "insert into employ(employeeid,employeename,gender,age,basesalary,locationid) value (?,?,?,?,?,?)";
        int result=jdbcTemplate.update(sql,
                employee.getEmployeeid(),
                employee.getEmployeename(),
                employee.getGender(),
                employee.getAge(),
                employee.getBasesalary(),
                employee.getLocationid()
                );
        if (result > 0) {
            System.out.println("信息添加成功");
        }else {
            System.out.println("插入失败");
        }
        return selectAllEmployee();
    }


    @Override
    public List<Employee> updataEmployee(Employee employee) {

        final String sql = "update employ set employeeid=?,employeename=?," +
                "gender=?,age=?,basesalary=?, locationid=? where employeeid=?";
        jdbcTemplate.update(sql,employee.getEmployeeid(),
                employee.getEmployeename(),
                employee.getGender(),
                employee.getAge(),
                employee.getBasesalary(),
                employee.getLocationid(),
                employee.getLocationid()

                );
        return selectAllEmployee();
    }

    @Override
    public List<Employee> deleteEmployeeById(String employeeid) {
        final String sql = "delete from employ where employeeid=?";
        jdbcTemplate.update(sql, employeeid);
        return selectAllEmployee();
    }
}
