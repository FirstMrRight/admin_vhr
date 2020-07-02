package org.javaboy.vhr;

import org.javaboy.vhr.Service.LogService;
import org.javaboy.vhr.model.TSysLogs;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * @author Liu-PC
 * @version : test, v 0.1 2020/7/1 21:19 Liu-PC Exp$
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VhrApplication.class)

public class test{
    @Autowired
    private LogService logService;
    @Test
    public void test(){
        TSysLogs ts = new TSysLogs();
        ts.setIp("111");
        ts.setId(UUID.randomUUID().toString());
//        ts.setId("1");
        logService.inserts(ts);
    }


}
