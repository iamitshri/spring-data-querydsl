package com.example.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import com.example.entity.Employee;

public interface EmployeeRepository  extends JpaRepository<Employee, Long>, QuerydslPredicateExecutor<Employee> {
}