package org.javaboy.vhr.controller.system;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.javaboy.vhr.Service.HrService;
import org.javaboy.vhr.Service.RoleService;
import org.javaboy.vhr.model.Hr;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.Role;
import org.javaboy.vhr.utils.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Liu-PC
 * @version HrController: HrController, v 0.1 2020/5/26 20:26 Liu-PC Exp$
 */
@RestController
@RequestMapping("/system/hr")
public class HrController {
    @Autowired
    HrService hrService;
    @Autowired
    RoleService roleService;

    /**
     * hr与role是一对多的关系
     * @return
     */
    @GetMapping("/")
    public List<Hr> getAllHrs(){
        return hrService.getAllHrs(HrUtils.getCurrentHr().getId());
    }
    @PutMapping("/")
    public RespBean updateHr(@RequestBody Hr hr) {
        if (hrService.updateHr(hr) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @PutMapping("/role")
    public RespBean updateHrRole(Integer hrid,Integer[] rids){
        if (hrService.updateHrRole(hrid,rids)){
            return RespBean.ok("更新成功");
        }else {
            return RespBean.error("更新失败");
        }
    }
}
