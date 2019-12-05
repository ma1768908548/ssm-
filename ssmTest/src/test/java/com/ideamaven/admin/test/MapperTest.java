package com.ideamaven.admin.test;

import com.ideamaven.commons.mapper.DepartmentMapper;
import com.ideamaven.commons.mapper.EmployeeMapper;
import com.ideamaven.commons.model.Employee;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * 测试dao层的工作
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext-*.xml"})
public class MapperTest {
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    SqlSession sqlSession;
    @Test
    public void testCRUD(){

        //1.插入几个部门
        //departmentMapper.insertSelective(new Department(null,"开发部"));
        //departmentMapper.insertSelective(new Department(null,"测试部"));

        //2.生成员工数据，测试员工插入
        //employeeMapper.insertSelective(new Employee(1001,"小马","男","980212@qq.com",1));

        //3.批量插入员工，使用可执行批操作的sqlsession
        EmployeeMapper mapper=sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0;i<10;i++){
            String uid=UUID.randomUUID().toString().substring(0,5)+i;
            mapper.insertSelective(new Employee(null,uid,"女",uid+"@1234",2));
        }
        System.out.println("批量完成");

    }

}
