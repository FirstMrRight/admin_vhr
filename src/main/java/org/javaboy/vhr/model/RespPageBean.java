package org.javaboy.vhr.model;

import lombok.Data;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * @author Liu-PC
 * 响应包装类
 * 分页数据数据量比较大，其他地方可能用到，这里是包装类
 * @version : RespPageBean, v 0.1 2020/6/26 10:47 Liu-PC Exp$
 */
@Data
public class RespPageBean {
    private Long total;
    private List<?> data;
}
