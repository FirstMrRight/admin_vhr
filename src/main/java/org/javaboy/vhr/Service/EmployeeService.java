package org.javaboy.vhr.Service;

import org.javaboy.vhr.mapper.EmployeeMapper;
import org.javaboy.vhr.model.Employee;
import org.javaboy.vhr.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Liu-PC
 * @version : EmployeeService, v 0.1 2020/6/26 10:57 Liu-PC Exp$
 */
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeeMapper;

    public RespPageBean getEmployeeByPage(Integer page, Integer size, String keyword) {
        if (page != null && size != null) {
            page = (page - 1) * size;
        }
        List<Employee> data = employeeeMapper.getEmployeeByPage(page, size, keyword);
        Long total = employeeeMapper.getTotal(keyword);
        RespPageBean bean = new RespPageBean();
        bean.setData(data);
        bean.setTotal(total);
        return bean;
    }

    public Integer addEmployee(Employee employee) {
        return employeeeMapper.insertSelective(employee);
    }
}
