package org.javaboy.vhr.controller.system.basic;

import org.javaboy.vhr.Service.DepartmentService;
import org.javaboy.vhr.model.Department;
import org.javaboy.vhr.model.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Liu-PC
 * @version DepartmentController: DepartmentController, v 0.1 2020/5/20 20:54 Liu-PC Exp$
 */

@RestController
@RequestMapping("/system/basic/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;
    @GetMapping("/")
    public List<Department> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @PostMapping("/")
    public RespBean addDep(@RequestBody Department dep){
        departmentService.addDep(dep);
        if (dep.getResult() == 1){
            return RespBean.ok("添加成功",dep);
        }else {
            return RespBean.error("添加失败",dep);
        }
    }

}
