package org.javaboy.vhr.Service;


import org.javaboy.vhr.mapper.PositionMapper;
import org.javaboy.vhr.model.Position;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;

@Service
public class PositionService {
@Autowired
    PositionMapper positionMapper;
    public List<Position> getAllPosition() {
       return positionMapper.getAllPosition();
    }

    public Integer addPosition(@RequestBody Position position) {
        position.setCreatedate(new Date());
        position.setEnabled(true);
        return positionMapper.insertSelective(position);
    }

    public Integer updatePositions(Position position) {
//        return positionMapper.updateByPrimaryKeySelective(position);
        return positionMapper.updateByOwn(position);
    }

    public Integer deletePositionById(@PathVariable Integer id){
        return positionMapper.deleteByOwn(id);
    }
}
