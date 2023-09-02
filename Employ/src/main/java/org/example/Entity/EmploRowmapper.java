package org.example.Entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: He Zhiyang
 * @Date: 2023/04/14/15:00
 * @Description:
 */
public class EmploRowmapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Employee(
                rs.getInt("employeeid"),
                rs.getString("employeename"),
                rs.getString("gender"),
                rs.getInt("age"),
                rs.getString("basesalary"),
                rs.getInt("locationid")
        );
    }
}
