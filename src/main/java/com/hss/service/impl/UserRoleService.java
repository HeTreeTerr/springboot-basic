package com.hss.service.impl;

import com.hss.bean.User;
import com.hss.mapper.RoleMapper;
import com.hss.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userMapper.findUserByUserName(userName);
        if(user == null){
            throw new UsernameNotFoundException("账号不存在");
        }
        user.setRoles(roleMapper.findRolesByUid(user.getId()));
        return user;
    }
}
