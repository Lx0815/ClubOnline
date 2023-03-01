package com.sgqn.clubonline.security;

import com.sgqn.clubonline.web.security.TokenManger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author wspstart
 * @create 2023-02-27 19:12
 */
@SpringBootTest
public class TokenTest {

    @Autowired
    public TokenManger tokenManger;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testToken(){
        String token = tokenManger.createToken("张三");
        System.out.println(token);
    }

    @Test
    void testPasswordEncode(){
        String encode = passwordEncoder.encode("123");
        System.out.println(encode);
    }
}
