package org.javaboy.vhr.Service;

import com.alibaba.druid.util.DaemonThreadFactory;
import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.mapper.EmployeeMapper;
import org.javaboy.vhr.model.Employee;
import org.javaboy.vhr.model.RespPageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author Liu-PC
 * @version : EmployeeService, v 0.1 2020/6/26 10:57 Liu-PC Exp$
 */
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeeMapper;
    //设置日期格式
    SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
    SimpleDateFormat monthFormat = new SimpleDateFormat("MM");
    //计算结果保留两位小数
    DecimalFormat decimalFormat = new DecimalFormat("##.00");

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
        Date beginContract = employee.getBeginDate();
        Date endContract = employee.getEndContract();
        double month = (Double.parseDouble(yearFormat.format(endContract)) - Double.parseDouble(yearFormat.format(beginContract))) * 12 +
                (Double.parseDouble(monthFormat.format(endContract)) - Double.parseDouble(monthFormat.format(beginContract)));
        employee.setContractTerm(Double.parseDouble(decimalFormat.format(month / 12)));
        return employeeeMapper.insertSelective(employee);
    }

    public Integer maxWorkId() {
        return employeeeMapper.getMaxWorkId();
    }

    public Integer deleteEmpById(Integer id) {
        return employeeeMapper.deleteByPrimaryKey(id);
    }

    public Integer updateEmp(Employee employee) {
        return employeeeMapper.updateByPrimaryKey(employee);
    }

    public Integer addEmps(@Param("list") List<Employee> list) {
        return employeeeMapper.addEmps(list);
    }
}
