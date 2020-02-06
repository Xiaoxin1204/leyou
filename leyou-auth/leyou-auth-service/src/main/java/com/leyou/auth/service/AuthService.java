package com.leyou.auth.service;

import com.leou.common.pojo.UserInfo;
import com.leou.common.utils.JwtUtils;
import com.leyou.auth.client.UserClient;
import com.leyou.auth.config.JwtProperties;
import com.leyou.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserClient userClient;

    @Autowired
    private JwtProperties properties;

    public String accredit(String username, String password) {
        //根据用户名和密码查询
        User user = userClient.queryUser(username, password);
        //判断User
        if (user == null) {
            return null;
        }
        //通过jwtUtils生成jwt类型的token
        UserInfo userInfo = new UserInfo();
        userInfo.setId(user.getId());
        userInfo.setUsername(user.getUsername());
        try {
            return JwtUtils.generateToken(userInfo, properties.getPrivateKey(), properties.getExpire());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
