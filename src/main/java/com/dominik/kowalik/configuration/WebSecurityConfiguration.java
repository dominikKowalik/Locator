package com.dominik.kowalik.configuration;

import com.dominik.kowalik.DAL.AccountDao;
import com.dominik.kowalik.model.Account;
import com.sun.xml.internal.bind.annotation.OverrideAnnotationOf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import sun.reflect.annotation.ExceptionProxy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by dominik on 2016-10-26.
 */


@Configuration
public class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

    @Autowired
    AccountDao accountDao;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Account account = accountDao.findByUsername(username);
                if (account != null) {
                    return new User(account.getUsername(), account.getPassword(), true, true, true, true,
                            AuthorityUtils.createAuthorityList("USER"));
                } else {
                    throw new UsernameNotFoundException("could not find the user '"
                            + username + "'");
                }
            }
        };
    }

    @EnableWebSecurity
    @Configuration
    class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        private String REALM = "MY_TEST_REALM";

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable().authorizeRequests().antMatchers("/", "/app.js", "/index.js", "/authInterceptor.js").permitAll().anyRequest().
                    authenticated().and().httpBasic()
                    .authenticationEntryPoint(getBasicAuthEntryPoint()).and().
                    sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().logout().
                    logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/logout").permitAll();
        }

        @Bean
        public CustomBasicAuthenticationEntryPoint getBasicAuthEntryPoint() {
            return new CustomBasicAuthenticationEntryPoint();
        }
    }


}