package com.hss.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.pagehelper.PageInfo;
import com.hss.bean.User;
import com.hss.service.UserService;
import com.hss.util.Msg;
import com.hss.util.MyMD5Util;
import com.hss.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;

@RestController
@RequestMapping("/sign")
public class SignController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUserSignInfo",method = {RequestMethod.POST,RequestMethod.GET})
    public Msg getUserInfo(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        User signUser = userService.findUserByUserName(principal.getName());
        return Msg.success().add("signUserInfo",signUser);
    }

}
