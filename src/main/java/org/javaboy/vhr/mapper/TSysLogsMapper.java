package org.javaboy.vhr.mapper;

import org.javaboy.vhr.model.TSysLogs;
import org.springframework.stereotype.Component;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author liutx
 * @since 2020-07-01
 */
@Component
public interface TSysLogsMapper{
    int insert(TSysLogs tSysLogs);
}
