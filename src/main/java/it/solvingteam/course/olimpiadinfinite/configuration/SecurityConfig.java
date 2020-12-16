package it.solvingteam.course.olimpiadinfinite.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import it.solvingteam.course.olimpiadinfinite.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/nationRepresentative/signup","/auth/signup")                    .permitAll()
                    .anyRequest()                    .authenticated()
                .and()
                .logout()
                .logoutUrl("/auth/signout").logoutSuccessUrl("/auth/signin").and()
                .formLogin()
                    .loginPage("/auth/signin")
                    .loginProcessingUrl("/auth/signin")
                    .failureUrl("/auth/signin?loginError")
                    .defaultSuccessUrl("/")
                .permitAll();
        
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/css/**","/assets/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }


}
