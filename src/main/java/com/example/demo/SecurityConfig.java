package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration //환경설정 이라고 알려줌
@EnableWebSecurity //시큐리티설정을 어노테이션으로 할것임을 알려주는것
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
//		super.configure(http);
		
		http.authorizeRequests() 	//사용자의 요청별 권한설정을 하겠다
		
		.mvcMatchers("/","/all/**") //이러한 요청들을 아무나 사용가능
		.permitAll()				//어디서든지 서비스
		.mvcMatchers("/admin/**")	//이러한 요청은 
		.hasRole("ADMIN") 			//어드민 권한이 있어야 사용가능
		.anyRequest()				//그외 누구든지
		.authenticated(); 			//인증만(로그인만) 하면됨
		
		http.formLogin();			//스프링 시큐리티가 제공하는 로그인폼을 사용하겠다
		http.httpBasic();			//http기본 프로토콜을 사용하겠다
	}
	
}
