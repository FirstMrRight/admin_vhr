package org.javaboy.vhr.Service;

import org.javaboy.vhr.mapper.NationMapper;
import org.javaboy.vhr.model.Nation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Liu-PC
 * @version : NationService, v 0.1 2020/8/15 9:41 Liu-PC Exp$
 */
@Service
public class NationService {

    @Autowired
    private NationMapper nationMapper;
    public List<Nation> getAllNations() {
        return nationMapper.getAllNations();
    }
}
