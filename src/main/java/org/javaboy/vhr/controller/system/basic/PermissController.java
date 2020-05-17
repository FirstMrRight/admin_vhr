package org.javaboy.vhr.controller.system.basic;

import org.javaboy.vhr.Service.MenuService;
import org.javaboy.vhr.Service.RoleService;
import org.javaboy.vhr.model.Menu;
import org.javaboy.vhr.model.MenuRole;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/permiss")
public class PermissController {
    @Autowired
    RoleService roleService;
    @Autowired
    MenuService menuService;
    @Autowired


    @GetMapping("/menus")
    public List<Menu> getAllMenus(){
        return menuService.getAllMenus();
    }

    @GetMapping("/")
    public List<Role> getRole(){
        return roleService.getAllRoles();
    }

    /**
     * 根据rid查询mid
     * @return
     */
    @GetMapping("/mids/{rid}")
    public List<Integer> getMidsByRid(@PathVariable Integer rid){
        return menuService.getMidsByRid(rid);
    }
//
//    @PutMapping("/")
//    public RespBean updateMenuRole(Integer rid,Integer[] mids){
//        if (menuService.updateMenuRole(rid,mids)){
//            return RespBean.ok("更新成功");
//        }else {
//            return RespBean.error("更新失败");
//        }
//    }

    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        if (menuService.updateMenuRole(rid, mids)) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }

}
