package org.example;


import org.example.Service.EmployeeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



@SpringBootApplication
public class EmployApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployApplication.class, args);
        ApplicationContext context = new AnnotationConfigApplicationContext("org.example.Aop");

    }


}
