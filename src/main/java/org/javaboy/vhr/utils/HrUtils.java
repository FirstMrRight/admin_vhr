package org.javaboy.vhr.utils;

import org.javaboy.vhr.model.Hr;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Liu-PC
 * @version HrUtils: HrUtils, v 0.1 2020/5/26 20:33 Liu-PC Exp$
 */

public class HrUtils {
    /**
     * 当前登录信息存储在SecurityContextHolder中
     * 通过SecurityContextHolder获取当前登录信息
     * @return
     */
    public static Hr getCurrentHr(){
        return ((Hr) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}
