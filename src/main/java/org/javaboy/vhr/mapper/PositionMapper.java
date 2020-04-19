package org.javaboy.vhr.mapper;

import org.apache.ibatis.annotations.Param;
import org.javaboy.vhr.model.Position;
import org.javaboy.vhr.model.RespBean;

import java.util.List;

public interface PositionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);

    List<Position> getAllPosition();

    RespBean addPosition(Position position);

    Integer updateByOwn(Position position);

    Integer deleteByOwn(@Param("id") Integer id);

    Integer deletePositionsByIds(@Param("ids") Integer[] ids);
}
