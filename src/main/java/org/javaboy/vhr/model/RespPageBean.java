package org.javaboy.vhr.model;

import lombok.Data;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * @author Liu-PC
 * @version : RespPageBean, v 0.1 2020/6/26 10:47 Liu-PC Exp$
 */
@Data
public class RespPageBean {
    private Long total;
    private List<?> data;
}
