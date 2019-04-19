package com.codegym.cms.repository;

import com.codegym.cms.model.Employee;
import com.codegym.cms.model.Group;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Long> {
    Iterable<Employee> findAllByGroup(Group group);
}
