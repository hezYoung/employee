package org.example.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: He Zhiyang
 * @Date: 2023/04/13/18:13
 * @Description:
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Employee {
    private int employeeid;
    private String employeename;
    private String gender;
    private int age;
    private String basesalary;
    private int locationid;
}
