package com.cos.comorizestart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public BCryptPasswordEncoder encode() {
		return new BCryptPasswordEncoder();
	}
	
	// 모델 : Image, User, Likes, Subscribe, Tag : 인증 필요함.
	// auth 주소 : 인증 필요없음.
	@Bean
	SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/", "/user/**", "/image/**", "/subscribe/**", "/comment/**").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin()
			.loginPage("/auth/signin") // 인증이 필요한 페이지 요청 시 진행 (GET 방식)  
			.loginProcessingUrl("/auth/signin") // 로그인 POST요청 시 진행 (POST 방식)
			.defaultSuccessUrl("/");
		
		return http.build();
	}	
}