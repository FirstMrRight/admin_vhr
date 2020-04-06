package org.javaboy.vhr.controller;


import org.javaboy.vhr.Service.MenuService;
import org.javaboy.vhr.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/system/config")
public class SystemConfigController {
    @Autowired
    private MenuService menuService;
    /*前端传过来的数据要校验，不要用前端传过来的数据*/
    @GetMapping("/menu")
    public List<Menu> getMenusByHrId(){
        return menuService.getMenuByHrId();
    }
}
