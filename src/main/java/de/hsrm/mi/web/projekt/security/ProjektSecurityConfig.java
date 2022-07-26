package de.hsrm.mi.web.projekt.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration @EnableWebSecurity
public class ProjektSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired UserDetailService detailService;

    @Bean PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder authmanagerbuilder) throws Exception{
        PasswordEncoder pwenc = passwordEncoder();

        authmanagerbuilder.userDetailsService(detailService)
        .passwordEncoder(pwenc);
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers("/css/**").permitAll()
            .antMatchers("/registrieren", "/logout").permitAll()
            .antMatchers("/h2-console/**").permitAll()
            .antMatchers("/*").authenticated()
        .and()
            .formLogin()
            .defaultSuccessUrl("/benutzerprofil")
            .permitAll()
        .and()
            .logout()
            .logoutUrl("/logout") // ist auch Default 
            .logoutSuccessUrl("/login")
            .permitAll();
    
    http.csrf().ignoringAntMatchers("/h2-console/**");
    
    http.headers().frameOptions().disable();

    }
    
}
