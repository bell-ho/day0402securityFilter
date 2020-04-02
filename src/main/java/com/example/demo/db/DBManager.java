package com.example.demo.db;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.MemberVo;

public class DBManager {
	private static SqlSessionFactory factory;
	static {
		try {
			Reader reader = Resources.getResourceAsReader("com/example/demo/db/sqlMapconfig.xml");
			factory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static MemberVo selectMember(String username){ //id가 아닌이유는 사용자가 username으로 전해줌
		SqlSession session = factory.openSession();
		MemberVo m = session.selectOne("member.selectMember",username);
		session.close();
		return m;
	}
	
	public static int insert(MemberVo m) {
		int re = -1;
		SqlSession session = factory.openSession(true);
		re = session.insert("member.insert",m);
		session.close();
		return re;
	}
	
}
