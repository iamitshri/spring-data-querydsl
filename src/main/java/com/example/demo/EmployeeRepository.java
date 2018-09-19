package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

interface EmployeeRepository  extends JpaRepository<Employee, Long>, QuerydslPredicateExecutor<Employee> {
}