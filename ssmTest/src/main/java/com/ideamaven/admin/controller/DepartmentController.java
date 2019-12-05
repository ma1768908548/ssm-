package com.ideamaven.admin.controller;

import com.ideamaven.admin.service.DepartmentService;
import com.ideamaven.admin.service.EmployeeService;
import com.ideamaven.commons.model.Department;
import com.ideamaven.commons.model.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 处理和部门有关的请求
 */
@Controller
public class DepartmentController {
    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    @Autowired
    public DepartmentController(DepartmentService departmentService, EmployeeService employeeService) {
        this.departmentService = departmentService;
        this.employeeService = employeeService;
    }

    /**
     * 返回所有的部门信息
     */
    @RequestMapping("/depts")
    @ResponseBody
    public Msg getDepts(){
        List<Department> list=departmentService.getAllDepartmentList();
        return Msg.success().add("depts",list);
    }
}
