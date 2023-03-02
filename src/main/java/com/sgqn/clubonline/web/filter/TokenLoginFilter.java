package com.sgqn.clubonline.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgqn.clubonline.common.utils.ResponseUtil;
import com.sgqn.clubonline.pojo.User;
import com.sgqn.clubonline.web.response.Response;
import com.sgqn.clubonline.web.response.ResponseCode;
import com.sgqn.clubonline.web.security.TokenManger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


@Slf4j
public class TokenLoginFilter extends UsernamePasswordAuthenticationFilter {

    private TokenManger tokenManger;

    private RedisTemplate<String, String> redisTemplate;

    private AuthenticationManager authenticationManager;


    public TokenLoginFilter(TokenManger tokenManger, RedisTemplate<String, String> redisTemplate, AuthenticationManager authenticationManager) {
        this.tokenManger = tokenManger;
        this.redisTemplate = redisTemplate;
        this.authenticationManager = authenticationManager;
        // 只运行post请求,经过这个filter
        this.setPostOnly(true);
        // 登录的路径和请求方式
        this.setRequiresAuthenticationRequestMatcher(
                new AntPathRequestMatcher("/login", "POST"));
    }

    /**
     * 执行认证的方法
     *
     * @param request
     * @param response
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 获取表单提供的数据
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            User user = objectMapper.readValue(request.getInputStream(), User.class);
            // 校验 == 认证的过程
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(),
                    user.getPassword(), new ArrayList<>());
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 认证成功以后调用的方法
     *
     * @param request
     * @param response
     * @param chain
     * @param authResult
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 得到用户名
        User currUser = (User) authResult.getPrincipal();
        Integer userId = currUser.getId();
        //生成token
        String token = tokenManger.createToken(userId);
        // 存入到redis  username : 权限
        redisTemplate.opsForHash().put("USER:token", userId.toString(), token);

        // 返回token,在响应体中写入
        ResponseUtil.out(response, new Response<>(ResponseCode.CERTIFICATION_SUCCEEDED,
                new HashMap<String, String>() {{
                    put("token", token);
                }}
        ));
    }

    /**
     * 认证失败后调用的方法
     *
     * @param request
     * @param response
     * @param failed
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ResponseUtil.out(response, new Response<>(ResponseCode.CERTIFICATION_FAIL, failed.getMessage()));
    }
}