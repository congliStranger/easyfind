package com.stranger.easyfindboot.config;

import com.stranger.easyfindboot.security.AuthenticationAccessDeniedHandler;
import com.stranger.easyfindboot.security.CustomMetadataSource;
import com.stranger.easyfindboot.security.UrlAccessDecisionManager;
import com.stranger.easyfindboot.service.ISysUserService;
import com.stranger.easyfindboot.service.impl.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

    @Autowired
    private CustomMetadataSource customMetadataSource;

    @Autowired
    private UrlAccessDecisionManager urlAccessDecisionManager;

    /**
     * solution to resovle the problem "There is no PasswordEncoder mapped for the id "null"
     * @return
     */
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
    /**
     *配置用户登录的信息（用户名和密码是否正确等）
     * 在Spring Security Config中,AuthenticationManagerBuilder是一个SecurityBuilder,
     * 其目的是根据所设置的属性构建一个AuthenticationManager(实现类使用ProviderManager)。
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
//        auth.userDetailsService(sysUserService).passwordEncoder(NoOpPasswordEncoder.getInstance());
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("user1").
//                password(new BCryptPasswordEncoder().encode("123456")).roles("USER");
    }

    /**
     *
     * @param web 配置Spring Security的Filter链
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    /**
     * 通过拦截器来保护服务器请求
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }
}
