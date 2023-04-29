package com.jjang051.jpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
public class SecurityConfiguration {

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  @Autowired
  private AuthenticationFailureHandler userLoginFailHandler;

  @Bean
  public PasswordEncoder passwordEncoder() {
    //return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf().disable();
    // prettier-ignore
    http
      .authorizeHttpRequests()
      .antMatchers("/", "/member/login","/member/join")
      .permitAll()
      .antMatchers("/board/**","/member/info")
      .authenticated()
      .and()
      .formLogin()
      .loginPage("/member/login")
      .loginProcessingUrl("/member/login")
      .failureHandler(userLoginFailHandler)
      .defaultSuccessUrl("/board/list")
      .and()
      .logout()
      .logoutUrl("/member/logout")
      .logoutSuccessUrl("/");

    http.userDetailsService(userDetailsService);

    return http.build();
  }
}
