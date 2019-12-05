package com.ideamaven.admin.service;

import com.ideamaven.commons.model.Employee;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeService {
    List<Employee> getAllEmployeeList();
    Employee getEmp(Integer id);
    void deleteBatch(List<Integer> ids);
    void deleteEmp(Integer id);
    void saveEmp(Employee employee);
    void updateEmp(Employee employee);
    boolean checkUser(String empName);
}

