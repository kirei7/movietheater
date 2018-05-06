package com.epam.rd.movietheater.config;

import com.epam.rd.movietheater.model.entity.User;
import com.epam.rd.movietheater.security.UserRole;
import com.epam.rd.movietheater.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserService userService;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/events/*/tickets", "/test").hasAuthority(UserRole.BOOKING_MANAGER.toString())
                .anyRequest().authenticated()
                .and()

                .formLogin().loginPage("/login").permitAll()
                .and()

                .logout()
                .permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login?logout")
                .and()

                .rememberMe().key("j$F78p1M_kl0_").tokenValiditySeconds(86400);
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/static/**");
    }

    @PostConstruct
    public void init() {
        User user = new User("user", passwordEncoder.encode("1111"), "mail@mm.com", LocalDate.now().minusYears(23));
        userService.save(user);

        user = new User("manager", passwordEncoder.encode("1111"), "mail@mm.com", LocalDate.now().minusYears(23));
        user.addRole(UserRole.BOOKING_MANAGER);
        userService.save(user);

    }
}