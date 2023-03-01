package com.sgqn.clubonline.config;

import com.sgqn.clubonline.web.fiter.TokenLoginFilter;
import com.sgqn.clubonline.web.security.TokenManger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author wspstart
 * @create 2023-02-28 10:26
 */
@Configuration
@EnableWebSecurity
// 方法级别开启权限认证功能
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private TokenManger tokenManger;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 权限认证对象[AuthenticationManager]注册到容器里面，其他类可以取到
     *
     * @return
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * 配置密码校验器
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * Security过滤器链配置
     *
     * @param http
     * @param authenticationManager
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationManager authenticationManager) throws Exception {
        http.authorizeRequests()
                // user目录下全部可以访问
                .antMatchers("/user/*").permitAll()
                .anyRequest().authenticated();
        //不通过Session获取SecurityContext
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        // 使用token认证可以关闭csrf
        http.csrf().disable();
        // 允许跨域
        http.cors();
        // 添加过滤器
        http.addFilterAt(new TokenLoginFilter(tokenManger, redisTemplate, authenticationManager) {{
            setFilterProcessesUrl("/club/login");
        }}, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}
