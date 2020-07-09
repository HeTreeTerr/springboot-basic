package com.hss.mapper;

import com.hss.bean.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleMapperTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void findRolesByUid() {
        List<Role> roleList = roleMapper.findRolesByUid(7L);
        for(Role role : roleList){
            logger.info("role Name -->"+role.getName());
        }
    }

    @Test
    public void addRole() {
        Role role = new Role();
        role.setName("USER");
        role.setNameZh("用户");
        roleMapper.addRole(role);
        logger.info("新角色编号："+role.getId());
    }
}