package com.kids.crm.config;

import com.kids.crm.model.Role;
import com.kids.crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired
    UserService userService;

    @Bean
    public AfterLoginSuccess afterLoginSuccess(){
        return new AfterLoginSuccess();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/teacher/**").hasRole(Role.TEACHER.getNameStripped())
                    .antMatchers("/student/**").hasRole(Role.STUDENT.getNameStripped())
                    .antMatchers("/assistant/**").hasAnyRole(Role.ASSISTANT.getNameStripped(), Role.SUPER_ADMIN.getNameStripped())
                    .antMatchers("/superadmin/**").hasRole(Role.SUPER_ADMIN.getNameStripped())
                    .antMatchers("/save","/find", "/list", "/register/**",
                            "/forgot-password", "/images/**", "/css/**", "/js/**", "/ui-lib/**", "/api/**","/forgot-password1","/reset-password", "/verify/email/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                        .loginPage("/login")
                        .successHandler(afterLoginSuccess())
                    //MOVED_TO_AFTERLOGINSUCCESS    .defaultSuccessUrl("/subject", true) //redirect after login. true means always go to this url. False mean goto last accessed url
                .permitAll()
                      .and()
                .logout()
                      .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
    }


}
