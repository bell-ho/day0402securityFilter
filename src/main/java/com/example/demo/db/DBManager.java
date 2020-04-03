package com.example.demo.db;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.MemberVo;
import com.example.demo.vo.TransferVo;

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
	public static List<MemberVo> selectMemberAll(){
		SqlSession session = factory.openSession();
		List<MemberVo> list  = session.selectList("member.selectAll");
		session.close();
		return list;
	}
	
	public static int insert(MemberVo m) {
		int re = -1;
		SqlSession session = factory.openSession(true);
		re = session.insert("member.insert",m);
		session.close();
		return re;
	}
	//계좌 이체를 위한 매니저 정의
	public static int transfer(TransferVo t) {
		int re = -1;
		SqlSession session = factory.openSession(false);	//true가되면 무조건 트랜잭션이 실행됨
		
		int re1 = session.update("account.withdraw",t);
		int re2 =session.update("account.deposit",t);
		
		if(re1==1 && re2==1) {
			session.commit();
		}else {
			session.rollback();
		}
		
		return re;
	}
}
