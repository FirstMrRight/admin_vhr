package org.javaboy.vhr.Service;

import org.javaboy.vhr.mapper.DepartmentMapper;
import org.javaboy.vhr.model.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Liu-PC
 * @version DepartmentService: DepartmentService, v 0.1 2020/5/20 21:08 Liu-PC Exp$
 */
@Service
public class DepartmentService {
    @Autowired
    DepartmentMapper departmentMapper;

    /**
     * 递归查询
     * pid:递归值
     * @param
     * @return
     */
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartmentsByParentId(-1);
    }

    public void addDep(Department dep) {
        dep.setEnabled(true);
        departmentMapper.addDep(dep);
    }
    public void deleteDepById(Department dep) {
        departmentMapper.deleteDepById(dep);
    }
}
