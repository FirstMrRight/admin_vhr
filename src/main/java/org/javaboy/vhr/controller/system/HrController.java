package org.javaboy.vhr.controller.system;

import org.javaboy.vhr.Service.HrService;
import org.javaboy.vhr.model.Hr;
import org.javaboy.vhr.utils.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
