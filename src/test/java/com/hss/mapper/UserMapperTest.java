package com.hss.mapper;

import com.hss.bean.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserMapper userMapper;

    @Test
    public void findUserList() {
        List<User> userList = userMapper.findUserList();
        for(User user : userList){
            System.out.println(user.getName());
        }
    }

    @Test
    public void registeredUser(){
        User user = new User();
        user.setUserName("hss");
        user.setName("何森森");
        user.setBirthday(new Date());
        user.setHeadImgUrl("lalal.png");
        user.setMobileNumber("18628466845");
        user.setPassword("11111111");
        user.setSex(1);
        user.setTfAdmin(false);
        user.setCreateUser("to_dou");
        user.setUpdateUser("to_dou");
        userMapper.registeredUser(user);
        logger.info("registeredUser id------------>"+user.getId());
    }

    @Test
    public void updateUserInfo(){
        User user = new User();
        user.setId(5L);
        user.setSex(1);
        userMapper.updateUserInfo(user);
    }
}