package org.javaboy.vhr.controller.system.basic;

import com.sun.org.apache.regexp.internal.RE;
import javafx.geometry.Pos;
import org.javaboy.vhr.Service.PositionService;
import org.javaboy.vhr.model.Position;
import org.javaboy.vhr.model.RespBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/system/basic/pos")
public class PositionController {
    @Autowired
    PositionService positionService;
    @GetMapping("/")
    public List<Position> getAllPositions(){
        return positionService.getAllPosition();
    }

    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        if (positionService.addPosition(position)==1){
            return RespBean.ok("添加成功");
        }else {
            return RespBean.error("添加失败");
        }
    }

    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position){
        if (positionService.updatePositions(position)==1){
            return RespBean.ok("更新成功");
        }else {
            return  RespBean.error("更新失败");
        }
    }

    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id){
        if (positionService.deletePositionById(id)==1){
            return RespBean.ok("删除成功");
        }else {
            return RespBean.error("删除失败");
        }
    }

    /**
     * 批量删除
     */

    @DeleteMapping("/")
    public RespBean deletePositionByIds(Integer[] ids){
        if (positionService.deletePositionsByIds(ids)==ids.length){
            return RespBean.ok("删除成功");
        }else {
           return RespBean.error("删除失败");
        }
    }

}
