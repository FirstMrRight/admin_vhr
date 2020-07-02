package org.javaboy.vhr.controller.emp;

import org.javaboy.vhr.Service.EmployeeService;
import org.javaboy.vhr.aop.SysLog;
import org.javaboy.vhr.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



/**
 * @author Liu-PC
 * @version : EmpBasicController, v 0.1 2020/6/26 10:45 Liu-PC Exp$
 */
@RestController
@RequestMapping("/emp/basic")
public class EmpBasicController {
    @Autowired
    EmployeeService employeeService;
    /**
     * 默认查询第一页，一页十个数据
     * @param page
     * @param size
     * @return
     */
    @SysLog(value = "测试",type = "emp")
    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "10") Integer size){
        return employeeService.getEmployeeByPage(page,size);
    }
}
