package com.leyou.auth.test;

import com.leou.common.pojo.UserInfo;
import com.leou.common.utils.JwtUtils;
import com.leou.common.utils.RsaUtils;
import org.junit.Before;
import org.junit.Test;

import java.security.PrivateKey;
import java.security.PublicKey;

public class JwtTest {

    private static final String pubKeyPath = "D:\\tmp\\rsa\\rsa.pub";

    private static final String priKeyPath = "D:\\tmp\\rsa\\rsa.pri";

    private PublicKey publicKey;

    private PrivateKey privateKey;

    @Test
    public void testRsa() throws Exception {
        RsaUtils.generateKey(pubKeyPath, priKeyPath, "234");
    }

    @Before
    public void testGetRsa() throws Exception {
        this.publicKey = RsaUtils.getPublicKey(pubKeyPath);
        this.privateKey = RsaUtils.getPrivateKey(priKeyPath);
    }

    @Test
    public void testGenerateToken() throws Exception {
        // 生成token
        String token = JwtUtils.generateToken(new UserInfo(20L, "jack"), privateKey, 5);
        System.out.println("token = " + token);
    }

    @Test
    public void testParseToken() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MjAsInVzZXJuYW1lIjoiamFjayIsImV4cCI6MTU4MDg5MjgxNX0.R9PkThe9tNAf6rhQd7HdBlc6Q09XE-sg1UcfKPinZmPAku8bJReIcw5ZP3jh-TjNjO1Krrbm8eSfIr6h5x7D04YotPfIn2I8nfa6gegx_qCHU0_tXK6ehocytDReOPNMOHVp3v0wzdwY9qdi3wqfJalXBJt0BoFMhnizI0CSsFM";

        // 解析token
        UserInfo user = JwtUtils.getInfoFromToken(token, publicKey);
        System.out.println("id: " + user.getId());
        System.out.println("userName: " + user.getUsername());
    }
}
