package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class QueryDslExampleRunner implements ApplicationRunner {

    @Autowired
    private EmployeeRepository repo;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("this is amit");

        List<Employee> employees = createEmployees();
        repo.saveAll(employees);
        System.out.println("-- employees persisted --");
        employees.forEach(System.out::println);

        System.out.println(" -- employees having salary greater than 3000 order by salary --");
        //BooleanExpression implements Predicate
        //goe='greater than or equals'
        BooleanExpression booleanExpression = QEmployee.employee.salary.goe(3000);
        OrderSpecifier<Integer> orderSpecifier = QEmployee.employee.salary.asc();
        Iterable<Employee> employees2 = repo.findAll(booleanExpression, orderSpecifier);
        employees2.forEach(System.out::println);

        System.out.println(" -- employees in IT and Admin depts and salary between 3000 and 5000 --");
        BooleanExpression booleanExpression2 = QEmployee.employee.dept.in("IT", "Admin").and(
                QEmployee.employee.salary.between(3000, 5000));
                Iterable<Employee> employee3 = repo.findAll(booleanExpression2);
        employee3.forEach(System.out::println);

        System.out.println(" -- find employee Mike --");
        BooleanExpression booleanExpression3 = QEmployee.employee.name.eq("Mike");
        Optional<Employee> opt = repo.findOne(booleanExpression3);
        System.out.println(opt.get());
    }
    
    private List<Employee> createEmployees() {
        return Arrays.asList(
                Employee.create("Diana", "Admin", 2000),
                Employee.create("Mike", "Sales", 1000),
                Employee.create("Rose", "IT", 4000),
                Employee.create("Sara", "Admin", 3500),
                Employee.create("Randy", "Sales", 3000),
                Employee.create("Charlie", "IT", 2500)
        );
    }


}
