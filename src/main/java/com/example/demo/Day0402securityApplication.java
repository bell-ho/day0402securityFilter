package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.db.DBManager;
import com.example.demo.vo.MemberVo;

@SpringBootApplication
public class Day0402securityApplication {

//스프링 시큐리티에서 제공하는 PasswordEncoder는 사용자가 등록한 비밀번호를 단방향으로 변환하여 저장하는 용도로 사용된다. 
//그리고 시대적인 흐름에 따라서 점점 고도화된 암호화 알고리즘 구현체가 적용되어간다. 이런 과정에서 서비스에 저장된 비밀번호에 대한 
//암호화 알고리즘을 변경하는 일은 상당히 많은 노력을 요구하게 된다.
//단방향의 변환된 암호를 풀어서 다시 암호화해야 하는데 그게 말처럼 쉬운 일은 아니다.
//그래서 스프링시큐리티에서 내놓은 해결책이 DelegatingPasswordEncoder 다.
	
//패스워드 암호화를 위한 객체를 생성한다 PasswordEncoder : 암호화
	@Bean
	public PasswordEncoder passwordEncoder() { //Delegat : 대리자
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
			   //패스워드인코더공장클래스에서 패스워드인코더를대리해주는 메소드를 생성
	}
	
	
	public static void main(String[] args) {
//		DBManager.insert(new MemberVo("tiger",PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("tiger"),"홍길동","USER"));
//		DBManager.insert(new MemberVo("master",PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("1234"),"길동","ADMIN"));
//		System.out.println("사용자 추가됨");
		System.out.println("패스워드 인코딩 객체");
		SpringApplication.run(Day0402securityApplication.class, args);
	}

}
