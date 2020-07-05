package com.hss.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hss.util.Msg;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Configuration
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("admin").password("123").roles("ADMIN")
                .and()
                .withUser("user").password("123").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/admin/**")
                .hasRole("ADMIN")
                .antMatchers("/user/**")
                .hasRole("USER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/loginPage")
                .loginProcessingUrl("/login")
                .usernameParameter("userName")
                .passwordParameter("password")
//                .defaultSuccessUrl("/indexPage",true)
//                .failureUrl("/loginPage")
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
                            throws IOException, ServletException {

                        successJsonResponse(response);
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
                            throws IOException, ServletException {
                        response.setContentType("application/json;charSet=utf-8");
                        PrintWriter out = response.getWriter();
                        response.setStatus(200);
                        String errorMsg = null;
                        if(e instanceof LockedException){
                            errorMsg = "账号被锁定，登录失败！";
                        }else if(e instanceof BadCredentialsException){
                            errorMsg = "账号名或密码输入错误！";
                        }else if(e instanceof DisabledException){
                            errorMsg = "账号被禁用，登录失败！";
                        }else if(e instanceof AccountExpiredException){
                            errorMsg = "账号已过期，登录失败";
                        }else if(e instanceof CredentialsExpiredException){
                            errorMsg = "密码已过期，登录失败";
                        }else{
                            errorMsg = "登录失败";
                        }
                        ObjectMapper om = new ObjectMapper();
                        out.write(om.writeValueAsString(Msg.success().add("flag",false).add("errorMsg",errorMsg)));
                        out.flush();
                        out.close();
                    }
                })
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .addLogoutHandler(new LogoutHandler() {
                    @Override
                    public void logout(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) {
                        //用户对出的数据清理
                    }
                })
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
                            throws IOException, ServletException {
                        successJsonResponse(response);
                    }
                })
                .and()
                .csrf()
                .disable();
    }

   @Override
  public void configure(WebSecurity web) throws Exception {
      //解决静态资源被拦截的问题
      web.ignoring().antMatchers("/**/*.css", "/**/*.js", "/**/*.png", "/**/*.jpg"
                                ,"/**/*.ico","/**/*.svg","/druid/**");
  }

  private void successJsonResponse(HttpServletResponse response) throws IOException {
      response.setContentType("application/json;charSet=utf-8");
      PrintWriter out = response.getWriter();
      response.setStatus(200);
      ObjectMapper om = new ObjectMapper();
      out.write(om.writeValueAsString(Msg.success().add("flag",true)));
      out.flush();
      out.close();
  }
}
