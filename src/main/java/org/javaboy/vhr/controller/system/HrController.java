package org.javaboy.vhr.controller.system;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.javaboy.vhr.Service.HrService;
import org.javaboy.vhr.model.Hr;
import org.javaboy.vhr.model.RespBean;
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

    /**
     * hr与role是一对多的关系
     * @return
     */
    @GetMapping("/")
    public List<Hr> getAllHrs(){
        return hrService.getAllHrs(HrUtils.getCurrentHr().getId());
    }

    //todo: 数据库异常,操作失败
    @PutMapping("/")
    public RespBean updateHr(@RequestBody Hr hr) {
        if (hrService.updateHr(hr) == 1) {
            return RespBean.ok("更新成功!");
        }
        return RespBean.error("更新失败!");
    }
}
