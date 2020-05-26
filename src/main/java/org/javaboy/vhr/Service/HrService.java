package org.javaboy.vhr.Service;

import org.javaboy.vhr.mapper.HrMapper;
import org.javaboy.vhr.model.Hr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HrService implements UserDetailsService {
@Autowired HrMapper hrMapper;
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
     * @param id
     */
    public List<Hr> getAllHrs(Integer id) {
        return hrMapper.getAllHrs(id);
    }
}
