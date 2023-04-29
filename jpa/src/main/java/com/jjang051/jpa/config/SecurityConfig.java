package com.jjang051.jpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Autowired
  private UserDedailServiceImpl userDedailService;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    // cross site 해킹방지용
    http.csrf().disable();
    http
      .authorizeHttpRequests()
      .antMatchers("/", "/member/login", "/member/join")
      .permitAll()
      .antMatchers("/board/**", "/member/info")
      .authenticated()
      .and()
      .formLogin()
      .loginPage("/member/login")
      .loginProcessingUrl("/member/login")
      .defaultSuccessUrl("/")
      .and()
      .logout()
      .logoutUrl("/member/logout")
      .logoutSuccessUrl("/");

    http.userDetailsService(userDedailService);

    return http.build();
  }
}
