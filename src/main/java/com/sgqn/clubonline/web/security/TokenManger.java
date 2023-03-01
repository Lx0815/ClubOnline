package com.sgqn.clubonline.web.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenManger {

    // 设置token的密钥
    private final String securityKey = "club";

    // 过期时间
    private final long exp = 60 * 60 * 24 * 7 * 1000;


    /**
     * 使用jwt生成token
     *
     * @param userDetailId
     * @return
     */
    public String createToken(Object userDetailId) {
        return Jwts.builder().setSubject(userDetailId.toString())
                // 设置过期时间
                .setExpiration(new Date(System.currentTimeMillis() + exp))
                .signWith(SignatureAlgorithm.HS256, securityKey).compact();
    }

    /**
     * 使用jwt解析token获得用户名
     *
     * @param token
     * @return
     */
    public String getUsernameToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(securityKey).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}