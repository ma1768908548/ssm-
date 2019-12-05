package com.ideamaven.admin.service.impl;

import com.ideamaven.admin.service.EmployeeService;
import com.ideamaven.commons.mapper.EmployeeMapper;
import com.ideamaven.commons.model.Employee;
import com.ideamaven.commons.model.EmployeeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeMapper employeeMapper;

    @Autowired
    public EmployeeServiceImpl(EmployeeMapper employeeMapper) {
        this.employeeMapper = employeeMapper;
    }


    //查询所有员工
    @Override
    public List<Employee> getAllEmployeeList() {
        EmployeeExample example = new EmployeeExample();
        example.setOrderByClause("emp_id");
        return employeeMapper.selectByExampleWithDept(example);
    }

    @Override
    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    /**
     * 员工更新
     * @param employee
     */
    @Override
    public void updateEmp(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    @Override
    public Employee getEmp(Integer id) {
        Employee employee=employeeMapper.selectByPrimaryKey(id);
        return employee;
    }

    @Override
    public void deleteBatch(List<Integer> ids) {
        EmployeeExample example=new EmployeeExample();
        EmployeeExample.Criteria criteria=example.createCriteria();
        //delete form xxx where emp_id in()
        criteria.andEmpIdIn(ids);
        employeeMapper.deleteByExample(example);
    }

    @Override
    public void deleteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    /**
     * 检验用户名是否可用
     * @param empName
     * @return
     */
    @Override
    public boolean checkUser(String empName) {
        EmployeeExample example=new EmployeeExample();
        EmployeeExample.Criteria criteria=example.createCriteria();
        criteria.andEmpNameEqualTo(empName);
        long count=employeeMapper.countByExample(example);
        return count==0;
    }
}
