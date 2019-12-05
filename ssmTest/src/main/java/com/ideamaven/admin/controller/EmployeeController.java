package com.ideamaven.admin.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ideamaven.admin.service.EmployeeService;
import com.ideamaven.commons.model.Employee;

import com.ideamaven.commons.model.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    /**
     * 单个和批量
     * @param ids
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/emp/{ids}",method=RequestMethod.DELETE)
    public Msg deleteEmp(@PathVariable("ids") String ids){
        //批量删除
        if(ids.contains("-")){
            List<Integer> del_ids=new ArrayList<>();
            String[] str_ids = ids.split("-");
            for (String string:str_ids) {
                del_ids.add(Integer.parseInt(string));
            }
            employeeService.deleteBatch(del_ids);
            //单个删除
        }else{
            Integer id=Integer.parseInt(ids);
            employeeService.deleteEmp(id);
        }
        return Msg.success();
    }


    /**
     * 员工更新
     * @param employee
     * @return
     */
    @RequestMapping(value = "/emp/{empId}",method = RequestMethod.PUT)
    @ResponseBody
    public Msg updateEmp(Employee employee){
        System.out.println("将要更新的员工数据"+employee);
        employeeService.updateEmp(employee);
        return Msg.success();
    }

    /**
     * 根据id查询员工
     * @param id
     * @return
     */
    @RequestMapping(value = "/emp/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg getEmp(@PathVariable("id") Integer id){
        Employee employee=employeeService.getEmp(id);
        return Msg.success().add("emp",employee);
    }

    /**
     * 检查用户名是否可用
     *
     * @param empName
     * @return
     */
    @RequestMapping("/checkUser")
    @ResponseBody
    public Msg checkUser(@RequestParam("empName") String empName) {
        //先判断用户名是否是合法的表达式
        String regx = "(^[a-zA-Z0-9_-]{6,16}$)|([\u2E80-\u9FFF]{2,5}$)";
        if (!empName.matches(regx)) {
            return Msg.fail().add("value_msg", "用户名必须是2-5位中文或者6-16位英文和数字的组合");
        }
        //数据库用户名重复校验
        boolean b = employeeService.checkUser(empName);
        if (b) {
            return Msg.success();
        } else {
            return Msg.fail().add("value_msg", "用户名不可用");
        }
    }


    /**
     * 使用json实现分页查询4
     *
     * 导入jackson包
     */
    @RequestMapping("/emps")
    @ResponseBody
    public Msg getEmpsWithJson(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, Model model) {
        PageHelper.startPage(pageNum, pageSize);
        List<Employee> emps = employeeService.getAllEmployeeList();
        return Msg.success().add("pageInfo", new PageInfo<>(emps));
    }

    /**
     * 查询员工数据（分页查询）
     */
    //@RequestMapping("/emps")
    public String getEmps(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize, Model model) {
        //引入PageHelper查询插件
        //在查询前传入页码以及每页的大小
        PageHelper.startPage(pageNum, pageSize);
        List<Employee> emps = employeeService.getAllEmployeeList();
        //使用PageInfo包装查询后的结果，只需要给PageInfo交给页面
        //封装了详细的分页信息，包括有我们查询的分页数据，连续传入5页
        PageInfo page = new PageInfo(emps);
        model.addAttribute("pageInfo", page);
        return "list";
    }

    /**
     * 员工保存
     * 1.支持JSR303校验
     * 2.导入Hibernate-Validator
     *
     * @return
     */
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    @ResponseBody
    public Msg saveEmp(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            //校验失败 返回错误信息 在模态框中显示校验失败的错误信息
            Map<String, Object> map = new HashMap<>();
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError fieldError : errors) {
                System.out.println("错误的字段名:" + fieldError.getField());
                System.out.println("错误信息：" + fieldError.getDefaultMessage());
                map.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            return Msg.fail().add("errorFields",map);
        } else {
            employeeService.saveEmp(employee);
            return Msg.success();
        }
    }

}
