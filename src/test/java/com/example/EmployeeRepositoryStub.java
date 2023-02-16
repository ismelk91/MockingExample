package com.example;

import java.util.Arrays;
import java.util.List;

public class EmployeeRepositoryStub implements EmployeeRepository {
    @Override
    public List<Employee> findAll() {
        Employee employee1 = new Employee("1", 30000);
        Employee employee2 = new Employee("2", 35000);
        return Arrays.asList(employee1, employee2);
    }

    @Override
    public Employee save(Employee e) {
        return null;
    }

}
