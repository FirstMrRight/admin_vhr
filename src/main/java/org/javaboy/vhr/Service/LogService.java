package org.javaboy.vhr.Service;

import org.javaboy.vhr.mapper.TSysLogsMapper;
import org.javaboy.vhr.model.TSysLogs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Liu-PC
 * @version : LogService, v 0.1 2020/7/2 22:42 Liu-PC Exp$
 */
@Service
public class LogService {
    @Autowired
    TSysLogsMapper tSysLogsMapper;
    public int inserts(TSysLogs tSysLogs){
        return tSysLogsMapper.insert(tSysLogs);
    }
}
