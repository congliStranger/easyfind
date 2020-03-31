package com.stranger.easyfindboot.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stranger.easyfindboot.security.AuthenticationAccessDeniedHandler;
import com.stranger.easyfindboot.security.CustomMetadataSource;
import com.stranger.easyfindboot.security.RespBean;
import com.stranger.easyfindboot.security.UrlAccessDecisionManager;
import com.stranger.easyfindboot.service.ISysUserService;
import com.stranger.easyfindboot.service.impl.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.AuthenticationEntryPointFailureHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


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
        auth.userDetailsService(sysUserService);
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
        web.ignoring().antMatchers("/index.html","/static/**","/login_p");
    }

    /**
     * 通过拦截器来保护服务器请求
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        http.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {

            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                o.setSecurityMetadataSource(customMetadataSource);
                o.setAccessDecisionManager(urlAccessDecisionManager);
                return o;
            }
        }).and().
                formLogin().loginPage("/login_p").loginProcessingUrl("/login").
                usernameParameter("username").passwordParameter("password").failureHandler(new AuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                 httpServletResponse.setContentType("application/json;charset=utf-8");
                 RespBean respBean=null;
                 if(e instanceof BadCredentialsException|| e instanceof UsernameNotFoundException){
                     respBean=RespBean.err("用户名或者密码输入错误");
                 }else if(e instanceof LockedException){
                     respBean=RespBean.err("用户已被锁定，请联系管理员");
                 }else if(e instanceof CredentialsExpiredException){
                     respBean=RespBean.err("密码过期,请联系管理员");
                 }else if(e instanceof AccountExpiredException){
                     respBean=RespBean.err("用户过期,请联系管理员");
                 }else if(e instanceof DisabledException){
                     respBean=RespBean.err("用户被禁用,请联系管理员");
                 }else {
                     respBean=RespBean.err("登录失败");
                 }
                 respBean.setStatus(404);
                PrintWriter out=httpServletResponse.getWriter();
                out.write(new ObjectMapper().writeValueAsString(respBean));
                out.flush();
                out.close();
            }
        }).successHandler(new AuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                RespBean respBean=RespBean.ok("login success");
                PrintWriter out=httpServletResponse.getWriter();
                out.write(new ObjectMapper().writeValueAsString(respBean));
                out.flush();
                out.close();
            }
        }).permitAll()
        .and()
        .logout().permitAll()
        .and()
        .csrf().disable()
        .exceptionHandling().accessDeniedHandler(authenticationAccessDeniedHandler);
    }
}
