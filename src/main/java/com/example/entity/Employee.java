package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Employee {

    private @Id @GeneratedValue Long id;
    private String name;
    private String dept;
    private int salary;



    public static Employee create(String name, String dept, int salary) {
        Employee e = new Employee();
        e.setName(name);
        e.setDept(dept);
        e.setSalary(salary);
        return e;
    }

    @Override
    public String toString() {
        return "Employee{" + "id=" + id + ", name='" + name + '\'' + ", dept='" + dept + '\''
                + ", salary=" + salary + '}';
    }
}

