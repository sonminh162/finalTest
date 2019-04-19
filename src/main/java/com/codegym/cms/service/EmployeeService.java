package com.codegym.cms.service;

import com.codegym.cms.model.Employee;
import com.codegym.cms.model.Group;

public interface EmployeeService {
    Iterable<Employee> findAll();
    Employee findById(Long id);
    void save(Employee employee);
    void remove(Long id);
    Iterable<Employee> findAllByGroup(Group group);
}
