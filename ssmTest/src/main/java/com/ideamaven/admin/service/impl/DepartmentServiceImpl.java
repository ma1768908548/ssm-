package com.ideamaven.admin.service.impl;

import com.ideamaven.admin.service.DepartmentService;
import com.ideamaven.commons.mapper.DepartmentMapper;
import com.ideamaven.commons.model.Department;
import com.ideamaven.commons.model.DepartmentExample;
import com.ideamaven.commons.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentMapper departmentMapper;

    @Autowired
    public DepartmentServiceImpl(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Override
    public List<Department> getAllDepartmentList() {
        DepartmentExample example=new DepartmentExample();
        List<Department> list=departmentMapper.selectByExample(example);
        return list;
    }

}
