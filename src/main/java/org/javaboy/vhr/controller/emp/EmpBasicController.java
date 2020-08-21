package org.javaboy.vhr.controller.emp;

import javafx.geometry.Pos;
import lombok.extern.slf4j.Slf4j;
import org.javaboy.vhr.Service.*;
import org.javaboy.vhr.aop.SysLog;
import org.javaboy.vhr.model.*;
import org.javaboy.vhr.utils.POIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * @author Liu-PC
 * @version : EmpBasicController, v 0.1 2020/6/26 10:45 Liu-PC Exp$
 */
@Slf4j
@RestController
@RequestMapping("/emp/basic")
public class EmpBasicController {
    @Resource
    private EmployeeService employeeService;
    @Resource
    private NationService nationService;
    @Resource
    private PoliticsstatusService politicsstatusService;
    @Resource
    private JobLevelService jobLevelService;
    @Resource
    private PositionService positionService;

    @Resource
    private DepartmentService departmentService;

    /**
     * 默认查询第一页，一页十个数据
     *
     * @param page
     * @param size
     * @return
     */
//    @SysLog(value = "测试", type = "emp")
    @GetMapping("/")
    public RespPageBean getEmployeeByPage(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer size, String keyword) {
        return employeeService.getEmployeeByPage(page, size, keyword);
    }

    @PostMapping("/")
    public RespBean addEmployee(@RequestBody Employee employee) {
        if (employeeService.addEmployee(employee) == 1) {
            return RespBean.ok("添加成功");
        } else {
            return RespBean.error("添加失败");
        }
    }

    @PutMapping("/")
    public RespBean updateEmp(@RequestBody Employee employee) {
        if (employeeService.updateEmp(employee) == 1) {
            return RespBean.ok("更新成功");
        } else {
            return RespBean.error("更新失败");
        }

    }

    @DeleteMapping("/{id}")
    public RespBean deleteEmpById(@PathVariable Integer id) {
        if (employeeService.deleteEmpById(id) == 1) {
            return RespBean.ok("删除成功");
        } else {
            return RespBean.error("删除失败");
        }
    }

    @GetMapping("/nations")
    public List<Nation> getAllNation() {
        return nationService.getAllNations();
    }

    @GetMapping("/politicsStatus")
    public List<Politicsstatus> getAllPoliticsstatus() {
        return politicsstatusService.getAllPoliticsstatus();
    }

    @GetMapping("/jobLevels")
    public List<JobLevel> getAllJobLevels() {
        return jobLevelService.getAlljobLevels();
    }

    @GetMapping("/positions")
    public List<Position> getAllPositions() {
        return positionService.getAllPosition();
    }

    /**
     * 获取最大的workId，新增人员，workId无法选择，只能自增长
     *
     * @return
     */
    @GetMapping("/maxWorkId")
    public RespBean maxWorkId() {
        RespBean respBean = RespBean.build().setStatus(200)
                .setObj(String.format("%08d", employeeService.maxWorkId() + 1));
        return respBean;
    }

    /**
     * execl导出用户列表
     * @return
     */
    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData() {
        List<Employee> list = (List<Employee>) employeeService.getEmployeeByPage(null, null, null).getData();
        return POIUtils.employee2Excel(list);
    }
}
