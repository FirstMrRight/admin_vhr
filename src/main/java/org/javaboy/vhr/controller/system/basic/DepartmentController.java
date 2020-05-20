package org.javaboy.vhr.controller.system.basic;

import org.javaboy.vhr.Service.DepartmentService;
import org.javaboy.vhr.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
