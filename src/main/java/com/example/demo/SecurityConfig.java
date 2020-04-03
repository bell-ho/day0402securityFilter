package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration 
//환경설정 이라고 알려줌
@EnableWebSecurity 
//시큐리티설정을 어노테이션으로 할것임을 알려주는것
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
		
//		http.formLogin();			
		//스프링 시큐리티가 제공하는 로그인폼을 사용하겠다
		
		
		//사용자정의 로그인페이지를 지정하면 로그아웃이 제공하지 않는다 
//		로그아웃을 위한 서비스명도 지정한다
		
		//로그인 페이지를 사용자가 만들어 지정해 보자
		http.formLogin().loginPage("/login").permitAll();
		
		//로그아웃을 위한 서비스명도 지정한다
		http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).invalidateHttpSession(true);
		
		http.httpBasic();			
		//http기본 프로토콜을 사용하겠다
	}
	
}
