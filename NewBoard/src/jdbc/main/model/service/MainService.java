package jdbc.main.model.service;

import java.sql.Connection;

import jdbc.main.model.dao.MainDAO;
import jdbc.member.dto.Member;
import static jdbc.main.common.JDBCTemplate.*;
public class MainService {
	
	private MainDAO dao = new MainDAO(); 
	

	/** 로그인 서비스 
	 * @param memberId
	 * @param memberPw
	 * @return
	 */
	public Member login(String memberId, String memberPw) throws Exception {
		
		Connection conn = getConnection(); 
		
		Member member = dao.login(conn, memberId, memberPw); 
		
		close(conn); 
		
		return member;
	}


	/** 아이디 중복 확인 서비스 
	 * @param memberId
	 * @return
	 */
	public int idDuplicationCheck(String memberId) throws Exception {
		
		Connection conn = getConnection(); 
		
		int result = dao.idDuplicationCheck(conn, memberId); 
		
		close(conn); 
		
		return result;
	}


	public int signUp(Member member) throws Exception {
		
		Connection conn = getConnection(); 
		
		int result = dao.signUp(conn, member); 
		
		close(conn); 
		
		return result;
	}

	
	
	
	
}
