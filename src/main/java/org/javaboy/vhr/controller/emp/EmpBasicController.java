package org.javaboy.vhr.controller.emp;

import lombok.extern.slf4j.Slf4j;
import org.javaboy.vhr.Service.EmployeeService;
import org.javaboy.vhr.aop.SysLog;
import org.javaboy.vhr.model.Employee;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author Liu-PC
 * @version : EmpBasicController, v 0.1 2020/6/26 10:45 Liu-PC Exp$
 */
@Slf4j
@RestController
@RequestMapping("/emp/basic")
public class EmpBasicController {
    @Autowired
    EmployeeService employeeService;

    /**
     * 默认查询第一页，一页十个数据
     *
     * @param page
     * @param size
     * @return
     */
    @SysLog(value = "测试", type = "emp")
    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size,String keyword) {
        return employeeService.getEmployeeByPage(page, size, keyword);
    }

    @PostMapping("/")
    public RespBean addEmployee(@RequestBody Employee employee){
        if (employeeService.addEmployee(employee)==1){
            return RespBean.ok("添加成功");
        }else {
            return RespBean.error("添加失败");
        }
    }
}
