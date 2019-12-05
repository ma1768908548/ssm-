package com.ideamaven.admin.service;

import com.ideamaven.commons.model.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    List<Department> getAllDepartmentList();
}
