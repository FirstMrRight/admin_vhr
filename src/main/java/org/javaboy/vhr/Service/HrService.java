package org.javaboy.vhr.Service;

import org.javaboy.vhr.mapper.HrMapper;
import org.javaboy.vhr.mapper.HrRoleMapper;
import org.javaboy.vhr.model.Hr;
import org.javaboy.vhr.utils.HrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class HrService implements UserDetailsService {
@Autowired HrMapper hrMapper;
@Autowired
    HrRoleMapper hrRoleMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /*
        * 查询，返回一个Hr
        * */
        Hr hr = hrMapper.loadUserByUsername(username);
        if (hr==null){
            throw  new UsernameNotFoundException("用户名不存在");
        }
        hr.setRoles(hrMapper.getHrRolesById(hr.getId()));
        return hr;
    }

    /**
     * 都是Hr,查询的时候把自己去掉
     * 需要把自己的id传进去
     * @return
     * @param keywords
     */
    public List<Hr> getAllHrs(String keywords) {
        return hrMapper.getAllHrs(HrUtils.getCurrentHr().getId(),keywords);
    }

    public Integer updateHr(Hr hr) {
        return hrMapper.updateByPrimaryKeySelective(hr);
    }

    @Transactional
    //先删除hr表中的权限数据再添加新数据
    public Boolean updateHrRole(Integer hrid,Integer[] rids) {
        hrRoleMapper.deleteByHrId(hrid);
        return hrRoleMapper.addRole(hrid,rids) == rids.length;
    }

    public Integer deleteHr(Integer id) {
       return hrMapper.deleteByPrimaryKey(id);
    }
}
