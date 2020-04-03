package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.db.DBManager;
import com.example.demo.vo.MemberVo;

@Service
public class MemberService implements UserDetailsService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	//패스워드 인코딩을 위한 객체를 멤버로 선언 이것은 Day0402securityApplication클래스에서 
	//제공되는 객체를 자동으로 의존관계로 설정한다
	
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	//컨트롤러가 준 vo에 들어있는 패스워드를 인코딩 하여 다시 vo에 담는다
	public int insertMember(MemberVo m) {
		m.setPwd(passwordEncoder.encode(m.getPwd()));
		int re = DBManager.insert(m);
		return re;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		//매개변수로 전달받은 username에 해당하는 회원의 정보를 db로 부터 꺼내온다
		MemberVo m = DBManager.selectMember(username);
		
		//만약 username에 해당하는 회원이 없으면 예외를 발생시킴
		if(m==null) {
			throw new UsernameNotFoundException(username);
		}
		
		//db로 부터 뽑아온 정보를 스프링시큐리티가 인증절차를 할수 있는 객체로 만들어 일을 한다
		return User.builder()
				.username(m.getId()) 
				.password(m.getPwd()) 
				.roles(m.getRole()) 
				.build();
	}

}
